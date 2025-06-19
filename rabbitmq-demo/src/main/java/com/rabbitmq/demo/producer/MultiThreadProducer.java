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

public class MultiThreadProducer {
    // 配置参数
    public static final String hostName = "rabbitmq-cn-cfn4ai6cm06.cn-shanghai.amqp-10.vpc.mq.amqp.aliyuncs.com";
    public static final String userName = "MjpyYWJiaXRtcS1jbi1jZm40YWk2Y20wNjpMVEFJNXRGVGJoODVzcTJkOWVWNEFLQzc=";
    public static final String password = "MTMzN0YxQjJERDE4QjI1M0M3NkE0NkQ2QTQyQzAyNEIyRkIxNzgwNToxNzQ5MDMyODI1NTA5";
    public static final String virtualHost = "51job";
    public static final int port = 5672;
    public static final boolean enableSSL = false;

    private Channel channel;
    private final ConcurrentNavigableMap<Long, String> outstandingConfirms;
    private final ConnectionFactory factory;
    private final String exchangeName;
    private final String queueName;
    private final String routingKey;
    private final AtomicInteger sentCount = new AtomicInteger(0);
    private final AtomicInteger ackCount = new AtomicInteger(0);

    public MultiThreadProducer(ConnectionFactory factory, String exchangeName, String queueName, String routingKey)
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
            MultiThreadProducer producer = new MultiThreadProducer(factory,
                    "51job-activity",
                    "51job-apptrack-callback-conf2",
                    "51job.track.callback.conf2.key");

            producer.declare();
            producer.initChannel();

            // 发送10000条消息
            producer.sendMessages(10000);

            // 等待所有消息确认
            while (producer.ackCount.get() < 10000) {
                Thread.sleep(1000);
                System.out.printf("Waiting for confirms... Sent: %d, Acked: %d%n",
                        producer.sentCount.get(), producer.ackCount.get());
            }

            System.out.println("All messages sent and confirmed successfully!");

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
                    System.out.printf("ACK: msgId=%s, tag=%d, multiple=%b%n", msgId, tag, true);
                    ackCount.incrementAndGet();
                });
                confirmed.clear();
            } else {
                String msgId = outstandingConfirms.remove(deliveryTag);
                System.out.printf("ACK: msgId=%s, tag=%d, multiple=%b%n", msgId, deliveryTag, false);
                ackCount.incrementAndGet();
            }
        };

        channel.addConfirmListener(cleanOutstandingConfirms, (deliveryTag, multiple) -> {
            String msgId = outstandingConfirms.get(deliveryTag);
            System.err.printf("NACK: msgId=%s, tag=%d, multiple=%b%n", msgId, deliveryTag, multiple);
            // 这里可以添加重试逻辑
        });

        channel.addReturnListener(returnMessage ->
                System.out.println("Returned: msgId=" + returnMessage.getProperties().getMessageId()));
    }

    private void declare() throws IOException {
        channel.exchangeDeclare(exchangeName, "topic", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
    }

    private void sendMessages(int totalMessages) throws Exception {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < totalMessages; i++) {
            String content = "Message-" + (i + 1);
            boolean success = false;
            int retryCount = 0;

            while (!success && retryCount < 3) { // 最多重试3次
                try {
                    doSend(content);
                    sentCount.incrementAndGet();
                    success = true;

                    // 每100条打印一次进度
                    if (i % 100 == 0) {
                        System.out.printf("Sent %d messages, progress: %.1f%%%n",
                                i, (i * 100.0 / totalMessages));
                    }

                } catch (Exception e) {
                    retryCount++;
                    if (retryCount >= 3) {
                        throw e;
                    }
                    System.err.println("Retry " + retryCount + " for message " + i + ": " + e.getMessage());
                    Thread.sleep(1000); // 等待1秒后重试
                }
            }
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.printf("Finished sending %d messages in %d ms (%.1f msg/s)%n",
                totalMessages, duration, totalMessages * 1000.0 / duration);
    }

    private void doSend(String content) throws IOException, TimeoutException,
            NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        try {
            String msgId = UUID.randomUUID().toString();
            AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                    .messageId(msgId)
                    .build();

            channel.basicPublish(exchangeName, routingKey, true, props,
                    content.getBytes(StandardCharsets.UTF_8));

            outstandingConfirms.put(channel.getNextPublishSeqNo(), msgId);

        } catch (AlreadyClosedException e) {
            if (channelClosedByServer(e.getMessage())) {
                System.out.println("Reconnecting channel...");
                reconnectChannel();
                doSend(content); // 重试发送
            } else {
                throw e;
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

    private boolean channelClosedByServer(String errorMsg) {
        return errorMsg != null
                && errorMsg.contains("channel.close")
                && errorMsg.contains("reply-code=541")
                && errorMsg.contains("reply-text=InternalError");
    }
}