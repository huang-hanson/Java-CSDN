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

public class Producer {
    //设置为云消息队列 RabbitMQ 版实例的接入点。
    public static final String hostName = "xxx";
    //设置为云消息队列 RabbitMQ 版实例的静态用户名。
    public static final String userName = "xxx";
    //设置为云消息队列 RabbitMQ 版实例的静态用户名密码。
    public static final String password = "xxx";
    //设置为云消息队列 RabbitMQ 版实例的Vhost名称。
    public static final String virtualHost = "51job";


    //如果使用5671端口，需要enableSSL设置为true。
    public static final int port = 5672;
    public static final boolean enableSSL = false;

    private Channel channel;
    private final ConcurrentNavigableMap<Long/*deliveryTag*/, String/*msgId*/> outstandingConfirms;
    private final ConnectionFactory factory;
    private final String exchangeName;
    private final String queueName;
    private final String routingKey;

    public Producer(ConnectionFactory factory, String exchangeName, String queueName, String routingKey) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.factory = factory;
        this.outstandingConfirms = new ConcurrentSkipListMap<>();
        this.channel = factory.createChannel();
        this.exchangeName = exchangeName;
        this.queueName = queueName;
        this.routingKey = routingKey;
    }

    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        //构建连接工厂。
        ConnectionFactory factory = new ConnectionFactory(hostName, port, userName, password, virtualHost, enableSSL);

        //初始化生产者。
        Producer producer = new Producer(factory, "51job-activity", "51job-track-callback-match", "51job.track.callback.match.key");

        //declare。
        producer.declare();

        producer.initChannel();

        //发送消息。
        producer.doSend("hello,amqp");
    }

    private void initChannel() throws IOException {
        channel.confirmSelect();

        ConfirmCallback cleanOutstandingConfirms = (deliveryTag, multiple) -> {
            if (multiple) {
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliveryTag, true);

                for (Long tag : confirmed.keySet()) {
                    String msgId = confirmed.get(tag);
                    System.out.format("Message with msgId %s has been ack-ed. deliveryTag: %d, multiple: %b%n", msgId, tag, true);
                }

                confirmed.clear();
            } else {
                String msgId = outstandingConfirms.remove(deliveryTag);
                System.out.format("Message with msgId %s has been ack-ed. deliveryTag: %d, multiple: %b%n", msgId, deliveryTag, false);
            }
        };
        channel.addConfirmListener(cleanOutstandingConfirms, (deliveryTag, multiple) -> {
            String msgId = outstandingConfirms.get(deliveryTag);
            System.err.format("Message with msgId %s has been nack-ed. deliveryTag: %d, multiple: %b%n", msgId, deliveryTag, multiple);
            // send msg failed, re-publish
        });


        channel.addReturnListener(returnMessage -> System.out.println("return msgId=" + returnMessage.getProperties().getMessageId()));
    }

    private void declare() throws IOException {
        channel.exchangeDeclare(exchangeName, "topic", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
    }


    private void doSend(String content) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        try {
            String msgId = UUID.randomUUID().toString();
            AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().messageId(msgId).build();

            channel.basicPublish(exchangeName, routingKey, true, props, content.getBytes(StandardCharsets.UTF_8));

            outstandingConfirms.put(channel.getNextPublishSeqNo(), msgId);
        } catch (AlreadyClosedException e) {
            //need reconnect if channel is closed.
            String message = e.getMessage();

            System.out.println(message);

            if (channelClosedByServer(message)) {
                factory.closeCon(channel);
                channel = factory.createChannel();
                this.initChannel();
                doSend(content);
            } else {
                throw e;
            }
        }
    }

    private boolean channelClosedByServer(String errorMsg) {
        if (errorMsg != null
                && errorMsg.contains("channel.close")
                && errorMsg.contains("reply-code=541")
                && errorMsg.contains("reply-text=InternalError")) {
            return true;
        } else {
            return false;
        }
    }
}