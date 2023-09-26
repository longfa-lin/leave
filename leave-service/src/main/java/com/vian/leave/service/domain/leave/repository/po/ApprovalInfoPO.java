package com.vian.leave.service.domain.leave.repository.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

@Table("approval_info")
@Data
public class ApprovalInfoPO {

    @Id(keyType = KeyType.Auto)
    String approvalInfoId;
    String leaveId;
    String applicantId;
    String approverId;
    int approverLevel;
    String approverName;
    String msg;
    long time;

}
