package com.vian.leave.service.infrastructure.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @ClassName: RabbitMQProperties
 * @Author: lin long fa
 * @Date: 2024-08-29 14:21:00
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
public class RabbitMQProperties {
    private String host;
    private int port;
    private String username;
    private String password;
}
