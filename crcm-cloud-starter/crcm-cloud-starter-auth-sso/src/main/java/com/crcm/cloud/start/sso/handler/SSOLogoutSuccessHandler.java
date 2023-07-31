package com.crcm.cloud.start.sso.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crcm.cloud.start.sso.config.properties.Oauth2Properties;
import com.crcm.cloud.start.sso.domain.AuthResult;
import com.crcm.cloud.start.sso.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @ClassName SsoLogoutSuccessHandler
 * @Description 单点登录退出登录成功处理器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/26
 **/
@Slf4j
public class SSOLogoutSuccessHandler implements LogoutSuccessHandler {
    private Oauth2Properties oauth2Properties;

    public SSOLogoutSuccessHandler(Oauth2Properties oauth2Properties) {
        this.oauth2Properties = oauth2Properties;
    }

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2Authentication oauth2Authentication = (OAuth2Authentication) authentication;
        System.out.println(request.getSession().getId());
        AuthResult authResult = AuthResult.failed("注销登陆失败！");
        if (authentication != null) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oauth2Authentication.getDetails();
            String accessToken = details.getTokenValue();
            // 删除存在cookie里面的token
            CookieUtil.removeCookie("access_token", request, response);
            CookieUtil.removeCookie("refresh_token", request, response);
            //请求头信息
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "bearer " + accessToken);
            //授权请求信息
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            //HttpEntity
            HttpEntity httpEntity = new HttpEntity(map, httpHeaders);
            //复杂构造函数的使用
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(5000);// 设置超时
            requestFactory.setReadTimeout(5000);
            //利用复杂构造器可以实现超时设置，内部实际实现为 HttpClient
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            JSONObject result = restTemplate.exchange(oauth2Properties.getRemoveTokenUri() + "?token=" + accessToken, HttpMethod.POST,
                    httpEntity, JSONObject.class).getBody();
            // 重定向到认证服务器的退出登录接口
//            redirectStrategy.sendRedirect(request, response, oauth2Properties.getRemoveTokenUri() + "?token=" + accessToken);
            log.info(String.format("用户 %s  于 %s 退出系统,IP %s,授权服务器响应结果为：%s,", authentication.getName(), LocalDateTime.now(), request.getRemoteHost(), JSON.toJSONString(result)));
            authResult = AuthResult.succeed(null, "登出成功");
        }
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=utf-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().print(JSON.toJSONString(authResult));
    }
}
