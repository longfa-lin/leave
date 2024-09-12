package com.vian.gateway.service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author byh
 * @date 2023-09-14 11:33
 * @description
 */
@Configuration
@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
// 该注解用于启用 Spring Security 在反应式应用中的方法级别安全性。它允许你在使用 Spring WebFlux 框架时，通过注解来保护你的服务和方法
// @PreAuthorize：在方法调用之前执行表达式，决定是否允许访问。例如，@PreAuthorize("hasRole('USER')")。
// @PostAuthorize：在方法调用之后执行表达式，并根据返回结果决定是否允许访问。例如，@PostAuthorize("returnObject.owner == authentication.name")。
// @PreFilter 和 @PostFilter：在方法调用之前或之后过滤集合或数组。
// @Secured：指定哪些角色可以访问该方法。
// @RolesAllowed：指定权限的别名，功能类似于 @Secured
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
//                .oauth2Client(withDefaults())
                // 启用登出功能，并使用默认配置
                .logout(withDefaults());
        // 配置跨域策略，例如允许所有跨域访问
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // 为了调试问题，可以配置详细的日志记录（可选）
        http.securityContextRepository(new WebSessionServerSecurityContextRepository());

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:9092"); // 根据具体需求进行配置
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}