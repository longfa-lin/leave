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

sentinel window 10 本地以后台服务方式运行相关操作：
```
下载NSSM：访问 NSSM https://nssm.cc/ 下载适合您Windows版本的NSSM。
安装服务：打开命令提示符（以管理员身份），使用NSSM安装您的Java应用程序作为服务：
nssm install YourServiceName "C:\Path\To\Java\bin\java.exe" "-jar C:\Path\To\Your\app.jar"
替换 YourServiceName 为您想要的服务名称，C:\Path\To\Java\bin\java.exe 为Java可执行文件的路径，C:\Path\To\Your\app.jar 为您的jar文件的路径。
nssm start YourServiceName ==> 启动服务
nssm status YourServiceName ==>  查看状态
nssm stop YourServiceName  ==> 停止服务
管理服务：您可以使用 services.msc 来管理服务，或者使用NSSM的命令来停止、重启服务。
```
sentinel流控和熔断处理，blockHandler和fallback
blockHandler优先级高于fallback，即设置了blockHandler之后，设置fallback无效
但是fallback可用于处理特定异常的弥补程序

#### gateway-sentinel-nacos实现网关限流
##### 注意事项：
1. 在nacos配置sentinel时，rule-type:网关限流为-gw-flow  api限流为 gw-api-group,具体可见com.alibaba.cloud.sentinel.datasource下的RuleType.class

#### 关于对接knife4j
##### 对接
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
/**
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
##### 注意事项
1. The OpenAPI 3 specification does not allow explicitly adding Authorization header. 
Note: Header parameters named Accept, Content-Type and Authorization are not allowed. To describe these headers
2. 
#### spring注解比较
##### @ParameterObject 对比 @ModelAttribute
```text
@ParameterObject 是 Spring 的一个组合注解，它在内部使用了 @ModelAttribute 或 @RequestParam，具体取决于上下文。这个注解的目的是为了减少样板代码，提高代码的可读性。当用在方法参数上时，
它可以使得控制器的处理方法更加简洁，因为你不需要显式地标记参数是来自模型属性还是请求参数。
@ModelAttribute 是一个更早期的注解，它明确地告诉 Spring 框架，方法参数应该是通过请求参数绑定到命名模型对象的属性上的。这通常用于处理表单提交的数据，或者是当你需要将请求参数绑定到一个对象时。
从功能上讲，@ParameterObject 并不优于 @ModelAttribute，因为它只是一个便捷的方式，让你不必显式声明 @ModelAttribute 或 @RequestParam。它的优势在于它可以使得你的控制器代码更加整洁和易于阅读。
在实际使用中，选择哪一个注解通常取决于个人或团队的偏好，以及项目中已经建立的编码标准。如果你更倾向于显式清晰，可能会选择 @ModelAttribute。如果你更倾向于简洁的代码，可能会选择 @ParameterObject。
重要的是保持代码的一致性和可维护性。
在 Spring Boot 2.2 及以后版本中，由于 @ParameterObject 的引入，它被视为一种简化控制器方法签名的首选方式，特别是当你有多个参数需要从请求中提取并封装到一个对象时。
但是，在这之前的版本中或者在不支持 @ParameterObject 的情况下，你仍然需要使用 @ModelAttribute。
```