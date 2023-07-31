package com.crcm.gateway;

import com.crcm.cloud.start.gateway.annotation.EnableProjectDynamicRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName CrcmGatewayApplication
 * @Description 
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/20
 **/
@SpringBootApplication
@EnableProjectDynamicRoute
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
