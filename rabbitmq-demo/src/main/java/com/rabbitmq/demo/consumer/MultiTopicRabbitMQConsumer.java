package com.rabbitmq.demo.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName RabbitMQConsumer
 * @Description mq测试消费
 * @date 2025/6/11 16:34
 **/
public class MultiTopicRabbitMQConsumer {

    private final static String HOST = "xxx";
    private final static int PORT = 5672;
    private final static String USERNAME = "xxx";    // 替换为你的用户名
    private final static String PASSWORD = "xxx"; // 替换为你的密码
    private final static String VIRTUAL_HOST = "xxx"; // 替换为你的VHost名称
    private final static String EXCHANGE_NAME = "xxx"; // Topic交换机名称
    private final static String[] ROUTING_KEYS = {
            "xxx",
            "xxx"
    };

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUAL_HOST);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // 1. 声明Topic交换机（如果不存在则创建）
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true);

            // 2. 为每个Routing Key创建临时队列并绑定
            for (String routingKey : ROUTING_KEYS) {
                // 生成临时队列（非持久化，独占，自动删除）
                String queueName = channel.queueDeclare().getQueue();
                channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
                System.out.println(" [*] 绑定队列 '" + queueName + "' 到路由键 '" + routingKey + "'");

                // 3. 定义消费者回调
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println("\n [x] 收到消息:");
                    System.out.println("  - 来源路由键: " + delivery.getEnvelope().getRoutingKey());
                    System.out.println("  - 内容: " + message);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                };

                // 4. 开始消费
                channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {});
            }

            System.out.println(" [*] 等待多个Topic的消息。按 Ctrl+C 退出");
            while (true) Thread.sleep(1000);

        } catch (IOException | TimeoutException | InterruptedException e) {
            System.err.println("RabbitMQ 错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}