package com.vian.leave.service.domain.leave.repository.persistence;

import com.vian.leave.service.domain.leave.repository.facade.LeaveRepositoryInterface;
import com.vian.leave.service.domain.leave.repository.mapper.ApprovalInfoDao;
import com.vian.leave.service.domain.leave.repository.mapper.LeaveDao;
import com.vian.leave.service.domain.leave.repository.mapper.LeaveEventDao;
import com.vian.leave.service.domain.leave.repository.po.ApprovalInfoPO;
import com.vian.leave.service.domain.leave.repository.po.LeaveEventPO;
import com.vian.leave.service.domain.leave.repository.po.LeavePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * persist entity and handle event in repository
 */
@Repository
public class LeaveRepositoryImpl implements LeaveRepositoryInterface {

    @Autowired
    LeaveDao leaveDao;
    @Autowired
    ApprovalInfoDao approvalInfoDao;
    @Autowired
    LeaveEventDao leaveEventDao;

    public void save(LeavePO leavePO) {
        //persist leave entity
        leaveDao.insert(leavePO);
        //set leave_id for approvalInfoPO after save leavePO
        leavePO.getHistoryApprovalInfoPOList().forEach(approvalInfoPO -> approvalInfoPO.setLeaveId(leavePO.getId()));
        approvalInfoDao.insertBatch(leavePO.getHistoryApprovalInfoPOList());
    }

    public void saveEvent(LeaveEventPO leaveEventPO) {
        leaveEventDao.insert(leaveEventPO);
    }

    @Override
    public LeavePO findById(Long id) {
//        return leaveDao.findById(id).orElseThrow(() -> new RuntimeException("leave not found"));
        return leaveDao.selectOneById(id);
    }

    @Override
    public List<LeavePO> queryByApplicantId(Long applicantId) {
        List<LeavePO> leavePOList = leaveDao.queryByApplicantId(applicantId);
        leavePOList
                .forEach(leavePO -> {
                    List<ApprovalInfoPO> approvalInfoPOList = approvalInfoDao.queryByLeaveId(leavePO.getId());
                    leavePO.setHistoryApprovalInfoPOList(approvalInfoPOList);
                });
        return leavePOList;
    }

    @Override
    public List<LeavePO> queryByApproverId(Long approverId) {
        List<LeavePO> leavePOList = leaveDao.queryByApproverId(approverId);
        leavePOList
                .forEach(leavePO -> {
                    List<ApprovalInfoPO> approvalInfoPOList = approvalInfoDao.queryByLeaveId(leavePO.getId());
                    leavePO.setHistoryApprovalInfoPOList(approvalInfoPOList);
                });
        return leavePOList;
    }

}
