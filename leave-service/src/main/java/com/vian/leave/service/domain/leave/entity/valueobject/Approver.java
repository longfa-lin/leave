package com.vian.leave.service.domain.leave.entity.valueobject;

import com.vian.leave.service.domain.person.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 审批人值对象
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Approver {

    private Long personId;
    private String personName;
    private Integer level;

    public static Approver fromPerson(Person person) {
        Approver approver = new Approver();
        approver.setPersonId(person.getPersonId());
        approver.setPersonName(person.getPersonName());
        approver.setLevel(person.getRoleLevel());
        return approver;
    }

}
