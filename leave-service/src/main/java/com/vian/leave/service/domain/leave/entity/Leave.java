package com.vian.leave.service.domain.leave.entity;

import com.vian.leave.service.domain.leave.entity.valueobject.Applicant;
import com.vian.leave.service.domain.leave.entity.valueobject.Approver;
import com.vian.leave.service.domain.leave.entity.valueobject.LeaveType;
import com.vian.leave.service.domain.leave.entity.valueobject.Status;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 请假单信息
 */
@Data
public class Leave {

    private Long id;
    // 请假申请人值对象
    private Applicant applicant;
    // 审批人值对象
    private Approver approver;
    // 请假类型
    private LeaveType type;
    // 状态
    private Status status;
    // 开始时间
    private Date startTime;
    // 结束时间
    private Date endTime;
    // 时长
    private Long duration;
    //审批领导的最大级别
    private Integer leaderMaxLevel;
    // 当前审批意见实体
    private ApprovalInfo currentApprovalInfo;
    // 历史审批意见
    private List<ApprovalInfo> historyApprovalInfos;

    public long getDuration() {
        return endTime.getTime() - startTime.getTime();
    }

    public Leave addHistoryApprovalInfo(ApprovalInfo approvalInfo) {
        if (null == historyApprovalInfos)
            historyApprovalInfos = new ArrayList<>();
        this.historyApprovalInfos.add(approvalInfo);
        return this;
    }

    public Leave create() {
        this.setStatus(Status.APPROVING);
        this.setStartTime(new Date());
        return this;
    }

    public Leave agree(Approver nextApprover) {
        this.setStatus(Status.APPROVING);
        this.setApprover(nextApprover);
        return this;
    }

    public Leave reject(Approver approver) {
        this.setApprover(approver);
        this.setStatus(Status.REJECTED);
        this.setApprover(null);
        return this;
    }

    public Leave finish() {
        this.setApprover(null);
        this.setStatus(Status.APPROVED);
        this.setEndTime(new Date());
        this.setDuration(this.getEndTime().getTime() - this.getStartTime().getTime());
        return this;
    }
}
