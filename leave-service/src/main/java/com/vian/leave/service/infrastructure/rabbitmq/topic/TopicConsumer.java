package com.vian.leave.service.infrastructure.rabbitmq.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Description: TODO
 * @ClassName: TopicConsumer
 * @Author: lin long fa
 * @Date: 2024-08-19 17:42:49
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Slf4j
public class TopicConsumer {


    @RabbitListener(queues = "topicFirstQueue")
    public void topicFirstListener(String message) {
        log.info("topicFirstListener: {}", message);
    }

    @RabbitListener(queues = "topicSecondQueue")
    public void topicSecondListener(String message) {
        System.out.println("【topic second】" + message);
    }
}
