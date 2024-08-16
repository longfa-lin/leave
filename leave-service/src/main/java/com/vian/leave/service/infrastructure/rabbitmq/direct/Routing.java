package com.vian.leave.service.infrastructure.rabbitmq.direct;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // 使用不同的routingKey 转发到不同的队列
        rabbitTemplate.convertAndSend("routingExchange", "firstRouting", " first routing message");
        rabbitTemplate.convertAndSend("routingExchange", "secondRouting", " second routing message");
        rabbitTemplate.convertAndSend("routingExchange", "thirdRouting", " third routing message");
        return "ok";
    }
}
