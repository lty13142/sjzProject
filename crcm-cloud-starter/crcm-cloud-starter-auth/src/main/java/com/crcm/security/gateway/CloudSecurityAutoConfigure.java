package com.crcm.security.gateway;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName CloudSecurityAutoConfigure
 * @Description 网关请求校验自动配置
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/10
 **/
@EnableConfigurationProperties(CloudSecurityProperties.class)
public class CloudSecurityAutoConfigure {
    @Bean
    public CloudSecurityInterceptorConfigure cloudSecurityInterceptorConfigure() {
        return new CloudSecurityInterceptorConfigure();
    }

}
