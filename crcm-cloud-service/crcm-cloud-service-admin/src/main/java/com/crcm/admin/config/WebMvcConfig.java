package com.crcm.admin.config;

import com.crcm.license.interceptor.LicenseCheckFilter;
import com.crcm.license.interceptor.LicenseCheckInterceptor;
import com.crcm.license.properties.LicenseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

/**
 * @author Tianyu
 * @date 2023/6/7 16:21
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LicenseProperties licenseProperties;

    /**
     * 添加授权过滤器
     * @return
     * @throws IOException
     */
    @Bean
    public FilterRegistrationBean<LicenseCheckFilter> myFilterRegistration() throws IOException {
        FilterRegistrationBean<LicenseCheckFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LicenseCheckFilter(licenseProperties));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

}
