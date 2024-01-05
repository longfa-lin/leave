package com.vian.gateway.service.infrastructure.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.vian.gateway.service.infrastructure.common.exception.CustomGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @Description: TODO
 * @ClassName: GatewayConfiguration
 * @Author: lin long fa
 * @Date: 2024-01-05 18:03:41
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Configuration
public class GatewayConfiguration {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * 注入自定义网关异常
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CustomGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        // Register the custom block exception handler .
        return new CustomGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }
}
