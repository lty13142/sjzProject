package com.crcm.security.handle;

import com.crcm.core.response.RestResult;
import com.crcm.security.constants.Oauth2ExceptionEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName SecurityHandler
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/17
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityHandler {
    private final ObjectMapper objectMapper;

    /**
     * 认证成功处理器，返回Token
     * 授权码模式下获取code时触发此处理器
     * @return org.springframework.security.web.authentication.AuthenticationSuccessHandler
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                log.info("loginSuccessHandler ---> 网页认证通过");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    /**
     * 网页认证失败处理器（使用json交互时）
     * @return org.springframework.security.web.authentication.AuthenticationFailureHandler
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            String msg;
            if (exception instanceof BadCredentialsException) {
                msg = Oauth2ExceptionEnum.PASSWORD_ERR.getMsg();
            } else {
                msg = exception.getMessage();
            }
            log.info("loginFailureHandler ---> {}",msg);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            RestResult rsp = RestResult.failed(HttpStatus.UNAUTHORIZED.value(), msg);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(rsp));
            response.getWriter().flush();
            response.getWriter().close();
        };
    }

}
