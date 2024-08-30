package com.vian.leave.service.infrastructure.rabbitmq.direct;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description: TODO
 * @ClassName: Routing
 * @Author: lin long fa
 * @Date: 2024-08-16 18:14:32
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class Routing {

    @Nonnull
    private RabbitTemplate rabbitTemplate;

    public String routingFirst() {
        log.info("当前时间：{},发送一条信息给两个 TTL 队列", new Date());
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message.getMessageProperties().setDelay(10000);
            message.getMessageProperties().setExpiration("10000");
            return message;
        };
        // 使用不同的routingKey 转发到不同的队列
        rabbitTemplate.convertAndSend("routingExchange", "firstRouting", " first routing message", messagePostProcessor);
        rabbitTemplate.convertAndSend("routingExchange", "secondRouting", " second routing message", messagePostProcessor);
        rabbitTemplate.convertAndSend("routingDelayedExchange", "thirdRouting", " third routing message ttl 10000", messagePostProcessor);
        log.info(" 当 前 时 间 ： {}, 发 送 一 条 延 迟 10000 毫秒的信息给队列 delayed.queue:{}", new Date(), "third routing message ttl 10000");
        rabbitTemplate.convertAndSend("routingDelayedExchange", "thirdRouting", " third routing message ttl 5000", message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message.getMessageProperties().setDelay(5000);
            message.getMessageProperties().setExpiration("5000");
            return message;
        });
        log.info(" 当 前 时 间 ： {}, 发 送 一 条 延 迟 5000 毫秒的信息给队列 delayed.queue:{}", new Date(), "third routing message ttl 5000");
        return "ok";
    }

    public String notRoutingReturnToExchange() {
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message.getMessageProperties().setDelay(10000);
            message.getMessageProperties().setExpiration("10000");
            return message;
        };
        // 使用不同的routingKey 转发到不同的队列
        rabbitTemplate.convertAndSend("routingExchange", "thirdRoutings", " third routings message", messagePostProcessor);

        return "ok";
    }
}
