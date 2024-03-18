package com.vian.auth.service.application.service.auth;

import com.vian.auth.service.domain.user.entity.RegisteredClientEntity;
import com.vian.auth.service.domain.user.service.register.RegisteredClientDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.ClientAuthorizationException;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @ClassName: RegisteredClientApplicationService
 * @Author: lin long fa
 * @Date: 2024-03-16 18:44:16
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
//@Component
@RequiredArgsConstructor
public class RegisteredClientApplicationService implements RegisteredClientRepository {

    private static final String CLIENT_ID_NOT_EXIST_ERROR_CODE = "client not exist";

    private static final String ZONED_DATETIME_ZONE_ID = "Asia/Shanghai";

    private final RegisteredClientDomainService registeredClientDomainService;

    @Override
    public void save(RegisteredClient registeredClient) {
        RegisteredClientEntity registeredClientEntity = new RegisteredClientEntity();
        registeredClientEntity.setClientId(registeredClient.getClientId());
        registeredClientEntity.setClientName(registeredClient.getClientName());
        registeredClientEntity.setClientSecret(registeredClient.getClientSecret());
        if (registeredClient.getClientIdIssuedAt() != null) {
            registeredClientEntity.setClientIdIssuedAt(registeredClient.getClientIdIssuedAt().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime());
        }
        if (registeredClient.getClientSecretExpiresAt() != null) {
            registeredClientEntity.setClientSecretExpiresAt(registeredClient.getClientSecretExpiresAt().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime());
        }
        registeredClientEntity.setClientAuthenticationMethods(registeredClient.getClientAuthenticationMethods().stream().map(ClientAuthenticationMethod::getValue).collect(Collectors.toSet()));
        registeredClientEntity.setAuthorizationGrantTypes(registeredClient.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::getValue).collect(Collectors.toSet()));
        registeredClientEntity.setRedirectUris(registeredClient.getRedirectUris());
        registeredClientEntity.setPostLogoutRedirectUris(registeredClient.getPostLogoutRedirectUris());
        registeredClientEntity.setScopes(registeredClient.getScopes());
        registeredClientEntity.setTokenSettings(registeredClient.getTokenSettings().getSettings());
        registeredClientEntity.setClientSettings(registeredClient.getClientSettings().getSettings());
        registeredClientDomainService.saveClient(registeredClientEntity);
    }

    @Override
    public RegisteredClient findById(String id) {
        RegisteredClientEntity registeredClientEntity = registeredClientDomainService.getOneById(id);
        if (registeredClientEntity == null) {
            throw new ClientAuthorizationException(new OAuth2Error(CLIENT_ID_NOT_EXIST_ERROR_CODE,
                    "Authorization client table data id not exist: " + id, null),
                    id);
        }
        return sysRegisteredClientDetailConvert(registeredClientEntity);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        RegisteredClientEntity registeredClientEntity = registeredClientDomainService.getOneByClientId(clientId);
        if (registeredClientEntity == null) {
            throw new ClientAuthorizationException(new OAuth2Error(CLIENT_ID_NOT_EXIST_ERROR_CODE,
                    "Authorization client id not exist: " + clientId, null),
                    clientId);
        }
        return sysRegisteredClientDetailConvert(registeredClientEntity);
    }

    /**
     * sysRegisteredClientDetailVo 转换为 RegisteredClient
     *
     * @param registeredClientEntity
     * @return
     */
    private RegisteredClient sysRegisteredClientDetailConvert(RegisteredClientEntity registeredClientEntity) {
        RegisteredClient.Builder builder = RegisteredClient
                .withId(registeredClientEntity.getId())
                .clientId(registeredClientEntity.getClientId())
                .clientSecret(registeredClientEntity.getClientSecret())
                .clientIdIssuedAt(Optional.ofNullable(registeredClientEntity.getClientIdIssuedAt())
                        .map(d -> d.atZone(ZoneId.of(ZONED_DATETIME_ZONE_ID)).toInstant())
                        .orElse(null))
                .clientSecretExpiresAt(Optional.ofNullable(registeredClientEntity.getClientSecretExpiresAt())
                        .map(d -> d.atZone(ZoneId.of(ZONED_DATETIME_ZONE_ID)).toInstant())
                        .orElse(null))
                .clientName(registeredClientEntity.getClientName())
                .clientAuthenticationMethods(c ->
                        c.addAll(registeredClientEntity.getClientAuthenticationMethods()
                                .stream().map(ClientAuthenticationMethod::new).collect(Collectors.toSet()))
                ).authorizationGrantTypes(a ->
                        a.addAll(registeredClientEntity.getAuthorizationGrantTypes()
                                .stream().map(AuthorizationGrantType::new).collect(Collectors.toSet()))
                ).redirectUris(r -> r.addAll(registeredClientEntity.getRedirectUris()))
                .postLogoutRedirectUris(p -> p.addAll(registeredClientEntity.getPostLogoutRedirectUris()))
                .scopes(s -> s.addAll(registeredClientEntity.getScopes()))
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build());// requireAuthorizationConsent(true) 不设置 授权页不会显示
//                .tokenSettings(TokenSettings.builder().build());
        //todo clientSettings和 tokenSettings 根据需要后续自行修改
//                .clientSettings(ClientSettings.withSettings(sysRegisteredClientDetailVo.getClientSettings()).build());
        return builder.build();

    }
}
