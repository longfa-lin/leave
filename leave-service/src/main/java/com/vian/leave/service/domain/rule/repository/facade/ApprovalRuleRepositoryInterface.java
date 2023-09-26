package com.vian.leave.service.domain.rule.repository.facade;


import com.vian.leave.service.domain.rule.entity.ApprovalRule;

public interface ApprovalRuleRepositoryInterface {

    int getLeaderMaxLevel(ApprovalRule rule);
}
