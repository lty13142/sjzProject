package com.crcm.security.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName CloudSecurityInterceptorConfigure
 * @Description 微服务网关请求校验拦截器配置
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/10
 **/
public class CloudSecurityInterceptorConfigure implements WebMvcConfigurer {
    private CloudSecurityProperties properties;

    @Autowired
    public void setProperties(CloudSecurityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public HandlerInterceptor serverProtectInterceptor() {
        ServerProtectInterceptor interceptor = new ServerProtectInterceptor();
        interceptor.setProperties(properties);
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverProtectInterceptor());
    }
}
