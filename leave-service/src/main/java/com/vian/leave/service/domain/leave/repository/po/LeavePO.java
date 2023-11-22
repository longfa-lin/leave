package com.vian.leave.service.domain.leave.repository.po;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.vian.leave.service.domain.leave.entity.Leave;
import com.vian.leave.service.domain.leave.entity.valueobject.LeaveType;
import com.vian.leave.service.domain.leave.entity.valueobject.Status;
import com.vian.leave.service.domain.person.entity.valueobject.PersonType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Table("Leave")
@Data
public class LeavePO {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    private Long id;
    private Long applicantId;
    private String applicantName;
    private PersonType applicantType;
    private Long approverId;
    private String approverName;
    private LeaveType leaveType;
    private Status status;
    private Date startTime;
    private Date endTime;
    private Long duration;

    @Column(ignore = true)
    List<ApprovalInfoPO> historyApprovalInfoPOList;

    public Leave toLeave() {
        return new Leave();
    }

}
