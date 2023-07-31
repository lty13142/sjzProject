package com.crcm.admin;

import com.crcm.cloud.start.data.annatation.EnableProjectMybatis;
import com.crcm.cloud.start.feign.annotation.EnableProjectFeignClients;
import com.crcm.cloud.start.job.annation.EnableXxlJob;
import com.crcm.cloud.start.sso.annation.EnableCloudSSO;
import com.crcm.starter.cloud.swagger.annatation.EnableProjectSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
//import org.springframework.cloud.gateway.config.GatewayClassPathWarningAutoConfiguration;

/**
 * @ClassName UpmsServerApplication
 * @Description 通用用户权限管理
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/3
 **/
//@EnableProjectWebsocket
@EnableProjectFeignClients
@EnableDiscoveryClient
@EnableCloudSSO
@SpringBootApplication
@EnableProjectMybatis
@EnableXxlJob
@EnableProjectSwagger
@MapperScan("com.crcm.admin.mapper")
public class AdminServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }
}
