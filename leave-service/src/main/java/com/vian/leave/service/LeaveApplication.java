package com.vian.leave.service;

import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.audit.ConsoleMessageCollector;
import com.mybatisflex.core.audit.MessageCollector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Description: 请假微服务启动类
 * @ClassName: LeaveApplication
 * @Author: lin long fa
 * @Date: 2023-09-26 16:13:04
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.vian.leave.service.domain.**.repository.mapper")
public class LeaveApplication implements CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {
    public static void main(String[] args) {
        SpringApplication.run(LeaveApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("onApplicationEvent");
        //开启审计功能
        AuditManager.setAuditEnable(true);

        //设置 SQL 审计收集器
        MessageCollector collector = new ConsoleMessageCollector();
        AuditManager.setMessageCollector(collector);
    }
}
