package com.crcm.cloud.start.sso.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CorsFilter
 * @Description 跨域处理过滤器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author Administrator
 * @Date 2020/2020/8/25/10:34
 **/
@ConditionalOnMissingBean(name = "WebCorsFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (req instanceof HttpServletRequest) {
            HttpServletResponse response = (HttpServletResponse) res;
            HttpServletRequest request = (HttpServletRequest) req;

            String origin = ((HttpServletRequest) req).getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", origin);
            //允许跨域的请求方式
            response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH,OPTIONS");
            //预检请求的间隔时间
            response.setHeader("Access-Control-Max-Age", "3600");
            //允许跨域请求携带的请求头
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, content-Type, Accept, Authorization,*");
            //若要返回cookie、携带seesion等信息则将此项设置为true
            response.setHeader("Access-Control-Allow-Credentials", "true");
            if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
                response.setStatus(HttpStatus.OK.value());
                return;
            }
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}