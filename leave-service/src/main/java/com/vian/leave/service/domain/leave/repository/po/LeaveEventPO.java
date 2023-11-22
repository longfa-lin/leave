package com.vian.leave.service.domain.leave.repository.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.vian.leave.service.domain.leave.event.LeaveEventType;
import lombok.Data;

import java.util.Date;

@Data
@Table("leave_event")
public class LeaveEventPO {
    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    private Long id;
    private LeaveEventType leaveEventType;
    private Date timestamp;
    private String source;
    private String data;
}
