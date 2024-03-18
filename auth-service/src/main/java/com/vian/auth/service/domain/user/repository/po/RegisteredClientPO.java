package com.vian.auth.service.domain.user.repository.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.mybatisflex.core.handler.GsonTypeHandler;
import com.mybatisflex.core.handler.JacksonTypeHandler;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.util.Map;
import java.util.Set;

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

    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    @Schema(description = "")
    private String id;

    @Schema(description = "")
    private String clientId;

    @Schema(description = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime clientIdIssuedAt;

    @Schema(description = "")
    private String clientSecret;

    @Schema(description = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime clientSecretExpiresAt;

    @Schema(description = "")
    private String clientName;

    @Column(typeHandler = GsonTypeHandler.class)
    private Set<String> clientAuthenticationMethods;

    @Column(typeHandler = GsonTypeHandler.class)
    private Set<String> authorizationGrantTypes;

    @Column(typeHandler = GsonTypeHandler.class)
    private Set<String> redirectUris;

    @Column(typeHandler = GsonTypeHandler.class)
    private Set<String> postLogoutRedirectUris;

    @Column(typeHandler = GsonTypeHandler.class)
    private Set<String> scopes;

    @Column(typeHandler = GsonTypeHandler.class)
    private Map<String, Object> clientSettings;

    @Column(typeHandler = GsonTypeHandler.class)
    private Map<String, Object> tokenSettings;

}
