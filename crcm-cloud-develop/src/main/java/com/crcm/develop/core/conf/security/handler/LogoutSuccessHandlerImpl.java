package com.crcm.develop.core.conf.security.handler;

import com.alibaba.fastjson.JSON;
import com.crcm.develop.common.utils.ServletUtils;
import com.crcm.develop.core.base.RestResult;
import com.crcm.develop.core.conf.security.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 *
 * @author zzyt
 */
@Configuration
@AllArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    private final TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
            String userName = tokenService.getUserName(request);
            // 删除用户缓存记录
//            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        ServletUtils.renderString(response, JSON.toJSONString(RestResult.succeed(null, "退出成功")));
    }
}
