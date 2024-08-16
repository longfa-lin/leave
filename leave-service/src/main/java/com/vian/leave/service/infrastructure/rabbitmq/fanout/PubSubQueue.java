package com.vian.leave.service.infrastructure.rabbitmq.fanout;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @ClassName: PubSubQueue
 * @Author: lin long fa
 * @Date: 2024-08-16 18:12:11
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class PubSubQueue {
    @RabbitListener(queues = "psFirstQueue")
    public void pubsubQueueFirst(String message) {
        System.out.println("【first】:" + message);
    }

    @RabbitListener(queues = "psSecondQueue")
    public void pubsubQueueSecond(String message) {
        System.out.println("【second】:" + message);
    }

    @RabbitListener(queues = "psThirdQueue")
    public void pubsubQueueThird(String message) {
        System.out.println("【third】:" + message);
    }
}
