package com.vian.leave.service.domain.rule.repository.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.vian.leave.service.domain.leave.entity.valueobject.LeaveType;
import com.vian.leave.service.domain.person.entity.valueobject.PersonType;
import lombok.Data;


@Table("approval_rule")
@Data
public class ApprovalRulePO {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    String id;
    LeaveType leaveType;
    PersonType personType;
    long duration;
    String applicantRoleId;
}
