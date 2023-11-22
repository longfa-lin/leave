package com.vian.leave.service.interfaces.dto;

import lombok.Data;

@Data
public class PersonDTO {

    private Long personId;
    private String personName;
    private Long roleId;
    private String personType;
    private String createTime;
    private String lastModifyTime;
    private String status;
}
