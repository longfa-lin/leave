package com.vian.leave.service.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "login-person-dto")
public class PersonDTO {
    @Schema(description = "用户ID", defaultValue = "1742843304132931585L")
    private Long personId;
    @Schema(description = "用户名", defaultValue = "vian")
    private String personName;
    @Schema(description = "角色ID", defaultValue = "1742843304132931585L")
    private Long roleId;
    @Schema(description = "用户类型", defaultValue = "INTERNAL")
    private String personType;
    @Schema(description = "创建时间", defaultValue = "2024-01-01 00:00:00")
    private String createTime;
    @Schema(description = "最后修改时间", defaultValue = "2024-01-01 23:44:21")
    private String lastModifyTime;
    @Schema(description = "状态", defaultValue = "ENABLE")
    private String status;
}
