package com.vian.gateway.service.infrastructure.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.fastjson.JSON;
import com.vian.gateway.service.infrastructure.common.api.Response;
import reactor.core.publisher.Mono;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Configuration
public class MySCGConfiguration {

    @Bean
    public BlockRequestHandler blockRequestHandler() {
        Response resultData = Response.failed("网关服务限流保护,请稍后再试!");
        String resultString = JSON.toJSONString(resultData);
        return (exchange, t) -> ServerResponse.status(444).contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(resultString));
    }

}