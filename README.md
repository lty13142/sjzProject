<<<<<<< HEAD
# 宋家镇数字乡村后端框架

德州市陵城区宋家镇数字乡村后端框架
=======


<h1>crcm-cloud微服务后端框架使用说明</h1><br/>

**<h3>一、框架结构：**</h3><br/>
```
├─ crcm-cloud                                   // 微服务父模块
│  ├─ crcm-cloud-visual                            // 可视化模块，监控
        ├─ crcm-cloud-monitor                // 监控服务
        └─ crcm-cloud-xxl-job-admin                // xxl-job分布式任务控制面板
│  ├─ crcm-cloud-business                       // 业务模块
        ├─ crcm-cloud-business-order                // 示例订单服务
        └─ crcm-cloud-business-product              // 示例产品服务
   ├─ crcm-cloud-business-api                   // 业务模块API，用于互相调用
   ├─ crcm-cloud-starter                         // 公共整合模块，整合技术中间件等，用于有需要的服务使用
        ├─ crcm-cloud-bom                          // maven版本管理模块，框架的子module可以在这里面进行统一的版本管理，之后引入该module可以不写版本号
        ├─ crcm-cloud-starter-core                  // 核心模块，包含常量、工具类、必要得依赖
        ├─ crcm-cloud-starter-feign                 // 封装openFeign,服务调用配置
        ├─ crcm-cloud-starter-file                  // 封装minio工具，用于直接操作文件上传下载
        ├─ crcm-cloud-starter-gateway               // 封装gateway网关
        ├─ crcm-cloud-starter-log                   // 封装日志（未完成，现在暂时调用admin服务进行存储）
        ├─ crcm-cloud-starter-mq                    // 封装消息队列（未完成）
        ├─ crcm-cloud-starter-data               // 封装mybatis+mysql模块，用于数据库操作
        ├─ crcm-cloud-starter-office                // 封装文档操作模块，用于Word、Excel的导入导出
        ├─ crcm-cloud-starter-redis                 // 封装缓存redis
        ├─ crcm-cloud-starter-ribbon                // 封装ribbon负载均衡（待完善）
        ├─ crcm-cloud-starter-auth              // 封装权限配置模块
        ├─ crcm-cloud-starter-auth-resource     // 封装资源服务器权限，用户服务之间的鉴权（服务和业务模块使用）
        ├─ crcm-cloud-starter-swagger               // 封装swagger文档（待优化）
        ├─ crcm-cloud-starter-job                  // 封装任务调度模块（待优化）
        ├─ crcm-cloud-starter-websocket             // 封装websocket模块（待简化）
        └─ crcm-cloud-common-tenant                // 多租户模块（待开发）
   ├─ crcm-cloud-develop                        // 代码开发模块，用于代码生成
   ├─ crcm-cloud-gateway                        // 网关路由
   ├─ crcm-cloud-register                       // nacos注册中心（实际部署时可以直接用nacos镜像），采用源码运行方式，配置mysql存储，SQL文件在db目录下
   ├─ crcm-cloud-service                        // 框架服务模块，包括admin管理、auth授权、file文件服务等
        ├─ crcm-cloud-service-admin                         // 微服务管理模块，管理用户、角色、组织、微服务配置等（待完善）
        ├─ crcm-cloud-service-auth                          // 微服务认证中心，用于登陆、鉴权
        └─ crcm-cloud-service-file                          // 微服务文件传输服务
   ├─ crcm-cloud-service-api                    // 框架服务模块API，用于互相调用
        ├─ crcm-cloud-service-admin-api                     // 微服务管理模块接口
        ├─ crcm-cloud-service-auth-api                      // 微服务认证中心接口
        └─ crcm-cloud-service-file-api                      // 微服务文件传输服务接口 
   ├─ db                                        // 数据库文件
   ├─ doc                                       // 文档
   └─ docker                                    // docker部署，暂未完成


```


<h3>二、框架启动顺序：</h3><br/>
    nacos(register) -> gateway -> service-auth -> service-admin -> service-file ....<br/>

<h3>三、权限集成</h3><br/>
<h4>权限集成有两种方式：</h4><br/>
<h5>1.服务与授权中心使用同一个redis</h5><br/>
``` 
        在服务pom引入  
        <dependency>
            <groupId>com.crcm</groupId>
            <artifactId>crcm-cloud-starter-auth-resource</artifactId>
        </dependency>
        
        在application.yml里面配置
        security:
          enable: true
          #是否限制服务只能通过网关访问
          gateway-fetch-only: true
          oauth2:
            client-id: crcm-cloud
            client-secret: crcm-cloud
            token-store:
              type: redis-custom
            ignore:
              urls:
                - /open/**
            resource:
              id: admin
              
              
        在项目启动类上面添加注解：@EnableProjectResourceServer
              
          参考 crcm-cloud-service-admin
```
<h5>2.服务与授权中心相互独立</h5><br/>

```
    pom中引入
     <!--oauth整合包-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-oauth2</artifactId>
        </dependency>
        
    在application.yml中配置认证属性
    
    security:
      oauth2:
        client:
          #授权服务器获取token url
          access-token-uri: http://${AUTH_HOST:crcm-cloud-auth}:8868/oauth/token
          #授权服务器调取授权URL
          user-authorization-uri: http://${AUTH_HOST:crcm-cloud-auth}:8868/oauth/authorize
          #客户端ID
          client-id: bis-order
          #客户端密码
          client-secret: secret
          #客户端身份验证方案 表单
          client-authentication-scheme: form
          #认证方式：授权码模式
          grant-type: authorization_code
          #登录成功后跳转的地址，需要认证中心能够访问到
          registered-redirect-uri: http://localhost:8853/login
        resource:
          #授权服务器验证token URL
          token-info-uri: http://${AUTH_HOST:crcm-cloud-auth}:8868/oauth/check_token
        #授权服务器登出URL
        login-out-url: http://${AUTH_HOST:crcm-cloud-auth}:8868/token/logout
        
        
        在项目中添加 WebSecurityConfig、SsoLogoutSuccessHandler，可参考crcm-cloud-business-order项目
```

<h3>四、host配置</h3>
微服务中数据库、redis、文件服务等地址建议使用host方式配置，便于动态切换服务地址<br/>

建议使用SwitchHosts.exe工具动态切换host<br/>

服务host配置，需要改成自己的ip和服务ip<br/>

```
# crcm-cloud
10.35.11.50 server-host
10.35.11.50 crcm-cloud-auth
10.150.1.18	crcm-cloud-mysql
127.0.0.1	crcm-cloud-redis
127.0.0.1	crcm-cloud-gateway
127.0.0.1	crcm-cloud-register
127.0.0.1	crcm-cloud-sentinel
127.0.0.1	crcm-cloud-monitor
127.0.0.1	crcm-upms-server
```
>>>>>>> 3b1e7e6 (test)
