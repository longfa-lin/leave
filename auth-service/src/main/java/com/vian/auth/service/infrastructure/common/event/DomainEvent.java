package com.vian.auth.service.infrastructure.common.event;

import lombok.Data;

import java.util.Date;

@Data
public class DomainEvent {

    String id;
    Date timestamp;
    String source;
    String data;
}
