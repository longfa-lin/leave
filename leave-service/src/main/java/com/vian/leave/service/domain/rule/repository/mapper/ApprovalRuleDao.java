package com.vian.leave.service.domain.rule.repository.mapper;

import com.mybatisflex.core.BaseMapper;
import com.vian.leave.service.domain.rule.entity.ApprovalRule;
import com.vian.leave.service.domain.rule.repository.po.ApprovalRulePO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ApprovalRuleDao extends BaseMapper<ApprovalRulePO> {

    @Select(value = "select * from approval_rule r where r.person_type=#{personType} " +
            "and r.leave_type=#{leaveType} and r.duration=#{duration}")
    ApprovalRule findRule(@Param("personType") String personType, @Param("leaveType") String leaveType, @Param("duration") long duration);
}
