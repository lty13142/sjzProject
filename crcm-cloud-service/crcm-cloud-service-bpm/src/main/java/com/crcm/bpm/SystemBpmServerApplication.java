package com.crcm.bpm;

import com.crcm.cloud.start.feign.annotation.EnableProjectFeignClients;
import com.crcm.cloud.start.resource.annatation.EnableProjectResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableProjectSwagger2
@SpringCloudApplication
@EnableProjectFeignClients
@EnableProjectResourceServer(details = true)
@EnableTransactionManagement(proxyTargetClass = true)
public class SystemBpmServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemBpmServerApplication.class, args);
    }

}
