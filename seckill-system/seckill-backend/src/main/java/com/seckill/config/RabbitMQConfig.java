package com.seckill.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置类
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 秒杀订单队列
     */
    public static final String SECKILL_ORDER_QUEUE = "seckill.order.queue";

    /**
     * 秒杀订单交换机
     */
    public static final String SECKILL_ORDER_EXCHANGE = "seckill.order.exchange";

    /**
     * 秒杀订单 RoutingKey
     */
    public static final String SECKILL_ORDER_ROUTING_KEY = "seckill.order";

    /**
     * 消息转换器
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * RabbitTemplate 配置
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    /**
     * 监听容器工厂配置
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());
        // 手动 ACK
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 预取数量，防止消息堆积
        factory.setPrefetchCount(1);
        return factory;
    }

    /**
     * 秒杀订单队列
     */
    @Bean
    public Queue seckillOrderQueue() {
        return QueueBuilder.durable(SECKILL_ORDER_QUEUE).build();
    }

    /**
     * 秒杀订单交换机（Topic 类型）
     */
    @Bean
    public TopicExchange seckillOrderExchange() {
        return ExchangeBuilder.topicExchange(SECKILL_ORDER_EXCHANGE).durable(true).build();
    }

    /**
     * 队列绑定到交换机
     */
    @Bean
    public Binding seckillOrderBinding(Queue seckillOrderQueue, TopicExchange seckillOrderExchange) {
        return BindingBuilder.bind(seckillOrderQueue)
                .to(seckillOrderExchange)
                .with(SECKILL_ORDER_ROUTING_KEY);
    }
}
