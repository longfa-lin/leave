package com.vian.leave.service.infrastructure.rabbitmq.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @Description: TODO
 * @ClassName: DirectConfig
 * @Author: lin long fa
 * @Date: 2024-08-16 18:13:14
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Configurable
public class DirectConfig {
    @Bean
    public Queue routingFirstQueue() {
        return new Queue("routingFirstQueue");
    }

    @Bean
    public Queue routingSecondQueue() {
        return new Queue("routingSecondQueue");
    }

    @Bean
    public Queue routingThirdQueue() {
        return new Queue("routingThirdQueue");
    }

    @Bean
    public DirectExchange routingExchange() {
        return new DirectExchange("routingExchange");
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
}
