package com.vian.leave.service.infrastructure.common.event;

import com.vian.leave.service.domain.leave.event.LeaveEvent;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    public void publish(LeaveEvent event){
        //send to MQ
        //mq.send(event);
    }
}