package com.rabbitmq.demo.consumer;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.demo.config.ConnectionFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

public class Consumer {
    //设置为云消息队列 RabbitMQ 版实例的接入点。
    public static final String hostName = "xxx";
    //设置为云消息队列 RabbitMQ 版实例的静态用户名。
    public static final String userName = "xxx";
    //设置为云消息队列 RabbitMQ 版实例的静态用户名密码。
    public static final String password = "xxx";
    //设置为云消息队列 RabbitMQ 版实例的Vhost名称。
    public static final String virtualHost = "xxx";

    //如果使用5671端口，需要enableSSL设置为true。
    public static final int port = 5672;
    public static final boolean enableSSL = false;

    private final Channel channel;
    private final String queue;

    public Consumer(Channel channel, String queue) {
        this.channel = channel;
        this.queue = queue;
    }

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        ConnectionFactory factory = new ConnectionFactory(hostName, port, userName, password, virtualHost, enableSSL);
        Channel channel = factory.createChannel();
        channel.basicQos(50);

        //设置为云消息队列 RabbitMQ 版实例的Queue名称。需要和生产者中设置的Queue名称一致。
        Consumer consumer = new Consumer(channel, "51job-apptrack-callback-conf");

        consumer.consume();
    }

    public void consume() throws IOException, InterruptedException {
        channel.basicConsume(queue, false, "yourConsumerTag", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {

                //业务处理。
                System.out.println("receive: msgId=" + properties.getMessageId());

                //消费者需要在有效时间内提交ack，否则消息会重新推送，最多推送16次。
                //若推送16次还未成功，则消息被丢弃或者进入死信Exchange。
                //专业版实例的有效时间为1分钟，企业版和Serverless实例为5分钟，铂金版实例为30分钟。
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

        CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                channel.getConnection().close();
            } catch (IOException e) {
                System.out.println("close connection error." + e);
            }
            latch.countDown();
        }));
        latch.await();
    }
}