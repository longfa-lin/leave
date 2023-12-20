package com.vian.gateway.service.infrastructure.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RequestLoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestLoggingGatewayFilterFactory.Config> {

    public RequestLoggingGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            log.info("Request Filter - Method: {}, URI: {}, Headers: {}", request.getMethod(), request.getURI(), request.getHeaders());
            return chain.filter(exchange);
        };
    }

    public static class Config {
        // 可选的配置项
    }
}
