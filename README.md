## 请假微服务系统

### 架构设计

1. DDD微服务软件架构设计
2. 采用spring cloud alibaba微服务组件，nacos、openfeign、spring cloud gateway
3. 数据库:mysql
4. 持久化插件 mybatis-flex
5. 引入rbac权限控制，集成spring security和oauth2.1实现分布式认证授权
6. 前端预采用vue2进行开发

### 问题

#### sentinel

sentinel部署如果部署在服务器上，本地联调会有问题，这个跟sentinel的架构有关系
程序启动后，会注册到sentinel中，而后sentinel服务会访问注册的地址，进行流量等监控，因为本地地址是内网地址，故会出现服务拉取不到相关数据的问题

sentinel流控和熔断处理，blockHandler和fallback
blockHandler优先级高于fallback，即设置了blockHandler之后，设置fallback无效
但是fallback可用于处理特定异常的弥补程序

#### 关于对接knife4j

源码地址url:https://gitee.com/xiaoym/knife4j
官方说明文档：https://doc.xiaominfo.com/
该版本针对 springboot 3.0以上 jdk版本>=17
引入方式：
maven bom方式:

```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-dependencies</artifactId>
            <version>4.4.0-SNAPSHOT</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

```

注意：该依赖阿里云仓库上没有需要添加maven中心仓库

```
<repositories>
        <repository>
            <id>nexus-maven</id>
            <name>nexus-maven</name>
            <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
        </repository>
    </repositories>
    
```

微服务端加依赖：

```
<dependencies>
    <dependency>
        <groupId>com.github.xiaoymin</groupId>
        <artifactId>knife4j-openapi3-jakarta-spring-boot-starter</artifactId>
    </dependency>
</dependencies>

```

增加config:

```java
/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */
package com.vian.auth.service.infrastructure.config;

import cn.hutool.core.util.RandomUtil;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/***
 * 创建Swagger配置
 * @since:knife4j-springdoc-openapi-demo 1.0
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a> 
 * 2020/03/15 20:40
 */
@Configuration
public class SwaggerConfig {
    /**
     * 根据@Tag 上的排序，写入x-order
     *
     * @return the global open api customizer
     */
    @Bean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getTags() != null) {
                openApi.getTags().forEach(tag -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("x-order", RandomUtil.randomInt(0, 100));
                    tag.setExtensions(map);
                });
            }
//            if (openApi.getPaths() != null) {
//                openApi.addExtension("x-test123", "333");
//                openApi.getPaths().addExtension("x-abb", RandomUtil.randomInt(1, 100));
//            }
        };
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("认证服务API")
                        .version("1.0")
                        .description("Knife4j集成springdoc-openapi")
                        .termsOfService("http://doc.xiaominfo.com")
                        .license(new License().name("Apache 2.0")
                                .url("http://doc.xiaominfo.com")));
    }
}

```
网关集成通过Spring Cloud Gateway网关聚合
相关文件说明url:https://doc.xiaominfo.com/docs/middleware-sources/spring-cloud-gateway/spring-gateway-introduction
主要操作为：引入pom依赖，增加相关开启配置，可分为手工聚合或者服务发现聚合