package com.vian.leave.service.infrastructure.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @ClassName: TopicConfig
 * @Author: lin long fa
 * @Date: 2024-08-19 17:33:36
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Configuration
public class TopicConfig {

    // 创建exchange
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    // 创建队列
    @Bean
    public Queue topicFirstQueue() {
        return new Queue("topicFirstQueue");
    }

    @Bean
    public Queue topicSecondQueue() {
        return new Queue("topicSecondQueue");
    }

    @Bean
    public Queue topicThirdQueue() {
        return new Queue("topicThirdQueue");
    }

    // 绑定exchange和队列，并指定key
    @Bean
    public Binding topicFirstBind() {
        // .com 为结尾
        return BindingBuilder.bind(topicFirstQueue()).to(topicExchange()).with("*.com");
    }

    @Bean
    public Binding topicSecondBind() {
        // www.为开头
        return BindingBuilder.bind(topicSecondQueue()).to(topicExchange()).with("www.#");
    }
}
