package com.vian.leave.service.infrastructure.rabbitmq.direct;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @ClassName: RoutingListener
 * @Author: lin long fa
 * @Date: 2024-08-16 18:16:02
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class RoutingListener {
    @NonNull
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "routingFirstQueue")
    public void routingFirstListener(String message) {
        System.out.println("【routing first】" + message);
    }

    @RabbitListener(queues = "routingSecondQueue")
    public void routingSecondListener(String message) {
        System.out.println("【routing second】" + message);
    }

    @RabbitListener(queues = "routingThirdQueue")
    public void routingThirdListener(String message) {
        System.out.println("【routing third】" + message);
    }
}
