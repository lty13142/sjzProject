package com.crcm.auth;

import com.crcm.cloud.start.feign.annotation.EnableProjectFeignClients;
import com.crcm.cloud.start.data.annatation.EnableProjectMybatis;
import com.crcm.starter.cloud.swagger.annatation.EnableProjectSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName AuthApplication
 * @Description 
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/19
 **/
@MapperScan("com.crcm.auth.mapper")
@EnableProjectMybatis
@EnableProjectFeignClients
@EnableProjectSwagger
@SpringBootApplication
@EnableDiscoveryClient
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
