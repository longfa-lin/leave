package com.vian.leave.service.domain.leave.entity.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 请假申请人值对象
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {

    private Long personId;
    private String personName;
    private String personType;
}
