package com.vian.leave.service.infrastructure.rabbitmq.fanout;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @ClassName: PublicSubSend
 * @Author: lin long fa
 * @Date: 2024-08-16 18:10:36
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class PublicSubSend {

    @NonNull
    private RabbitTemplate rabbitTemplate;

    public String publishSubSend() {
        rabbitTemplate.convertAndSend("PUBLISH_SUBSCRIBE_EXCHANGE", null, "publish/subscribe hello");
        return "ok";
    }
}
