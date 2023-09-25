package com.vian.leave.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 系统服务启动类
 * @ClassName: SystemApplication
 * @Author: lin long fa
 * @Date: 2023-09-25 14:43:12
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
