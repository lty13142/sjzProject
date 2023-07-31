package com.crcm.cloud.start.data.tenant;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FeignTenantConfiguration
 * @Description feign多租户配置
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/12/3
 **/
@Configuration
public class FeignTenantConfiguration {

    @Bean
    public RequestInterceptor uimpFeignTenantInterceptor() {
        return new FeignTenantInterceptor();
    }
}
