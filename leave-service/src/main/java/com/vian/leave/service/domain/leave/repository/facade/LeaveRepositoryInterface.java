package com.vian.leave.service.domain.leave.repository.facade;


import com.vian.leave.service.domain.leave.repository.po.LeaveEventPO;
import com.vian.leave.service.domain.leave.repository.po.LeavePO;

import java.util.List;

public interface LeaveRepositoryInterface {

    void save(LeavePO leavePO);

    void saveEvent(LeaveEventPO leaveEventPO);

    LeavePO findById(String id);

    List<LeavePO> queryByApplicantId(String applicantId);

    List<LeavePO> queryByApproverId(String approverId);

}