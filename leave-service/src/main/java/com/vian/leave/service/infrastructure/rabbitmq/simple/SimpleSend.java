package com.vian.leave.service.infrastructure.rabbitmq.simple;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @ClassName: WorkSend
 * @Author: lin long fa
 * @Date: 2024-08-16 16:52:46
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SimpleSend {

    @NonNull
    private RabbitTemplate rabbitTemplate;

    public String simpleSend() {
        rabbitTemplate.convertAndSend("simple", "this is news");
        return "this is news";
    }
}
