package com.vian.leave.service.infrastructure.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Data
public class Setting {
    @Value("${setting.upload-addr}")
    private String uploadAddr;
    @Value("${setting.path}")
    private String path;
}