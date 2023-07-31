package com.crcm.security.handle;

import com.crcm.security.constants.Oauth2ExceptionEnum;
import com.crcm.core.response.RestResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CustomAccessDeniedHandler
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/17
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            // 无权限
            log.info("无权限 --->  CustomAccessDeniedHandler ");
            RestResult result = RestResult.failed( Oauth2ExceptionEnum.USER_PERMISSION_DENIED.getCode(),Oauth2ExceptionEnum.USER_PERMISSION_DENIED.getMsg());
            response.getWriter().write(objectMapper.writeValueAsString(result));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
