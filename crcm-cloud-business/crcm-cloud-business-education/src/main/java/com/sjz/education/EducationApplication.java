package com.sjz.education;


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
@SpringBootApplication(scanBasePackages = {"com.sjz.education"})
@EnableProjectMybatis
@EnableProjectSwagger
@MapperScan("com.sjz.education.mapper")
public class EducationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationApplication.class, args);
    }

}
