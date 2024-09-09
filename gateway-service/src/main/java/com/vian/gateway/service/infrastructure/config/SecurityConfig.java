package com.vian.gateway.service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author byh
 * @date 2023-09-14 11:33
 * @description
 */
@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                // 配置授权交换规则
                .authorizeExchange(authorize -> authorize
                        // 要求所有请求都经过身份验证
                        .anyExchange().authenticated()
                )
                // 启用OAuth2登录，并使用默认配置   实际上配置了一个 OAuth2LoginAuthenticationWebFilter，
                // 这个过滤器附加到过滤器链中，在需要时处理相关的OAuth2身份验证。
                .oauth2Login(withDefaults())
                // 启用OAuth2客户端功能
                .oauth2Client(withDefaults())
                // 启用登出功能，并使用默认配置
                .logout(withDefaults());
        // 构建并返回ServerHttpSecurity对象
        return http.build();
    }
}