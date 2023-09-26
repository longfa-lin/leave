package com.vian.leave.service.interfaces.assembler;


import com.vian.leave.service.domain.leave.entity.valueobject.Applicant;
import com.vian.leave.service.interfaces.dto.ApplicantDTO;

// 组装类
public class ApplicantAssembler {

    public static ApplicantDTO toDTO(Applicant applicant) {
        ApplicantDTO dto = new ApplicantDTO();
        dto.setPersonId(applicant.getPersonId());
        dto.setPersonName(applicant.getPersonName());
        return dto;
    }

    public static Applicant toDO(ApplicantDTO dto) {
        Applicant applicant = new Applicant();
        applicant.setPersonId(dto.getPersonId());
        applicant.setPersonName(dto.getPersonName());
        return applicant;
    }

}
