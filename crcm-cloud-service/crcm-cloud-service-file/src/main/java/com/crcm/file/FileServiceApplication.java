package com.crcm.file;

import com.crcm.cloud.start.data.annatation.EnableProjectMybatis;
import com.crcm.cloud.start.feign.annotation.EnableProjectFeignClients;
import com.crcm.cloud.start.sso.annation.EnableCloudSSO;
import com.crcm.starter.cloud.swagger.annatation.EnableProjectSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName CrcmFileServiceApplication
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/21
 **/
@EnableProjectSwagger
@EnableProjectMybatis
@EnableProjectFeignClients
@EnableCloudSSO
@MapperScan("com.crcm.file.mapper")
@SpringBootApplication
public class FileServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileServiceApplication.class,args);
    }
}
