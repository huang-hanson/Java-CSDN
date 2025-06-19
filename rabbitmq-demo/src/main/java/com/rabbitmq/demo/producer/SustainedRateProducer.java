package com.rabbitmq.demo.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AlreadyClosedException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.demo.config.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SustainedRateProducer {
    // 配置参数
    public static final String hostName = "10.100.2.168";
    public static final String userName = "51job_msguse";
    public static final String password = "51job_msguse";
//    public static final String hostName = "rabbitmq-cn-cfn4ai6cm06.cn-shanghai.amqp-10.vpc.mq.amqp.aliyuncs.com";
//    public static final String userName = "MjpyYWJiaXRtcS1jbi1jZm40YWk2Y20wNjpMVEFJNXRGVGJoODVzcTJkOWVWNEFLQzc=";
//    public static final String password = "MTMzN0YxQjJERDE4QjI1M0M3NkE0NkQ2QTQyQzAyNEIyRkIxNzgwNToxNzQ5MDMyODI1NTA5";
    public static final String virtualHost = "51job";
    public static final int port = 5672;
    public static final boolean enableSSL = false;
    public static final int TARGET_RATE = 200; // 目标速率：200条/秒

    private Channel channel;
    private final ConcurrentNavigableMap<Long, String> outstandingConfirms;
    private final ConnectionFactory factory;
    private final String exchangeName;
    private final String queueName;
    private final String routingKey;
    private final AtomicLong sentCounter = new AtomicLong(0);
    private final AtomicLong ackCounter = new AtomicLong(0);
    private volatile boolean running = true;

    public SustainedRateProducer(ConnectionFactory factory, String exchangeName, String queueName, String routingKey)
            throws IOException, TimeoutException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.factory = factory;
        this.outstandingConfirms = new ConcurrentSkipListMap<>();
        this.channel = factory.createChannel();
        this.exchangeName = exchangeName;
        this.queueName = queueName;
        this.routingKey = routingKey;
    }

    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory(hostName, port, userName, password, virtualHost, enableSSL);
            SustainedRateProducer producer = new SustainedRateProducer(factory,
                    "51job-activity",
                    "51job-apptrack-callback-conf2",
                    "51job.track.callback.conf2.key");

            producer.declare();
            producer.initChannel();

            // 添加关闭钩子
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                producer.stop();
                System.out.println("\nProducer is shutting down...");
                System.out.printf("Final stats - Sent: %d, Acked: %d%n",
                        producer.sentCounter.get(), producer.ackCounter.get());
            }));

            // 启动监控线程
            new Thread(producer::monitor).start();

            // 开始持续发送
            producer.startSending();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initChannel() throws IOException {
        channel.confirmSelect();

        ConfirmCallback cleanOutstandingConfirms = (deliveryTag, multiple) -> {
            if (multiple) {
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliveryTag, true);
                confirmed.keySet().forEach(tag -> {
                    String msgId = confirmed.get(tag);
                    ackCounter.incrementAndGet();
                });
                confirmed.clear();
            } else {
                String msgId = outstandingConfirms.remove(deliveryTag);
                ackCounter.incrementAndGet();
            }
        };

        channel.addConfirmListener(cleanOutstandingConfirms, (deliveryTag, multiple) -> {
            System.err.printf("NACK received for deliveryTag: %d%n", deliveryTag);
        });

        channel.addReturnListener(returnMessage ->
                System.err.println("Message returned: " + returnMessage.getProperties().getMessageId()));
    }

    private void declare() throws IOException {
        channel.exchangeDeclare(exchangeName, "topic", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
    }

    private void startSending() {
        System.out.println("Starting producer at rate: " + TARGET_RATE + " msg/s");

        long intervalNs = 1_000_000_000 / TARGET_RATE; // 每条消息间隔时间(纳秒)
        long nextSendTime = System.nanoTime();

        while (running) {
            try {
                // 计算下一条消息应该发送的时间
                nextSendTime += intervalNs;

                // 发送消息
                String content = "Msg-" + sentCounter.incrementAndGet();
                sendMessage(content);

                // 等待到下一个发送时间点
                long sleepNs = nextSendTime - System.nanoTime();
                if (sleepNs > 0) {
                    long sleepMs = sleepNs / 1_000_000;
                    int sleepNsRemainder = (int) (sleepNs % 1_000_000);
                    Thread.sleep(sleepMs, sleepNsRemainder);
                }
            } catch (Exception e) {
                System.err.println("Error sending message: " + e.getMessage());
                try {
                    Thread.sleep(1000); // 出错后暂停1秒
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void sendMessage(String content) throws Exception {
        int retryCount = 0;
        boolean success = false;

        while (!success && retryCount < 3 && running) {
            try {
                String msgId = UUID.randomUUID().toString();
                AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                        .messageId(msgId)
                        .build();

                channel.basicPublish(exchangeName, routingKey, true, props,
                        content.getBytes(StandardCharsets.UTF_8));

                outstandingConfirms.put(channel.getNextPublishSeqNo(), msgId);
                success = true;
            } catch (AlreadyClosedException e) {
                if (channelClosedByServer(e.getMessage())) {
                    System.out.println("Reconnecting channel...");
                    reconnectChannel();
                } else {
                    throw e;
                }
            } catch (Exception e) {
                retryCount++;
                if (retryCount >= 3) {
                    throw e;
                }
                Thread.sleep(1000); // 重试前等待
            }
        }
    }

    private void monitor() {
        long lastSent = 0;
        long lastAcked = 0;
        long lastTime = System.currentTimeMillis();

        while (running) {
            try {
                Thread.sleep(1000); // 每秒报告一次

                long currentSent = sentCounter.get();
                long currentAcked = ackCounter.get();
                long currentTime = System.currentTimeMillis();

                double sendRate = 1000.0 * (currentSent - lastSent) / (currentTime - lastTime);
                double ackRate = 1000.0 * (currentAcked - lastAcked) / (currentTime - lastTime);

                System.out.printf("Rate - Send: %.1f msg/s, Ack: %.1f msg/s | Total - Sent: %d, Acked: %d%n",
                        sendRate, ackRate, currentSent, currentAcked);

                lastSent = currentSent;
                lastAcked = currentAcked;
                lastTime = currentTime;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void reconnectChannel() throws IOException, TimeoutException,
            NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        factory.closeCon(channel);
        channel = factory.createChannel();
        declare();
        initChannel();
    }

    private void stop() {
        running = false;
        try {
            if (channel != null && channel.isOpen()) {
                channel.close();
            }
            if (factory != null) {
//                factory.close();
                factory.closeCon(channel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean channelClosedByServer(String errorMsg) {
        return errorMsg != null
                && errorMsg.contains("channel.close")
                && errorMsg.contains("reply-code=541")
                && errorMsg.contains("reply-text=InternalError");
    }
}