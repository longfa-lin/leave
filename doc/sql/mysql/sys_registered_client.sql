CREATE TABLE `sys_registered_client`
(
    `id`                            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `client_id`                     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `client_id_issued_at`           timestamp(0)                                                   NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `client_secret`                 varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL,
    `client_secret_expires_at`      timestamp(0)                                                   NULL     DEFAULT NULL,
    `client_name`                   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `client_authentication_methods` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `authorization_grant_types`     varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `redirect_uris`                 varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `post_logout_redirect_uris`     varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `scopes`                        varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `client_settings`               varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `token_settings`                varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_registered_client
-- ----------------------------
INSERT INTO `sys_registered_client`
VALUES ('1702591381795115010', 'device-messaging-client', '2024-03-19 12:49:51', NULL, NULL,
        'a8513cd1-ad98-4817-9c60-58e7712af873', '[\"none\"]',
        '[\"refresh_token\", \"urn:ietf:params:oauth:grant-type:device_code\"]', '', '',
        '[\"message.read\", \"message.write\"]',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');
INSERT INTO `sys_registered_client`
VALUES ('1703682313609162754', 'messaging-client', '2024-03-19 12:49:51',
        '{bcrypt}$2a$10$U6e5ChyXSWL7Js7hs0GWlew5BMdjzhlB6Kwx7gK2fXgU9skSe8kEq', NULL,
        '9069244b-ca7c-4788-93f1-64f23e0b2250', '[\"client_secret_basic\"]',
        '[\"refresh_token\", \"client_credentials\", \"password\", \"authorization_code\"]',
        '[\"http://127.0.0.1:8080/authorized\", \"http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc\"]',
        '[\"http://127.0.0.1:8080/logged-out\"]', '[\"openid\", \"profile\", \"message.read\", \"message.write\"]',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');


