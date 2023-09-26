package com.vian.leave.service.interfaces.assembler;

import com.vian.leave.service.domain.leave.entity.valueobject.Approver;
import com.vian.leave.service.interfaces.dto.ApproverDTO;

// 组装类
public class ApproverAssembler {

    public static ApproverDTO toDTO(Approver approver){
        ApproverDTO dto = new ApproverDTO();
        dto.setPersonId(approver.getPersonId());
        dto.setPersonName(approver.getPersonName());
        return dto;
    }

    public static Approver toDO(ApproverDTO dto){
        Approver approver = new Approver();
        approver.setPersonId(dto.getPersonId());
        approver.setPersonName(dto.getPersonName());
        return approver;
    }

}
