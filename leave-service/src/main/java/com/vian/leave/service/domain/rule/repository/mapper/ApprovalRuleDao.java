package com.vian.leave.service.domain.rule.repository.mapper;

import com.mybatisflex.core.BaseMapper;
import com.vian.leave.service.domain.rule.entity.ApprovalRule;
import com.vian.leave.service.domain.rule.repository.po.ApprovalRulePO;
import org.apache.ibatis.annotations.Select;

public interface ApprovalRuleDao extends BaseMapper<ApprovalRulePO> {

    @Select(value = "select r from ApprovalRulePO r where r.applicantRoleId=?1 and r.leaveType=?2 and duration=?3")
    ApprovalRule findRule(String applicantRoleId, String leaveType, long duration);
}
