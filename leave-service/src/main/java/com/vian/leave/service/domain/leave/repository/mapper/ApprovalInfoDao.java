package com.vian.leave.service.domain.leave.repository.mapper;

import com.mybatisflex.core.BaseMapper;
import com.vian.leave.service.domain.leave.repository.po.ApprovalInfoPO;
import com.vian.leave.service.domain.leave.repository.po.LeavePO;

import java.util.List;

public interface ApprovalInfoDao extends BaseMapper<ApprovalInfoPO> {

    List<LeavePO> queryByApplicantId(String applicantId);

    List<ApprovalInfoPO> queryByLeaveId(String leaveId);

}
