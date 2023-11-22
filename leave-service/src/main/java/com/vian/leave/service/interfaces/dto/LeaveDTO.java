package com.vian.leave.service.interfaces.dto;

import lombok.Data;

import java.util.List;

@Data
public class LeaveDTO {

    private Long leaveId;
    private ApplicantDTO applicantDTO;
    private ApproverDTO approverDTO;
    private String leaveType;
    private ApprovalInfoDTO currentApprovalInfoDTO;
    private List<ApprovalInfoDTO> historyApprovalInfoDTOList;
    private String startTime;
    private String endTime;
    private Long duration;
    private String status;

}
