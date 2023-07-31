package com.crcm.cloud.start.feign.ext;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @ClassName RequestInterceptorConfig
 * @Description 请求拦截器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/19
 **/
@Configuration
public class RequestInterceptorConfig implements RequestInterceptor {

    /**
     * 微服务调用时请求携带请求头
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        // 传递token
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            //将请求头信息放入header中
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    // 去除Content-Length，避免一些奇怪的错误
                    if (StringUtils.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH,name) ){
                        continue;
                    }
                    template.header(name, values);
                }
            }
        }
    }

    /**
     * 将请求所有的请求头传入feign
     *
     * @return
     */
    @Bean
    public RequestInterceptor headerInterceptor() {
        return template -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (null != attributes) {
                HttpServletRequest request = attributes.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        // 去除Content-Length，避免一些奇怪的错误
                        if (StringUtils.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH,name) ){
                            continue;
                        }
                        template.header(name, values);
                    }
                }
            }
        };
    }
}
