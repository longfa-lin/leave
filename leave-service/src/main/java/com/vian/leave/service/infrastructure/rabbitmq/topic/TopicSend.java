package com.vian.leave.service.infrastructure.rabbitmq.topic;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @ClassName: TopicSend
 * @Author: lin long fa
 * @Date: 2024-08-19 17:36:11
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TopicSend {

    @NonNull
    private final RabbitTemplate rabbitTemplate;

    public String topicFirstSend() {
        rabbitTemplate.convertAndSend("topicExchange", "www.taobao.com", "www.taobao.com");
        rabbitTemplate.convertAndSend("topicExchange", "taobao.com", "taobao.com");
        rabbitTemplate.convertAndSend("topicExchange", "www.jd", "www.jd");
        return "ok";
    }
}
