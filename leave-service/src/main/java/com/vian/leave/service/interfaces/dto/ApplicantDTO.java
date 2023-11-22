package com.vian.leave.service.interfaces.dto;

import lombok.Data;

@Data
public class ApplicantDTO {

    private Long personId;
    private String personName;
    private Long leaderId;
    private String applicantType;
    private Integer roleLevel;
}
