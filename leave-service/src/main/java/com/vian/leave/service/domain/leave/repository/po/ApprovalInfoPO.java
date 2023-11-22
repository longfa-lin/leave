package com.vian.leave.service.domain.leave.repository.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;

@Table("approval_info")
@Data
public class ApprovalInfoPO {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    private Long approvalInfoId;
    private Long leaveId;
    private Long applicantId;
    private Long approverId;
    private Integer approverLevel;
    private String approverName;
    private String msg;
    private Long time;

}
