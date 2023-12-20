package com.vian.gateway.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: TODO
 * @ClassName: GatewayApplication
 * @Author: lin long fa
 * @Date: 2023-12-19 16:04:50
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
