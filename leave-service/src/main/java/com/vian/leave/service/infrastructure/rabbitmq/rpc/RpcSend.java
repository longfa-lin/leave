package com.vian.leave.service.infrastructure.rabbitmq.rpc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @ClassName: RpcSend
 * @Author: lin long fa
 * @Date: 2024-08-19 17:48:26
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class RpcSend {

    private final RabbitTemplate rabbitTemplate;

    public void rpcSend() {
        Object receive = rabbitTemplate.convertSendAndReceive("rpcQueue", "rpc");
        log.info("【发送端接收消息】:{}", receive);
    }
}
