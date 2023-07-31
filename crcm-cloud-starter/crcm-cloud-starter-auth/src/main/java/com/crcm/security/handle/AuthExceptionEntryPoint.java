package com.crcm.security.handle;

import com.alibaba.fastjson.JSON;
import com.crcm.core.response.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AuthExceptionEntryPoint
 * @Description 异常打印器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    private DefaultWebResponseExceptionTranslator exceptionTranslator = new DefaultWebResponseExceptionTranslator();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Throwable cause = authException.getCause();
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            //解析异常
            ResponseEntity<?> result = exceptionTranslator.translate(authException);
            // 输出自己定义的返回类型
            writeException(response, result.getStatusCodeValue(), authException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeException(HttpServletResponse response,
                                Integer code, String message) throws IOException {
        // 返回指定格式的错误信息
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().print(JSON.toJSONString(RestResult.failed(code, message)));
        response.getWriter().flush();

    }
}
