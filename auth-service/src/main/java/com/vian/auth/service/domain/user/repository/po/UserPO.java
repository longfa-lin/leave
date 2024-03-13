package com.vian.auth.service.domain.user.repository.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;

/**
 * 用户表 实体类。
 *
 * @author linlo
 * @since 2024-03-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户表")
@Table(value = "sys_user")
public class UserPO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id(keyType = KeyType.Auto)
    @Schema(description = "id")
    private Long id;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String name;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 手机号(未加密)
     */
    @Schema(description = "手机号(未加密)")
    private String phone;

    /**
     * 手机号(加密)
     */
    @Schema(description = "手机号(加密)")
    private String mobile;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 账号状态(0:无效；1:有效)
     */
    @Schema(description = "账号状态(0:无效；1:有效)")
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime modifiedTime;

}
