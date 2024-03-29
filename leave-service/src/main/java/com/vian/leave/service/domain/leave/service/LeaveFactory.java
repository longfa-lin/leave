package com.vian.leave.service.domain.leave.service;

import com.alibaba.fastjson.JSON;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.mybatisflex.core.keygen.impl.FlexIDKeyGenerator;
import com.mybatisflex.core.keygen.impl.SnowFlakeIDKeyGenerator;
import com.vian.leave.service.domain.leave.entity.ApprovalInfo;
import com.vian.leave.service.domain.leave.entity.Leave;
import com.vian.leave.service.domain.leave.entity.valueobject.Applicant;
import com.vian.leave.service.domain.leave.entity.valueobject.Approver;
import com.vian.leave.service.domain.leave.event.LeaveEvent;
import com.vian.leave.service.domain.leave.repository.po.ApprovalInfoPO;
import com.vian.leave.service.domain.leave.repository.po.LeaveEventPO;
import com.vian.leave.service.domain.leave.repository.po.LeavePO;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LeaveFactory {

    public LeavePO createLeavePO(Leave leave) {
        LeavePO leavePO = new LeavePO();
        leavePO.setId(new SnowFlakeIDKeyGenerator().nextId());
        leavePO.setApplicantId(leave.getApplicant().getPersonId());
        leavePO.setApplicantName(leave.getApplicant().getPersonName());
        leavePO.setApproverId(leave.getApprover().getPersonId());
        leavePO.setApproverName(leave.getApprover().getPersonName());
        leavePO.setStartTime(leave.getStartTime());
        leavePO.setStatus(leave.getStatus());
        List<ApprovalInfoPO> historyApprovalInfoPOList = approvalInfoPOListFromDO(leave);
        leavePO.setHistoryApprovalInfoPOList(historyApprovalInfoPOList);
        return leavePO;
    }

    public Leave getLeave(LeavePO leavePO) {
        Leave leave = new Leave();
        Applicant applicant = Applicant.builder()
                .personId(leavePO.getApplicantId())
                .personName(leavePO.getApplicantName())
                .build();
        leave.setApplicant(applicant);
        Approver approver = Approver.builder()
                .personId(leavePO.getApproverId())
                .personName(leavePO.getApproverName())
                .build();
        leave.setApprover(approver);
        leave.setStartTime(leavePO.getStartTime());
        leave.setStatus(leavePO.getStatus());
        List<ApprovalInfo> approvalInfos = getApprovalInfos(leavePO.getHistoryApprovalInfoPOList());
        leave.setHistoryApprovalInfos(approvalInfos);
        return leave;
    }

    public LeaveEventPO createLeaveEventPO(LeaveEvent leaveEvent) {
        LeaveEventPO eventPO = new LeaveEventPO();
        eventPO.setLeaveEventType(leaveEvent.getLeaveEventType());
        eventPO.setSource(leaveEvent.getSource());
        eventPO.setTimestamp(leaveEvent.getTimestamp());
        eventPO.setData(JSON.toJSONString(leaveEvent.getData()));
        return eventPO;
    }

    private List<ApprovalInfoPO> approvalInfoPOListFromDO(Leave leave) {
        return leave.getHistoryApprovalInfos()
                .stream()
                .map(approvalInfo -> approvalInfoPOFromDO(approvalInfo))
                .collect(Collectors.toList());
    }

    private ApprovalInfoPO approvalInfoPOFromDO(ApprovalInfo approvalInfo) {
        ApprovalInfoPO po = new ApprovalInfoPO();
        po.setApproverId(approvalInfo.getApprover().getPersonId());
        po.setApproverLevel(approvalInfo.getApprover().getLevel());
        po.setApproverName(approvalInfo.getApprover().getPersonName());
        po.setApprovalInfoId(approvalInfo.getApprovalInfoId());
        po.setMsg(approvalInfo.getMsg());
        po.setTime(approvalInfo.getTime());
        return po;
    }

    private ApprovalInfo approvalInfoFromPO(ApprovalInfoPO approvalInfoPO) {
        ApprovalInfo approvalInfo = new ApprovalInfo();
        approvalInfo.setApprovalInfoId(approvalInfoPO.getApprovalInfoId());
        Approver approver = Approver.builder()
                .personId(approvalInfoPO.getApproverId())
                .personName(approvalInfoPO.getApproverName())
                .level(approvalInfoPO.getApproverLevel())
                .build();
        approvalInfo.setApprover(approver);
        approvalInfo.setMsg(approvalInfoPO.getMsg());
        approvalInfo.setTime(approvalInfoPO.getTime());
        return approvalInfo;
    }

    private List<ApprovalInfo> getApprovalInfos(List<ApprovalInfoPO> approvalInfoPOList) {
        return approvalInfoPOList.stream()
                .map(this::approvalInfoFromPO)
                .collect(Collectors.toList());
    }
}
