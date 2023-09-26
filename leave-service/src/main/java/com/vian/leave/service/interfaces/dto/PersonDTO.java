package com.vian.leave.service.interfaces.dto;

import lombok.Data;

@Data
public class PersonDTO {

    String personId;
    String personName;
    String roleId;
    String personType;
    String createTime;
    String lastModifyTime;
    String status;
}
