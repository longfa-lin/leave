package com.vian.leave.service.infrastructure.rabbitmq.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Description: TODO
 * @ClassName: RpcConsumer
 * @Author: lin long fa
 * @Date: 2024-08-19 17:50:29
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Slf4j
public class RpcConsumer {

    @RabbitListener(queuesToDeclare = @Queue("rpcQueue"))
    public String rpcListener(String message) {
        System.out.println("【rpc接收消息】" + message);
        return "rpc 返回" + message;
    }
}
