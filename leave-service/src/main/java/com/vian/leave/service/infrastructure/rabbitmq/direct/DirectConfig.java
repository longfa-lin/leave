package com.vian.leave.service.infrastructure.rabbitmq.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 路由模式
 * @ClassName: DirectConfig
 * @Author: lin long fa
 * @Date: 2024-08-16 18:13:14
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Configuration
public class DirectConfig {

    @Bean
    public DirectExchange routingExchange() {
        return new DirectExchange("routingExchange", true, false);
    }

    @Bean
    public Queue routingFirstQueue() {
        return new Queue("routingFirstQueue", true);
    }

    @Bean
    public Queue routingSecondQueue() {
        return new Queue("routingSecondQueue", true);
    }

    @Bean
    public Queue routingThirdQueue() {
        return new Queue("routingThirdQueue", true);
    }

    @Bean
    public Binding routingFirstBind() {
        return BindingBuilder.bind(routingFirstQueue()).to(routingExchange()).with("firstRouting");
    }

    @Bean
    public Binding routingSecondBind() {
        return BindingBuilder.bind(routingSecondQueue()).to(routingExchange()).with("secondRouting");
    }

    @Bean
    public Binding routingThirdBind() {
        return BindingBuilder.bind(routingThirdQueue()).to(routingExchange()).with("thirdRouting");
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);  // 启用生产者确认
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("Message successfully delivered to the exchange");
            } else {
                System.err.println("Failed to deliver message to the exchange: " + cause);
            }
        });
        rabbitTemplate.setReturnsCallback(returned -> {
            System.err.println("Message returned: " + new String(returned.getMessage().getBody()));
            System.err.println("Returned reply code: " + returned.getReplyCode());
            System.err.println("Returned reply text: " + returned.getReplyText());
            System.err.println("Exchange: " + returned.getExchange());
            System.err.println("Routing key: " + returned.getRoutingKey());
        });
        return rabbitTemplate;
    }
}
