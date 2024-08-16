package com.vian.leave.service.infrastructure.rabbitmq.work;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @ClassName: WorkConsumer
 * @Author: lin long fa
 * @Date: 2024-08-16 16:54:43
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void consume(String message) {
        log.info(message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void consumeSecond(String message) {
        log.info(message);
    }
}
