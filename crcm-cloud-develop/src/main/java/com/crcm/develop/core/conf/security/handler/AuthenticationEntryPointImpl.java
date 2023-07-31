package com.crcm.develop.core.conf.security.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.crcm.develop.common.utils.ServletUtils;
import com.crcm.develop.core.base.RestResult;
import com.crcm.develop.core.text.StrFormatter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 * @author zzyt
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        String msg = StrFormatter.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, HttpStatus.HTTP_UNAUTHORIZED, JSON.toJSONString(RestResult.failed(HttpStatus.HTTP_UNAUTHORIZED, msg)));
    }
}
