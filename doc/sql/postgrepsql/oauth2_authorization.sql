
DROP TABLE IF EXISTS oauth2_authorization;
CREATE TABLE oauth2_authorization
(
    id                            varchar(100) NOT NULL,
    registered_client_id          varchar(100) NOT NULL,
    principal_name                varchar(200) NOT NULL,
    authorization_grant_type      varchar(100) NOT NULL,
    authorized_scopes             varchar(1000) DEFAULT NULL,
    attributes                    text,
    state                         varchar(500)  DEFAULT NULL,
    authorization_code_value      text,
    authorization_code_issued_at  timestamp(6),
    authorization_code_expires_at timestamp(6),
    authorization_code_metadata   text,
    access_token_value            text,
    access_token_issued_at        timestamp(6),
    access_token_expires_at       timestamp(6),
    access_token_metadata         text,
    access_token_type             varchar(100)  DEFAULT NULL,
    access_token_scopes           varchar(1000) DEFAULT NULL,
    oidc_id_token_value           text,
    oidc_id_token_issued_at       timestamp(6),
    oidc_id_token_expires_at      timestamp(6),
    oidc_id_token_metadata        text,
    refresh_token_value           text,
    refresh_token_issued_at       timestamp(6),
    refresh_token_expires_at      timestamp(6),
    refresh_token_metadata        text,
    user_code_value               text,
    user_code_issued_at           timestamp(6),
    user_code_expires_at          timestamp(6),
    user_code_metadata            text,
    device_code_value             text,
    device_code_issued_at         timestamp(6),
    device_code_expires_at        timestamp(6),
    device_code_metadata          text
)
;
ALTER TABLE oauth2_authorization ADD CONSTRAINT oauth2_authorization_pkey PRIMARY KEY (id);
