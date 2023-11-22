package com.vian.leave.service.domain.leave.entity;

import com.vian.leave.service.domain.leave.entity.valueobject.ApprovalType;
import com.vian.leave.service.domain.leave.entity.valueobject.Approver;
import lombok.Data;

//审批意见实体
@Data
public class ApprovalInfo {

    private Long approvalInfoId;
    private Approver approver;
    private ApprovalType approvalType;
    private String msg;
    private Long time;

}
