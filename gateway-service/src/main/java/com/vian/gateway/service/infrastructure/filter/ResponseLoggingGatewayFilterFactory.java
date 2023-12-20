package com.vian.gateway.service.infrastructure.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResponseLoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<ResponseLoggingGatewayFilterFactory.Config> {

    public ResponseLoggingGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> chain.filter(exchange)
                .doFinally(signalType -> {
                    ServerHttpRequest request = exchange.getRequest();
                    ServerHttpResponse response = exchange.getResponse();
                    log.info("Response Filter - Method: {}, URI: {}, Status code: {}, Headers: {}", request.getMethod(), request.getURI(), response.getStatusCode(), response.getHeaders());
                });
    }

    public static class Config {
        // 可选的配置项
    }
}
