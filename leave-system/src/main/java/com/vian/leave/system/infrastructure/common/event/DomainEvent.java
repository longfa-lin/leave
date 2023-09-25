package com.vian.leave.system.infrastructure.common.event;

import java.util.Date;

/**
 * @Description: 领域事件基类
 * @ClassName: DomainEvent
 * @Author: vian
 * @Date: 2023-09-25 18:21:20
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
public class DomainEvent {
    String id;
    Date timestamp;
    String source;
    String data;
}
