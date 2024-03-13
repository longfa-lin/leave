package com.vian.auth.service.domain.user.repository.po;

import com.mybatisflex.annotation.Id;
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
 * oauth2 客户端注册登记 实体类。
 *
 * @author linlo
 * @since 2024-03-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "oauth2 客户端注册登记")
@Table(value = "sys_registered_client")
public class RegisteredClientPO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Schema(description = "")
    private String id;

    @Schema(description = "")
    private String clientId;

    @Schema(description = "")
    private LocalDateTime clientIdIssuedAt;

    @Schema(description = "")
    private String clientSecret;

    @Schema(description = "")
    private LocalDateTime clientSecretExpiresAt;

    @Schema(description = "")
    private String clientName;

    @Schema(description = "")
    private String clientAuthenticationMethods;

    @Schema(description = "")
    private String authorizationGrantTypes;

    @Schema(description = "")
    private String redirectUris;

    @Schema(description = "")
    private String scopes;

    @Schema(description = "")
    private String clientSettings;

    @Schema(description = "")
    private String tokenSettings;

    @Schema(description = "")
    private String postLogoutRedirectUris;

}
