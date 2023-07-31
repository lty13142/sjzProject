package com.crcm.cloud.start.sso.components;

import com.alibaba.fastjson.JSON;
import com.crcm.cloud.start.sso.constants.Oauth2ExceptionEnum;
import com.crcm.cloud.start.sso.domain.AuthResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName CustomAccessDeniedHandler
 * @Description 自定义权限不足过滤器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/17
 **/
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private String errorPage;

    @Override
    @SneakyThrows
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException ex) {
        //判断是否为ajax请求
        if (httpServletRequest.getHeader("accept").indexOf("application/json") > -1
                || (httpServletRequest.getHeader("X-Requested-With") != null && httpServletRequest.getHeader("X-Requested-With").equals(
                "XMLHttpRequest"))) {
            //设置状态为403，无权限状态
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            //设置格式以及返回json数据 方便前台使用reponseJSON接取
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            AuthResult result = AuthResult.failed(Oauth2ExceptionEnum.USER_PERMISSION_DENIED.getCode(), Oauth2ExceptionEnum.USER_PERMISSION_DENIED.getMsg());
            out.append(JSON.toJSONString(result));
        } else if (!httpServletResponse.isCommitted()) {//非ajax请求
            if (errorPage != null) {
                // Put exception into request scope (perhaps of use to a view)
                httpServletRequest.setAttribute(WebAttributes.ACCESS_DENIED_403, ex);

                // Set the 403 status code.
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

                // forward to error page.
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(errorPage);
                dispatcher.forward(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, ex.getMessage());
            }
        }
    }

    public void setErrorPage(String errorPage) {
        if ((errorPage != null) && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage must begin with '/'");
        }
        this.errorPage = errorPage;
    }
}
