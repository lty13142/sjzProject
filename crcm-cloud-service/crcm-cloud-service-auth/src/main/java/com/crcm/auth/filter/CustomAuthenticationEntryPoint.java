package com.crcm.auth.filter;

import com.alibaba.fastjson.JSON;
import com.crcm.core.response.RestResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CustomAuthenticationEntryPoint
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/26
 **/
@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        RestResult r = RestResult.failed(HttpStatus.UNAUTHORIZED.value(), "client_id或client_secret错误");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().print(JSON.toJSONString(r));
        response.getWriter().flush();
    }
}