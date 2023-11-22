package com.vian.leave.service.interfaces.dto;

import lombok.Data;

@Data
public class ApprovalInfoDTO {

    private Long approvalInfoId;
    private ApproverDTO approverDTO;
    private String msg;
    private Long time;
}
