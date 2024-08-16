package com.vian.leave.service.infrastructure.rabbitmq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;


/**
 * @Description: TODO
 * @ClassName: FanoutConfig
 * @Author: lin long fa
 * @Date: 2024-08-16 17:56:57
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Configurable
public class FanoutConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("PUBLISH_SUBSCRIBE_EXCHANGE");
    }

    @Bean
    public Queue psFirstQueue() {
        return new Queue("psFirstQueue");
    }

    @Bean
    public Queue psSecondQueue() {
        return new Queue("psSecondQueue");
    }

    @Bean
    public Queue psThirdQueue() {
        return new Queue("psThirdQueue");
    }

    @Bean
    public Binding routingFirstBinding() {
        return BindingBuilder.bind(psFirstQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding routingSecondBinding() {
        return BindingBuilder.bind(psSecondQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding routingThirdBinding() {
        return BindingBuilder.bind(psThirdQueue()).to(fanoutExchange());
    }
}
