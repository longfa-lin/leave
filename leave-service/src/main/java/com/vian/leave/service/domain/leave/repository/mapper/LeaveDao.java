package com.vian.leave.service.domain.leave.repository.mapper;

import com.mybatisflex.core.BaseMapper;
import com.vian.leave.service.domain.leave.repository.po.LeavePO;

import java.util.List;

public interface LeaveDao extends BaseMapper<LeavePO> {

    List<LeavePO> queryByApplicantId(Long applicantId);

    List<LeavePO> queryByApproverId(Long approverId);
}
