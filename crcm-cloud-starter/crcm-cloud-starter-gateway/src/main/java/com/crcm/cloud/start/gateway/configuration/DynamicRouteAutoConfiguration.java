package com.crcm.cloud.start.gateway.configuration;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.config.PropertiesRouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DynamicRouteAutoConfiguration
 * @Description 动态路由配置类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/8
 **/
@Configuration
@ComponentScan("com.crcm.cloud.start.gateway")
public class DynamicRouteAutoConfiguration {
    /**
     * 配置文件设置为空
     * redis 加载为准
     *
     * @return
     */
    @Bean
    public PropertiesRouteDefinitionLocator propertiesRouteDefinitionLocator() {
        return new PropertiesRouteDefinitionLocator(new GatewayProperties());
    }
}
