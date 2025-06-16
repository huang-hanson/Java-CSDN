package com.rabbitmq.demo.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName RabbitMQConsumer
 * @Description mq测试消费
 * @date 2025/6/11 16:34
 **/
public class RabbitMQConsumer2 {

    private final static String HOST = "xxx";
    private final static int PORT = 5672;
    private final static String USERNAME = "xxx";    // 替换为你的用户名
    private final static String PASSWORD = "xxx"; // 替换为你的密码
    private final static String QUEUE_NAME = "xxx";
    private final static String VIRTUAL_HOST = "xxx"; // 替换为你的VHost名称

//    private final static String HOST = "xxx";
//    private final static int PORT = 5672;
//    private final static String USERNAME = "xxx";    // 替换为你的用户名
//    private final static String PASSWORD = "xxx"; // 替换为你的密码
//    private final static String QUEUE_NAME = "xxx";
//    private final static String VIRTUAL_HOST = "xxx"; // 替换为你的VHost名称

    public static void main(String[] args) {
        // 1. 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);    // 替换为你的用户名
        factory.setPassword(PASSWORD); // 替换为你的密码
        factory.setVirtualHost(VIRTUAL_HOST); // 关键：设置虚拟主机

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // 2. 声明队列（参数说明见下文）
            channel.queueDeclare(QUEUE_NAME,
                    true,   // 持久化队列（重启后依然存在）
                    false,  // 非独占队列
                    false,  // 不自动删除队列
                    null); // 其他参数（如TTL、死信队列等）

            System.out.println(" [*] 等待消息 (VHost: " + VIRTUAL_HOST + ")。按 Ctrl+C 退出");

            // 3. 消息回调处理（打印消息内容+消息头）
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("\n [x] 收到消息:");
                System.out.println("  - 内容: " + message);
                System.out.println("  - 路由键: " + delivery.getEnvelope().getRoutingKey());
                System.out.println("  - 消息头: " + delivery.getProperties().getHeaders());

                // 手动确认消息（关闭自动确认）
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            };

            // 4. 消费消息（关闭自动确认）
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {});

            // 保持程序持续运行
            while (true) {
                Thread.sleep(1000);
            }

        } catch (IOException | TimeoutException | InterruptedException e) {
            System.err.println("RabbitMQ 错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}