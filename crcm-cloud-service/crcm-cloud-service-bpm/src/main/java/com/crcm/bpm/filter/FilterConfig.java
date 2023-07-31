package com.crcm.bpm.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * url过滤器配置类
 *
 * @author zzyt
 */
@Configuration
public class FilterConfig {

    /**
     * 注入filter类，否则外部服务feignAdminService永远是空
     */
    @Bean
    public UrlFilter sysInterceptor() {
        return new UrlFilter();
    }

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(this.sysInterceptor());
        registration.addUrlPatterns("/*");
        registration.setName("UrlFilter");
        registration.setOrder(1);
        return registration;
    }

}
