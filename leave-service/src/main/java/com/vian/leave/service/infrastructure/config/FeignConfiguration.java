package com.vian.leave.service.infrastructure.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志记录等级：
 * ● NONE，无日志记录（默认）。
 * ● BASIC，仅记录请求方法、URL、响应状态代码和执行时间。
 * ● HEADERS，记录基本信息以及请求和响应头。
 * ● FULL，记录请求和响应的头、正文和元数据。
 *
 * @author: lin long fa
 * @date: 2023/12/19 14:10
 **/
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}