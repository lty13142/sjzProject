package com.crcm.develop.core.conf;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

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
@Order(0)
@Component
public class CorsFilter implements Filter {
    public CorsFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (req instanceof HttpServletRequest) {
            HttpServletResponse response = (HttpServletResponse) res;
            HttpServletRequest request = (HttpServletRequest) req;
            String origin = ((HttpServletRequest) req).getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,OPTIONS");//允许跨域的请求方式
            response.setHeader("Access-Control-Max-Age", "3600");//预检请求的间隔时间
            response.setHeader("Access-Control-Allow-Headers", "Authorization,Origin,Access-Token,X-Requested-With,Content-Type, Accept,token,Content-Disposition");//允许跨域请求携带的请求头
            response.setHeader("Access-Control-Allow-Credentials", "true");//若要返回cookie、携带seesion等信息则将此项设置我true

            response.setHeader("strict-transport-security", "max-age=16070400; includeSubDomains");//简称为HSTS。它允许一个HTTPS网站，要求浏览器总是通过HTTPS来访问它
//            response.setHeader("Content-Security-Policy", "default-src 'self'; script-src 'self' blob:; frame-ancestors 'self'; object-src 'none'; style-src 'self' 'unsafe-inline'; img-src 'self' data:;font-src 'self' data:;");//这个响应头主要是用来定义页面可以加载哪些资源，减少XSS的发生
            response.setHeader("X-Content-Type-Options", "nosniff");//互联网上的资源有各种类型，通常浏览器会根据响应头的Content-Type字段来分辨它们的类型。通过这个响应头可以禁用浏览器的类型猜测行为
            response.setHeader("X-XSS-Protection", "1; mode=block");//1; mode=block：启用XSS保护，并在检查到XSS攻击时，停止渲染页面
            response.setHeader("X-Frame-Options", "SAMEORIGIN");//SAMEORIGIN：不允许被本域以外的页面嵌入

            if (request.getMethod() == HttpMethod.OPTIONS.name()) {
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