package com.sjz.evaluations;


import com.crcm.cloud.start.data.annatation.EnableProjectMybatis;
import com.crcm.cloud.start.feign.annotation.EnableProjectFeignClients;
import com.crcm.cloud.start.sso.annation.EnableCloudSSO;
import com.crcm.starter.cloud.swagger.annatation.EnableProjectSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableProjectFeignClients
@EnableDiscoveryClient
@EnableCloudSSO
@SpringBootApplication
@EnableProjectMybatis
@EnableProjectSwagger
@MapperScan("com.sjz.evaluations.mapper")
public class EvaluationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvaluationsApplication.class, args);
    }

}
