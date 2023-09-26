package com.vian.leave.service.domain.rule.entity;

import com.vian.leave.service.domain.leave.entity.Leave;
import lombok.Data;

@Data
public class ApprovalRule {

    String personType;
    String leaveType;
    long duration;
    int maxLeaderLevel;

    public static ApprovalRule getByLeave(Leave leave){
        ApprovalRule rule = new ApprovalRule();
        rule.setPersonType(leave.getApplicant().getPersonType());
        rule.setLeaveType(leave.getType().toString());
        rule.setDuration(leave.getDuration());
        return rule;
    }
}
