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