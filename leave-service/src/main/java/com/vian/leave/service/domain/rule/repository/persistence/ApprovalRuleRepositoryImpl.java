package com.vian.leave.service.domain.rule.repository.persistence;

import com.vian.leave.service.domain.rule.entity.ApprovalRule;
import com.vian.leave.service.domain.rule.repository.facade.ApprovalRuleRepositoryInterface;
import com.vian.leave.service.domain.rule.repository.mapper.ApprovalRuleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApprovalRuleRepositoryImpl implements ApprovalRuleRepositoryInterface {

    @Autowired
    ApprovalRuleDao ruleDao;

    @Override
    public int getLeaderMaxLevel(ApprovalRule rule) {
        String personType = rule.getPersonType();
        String leaveType = rule.getLeaveType();
        rule = ruleDao.findRule(personType, leaveType, rule.getDuration());
        return rule.getMaxLeaderLevel();
    }
}
