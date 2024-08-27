package com.vian.leave.service.infrastructure.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

    @RabbitListener(queues = "routingFirstQueue", ackMode = "MANUAL")
    public void routingFirstListener(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        try {
            // 处理消息
            System.out.println("【routing first】" + message);
            // 手动确认消息
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            // 处理失败，拒绝消息，可以重新放回队列或丢弃
            try {
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
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
