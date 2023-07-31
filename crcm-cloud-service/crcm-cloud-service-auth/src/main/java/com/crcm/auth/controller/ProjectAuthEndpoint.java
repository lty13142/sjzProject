package com.crcm.auth.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.crcm.core.response.RestResult;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

/**
 * @ClassName ProjectAuthEnpoint
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/25
 **/
@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class ProjectAuthEndpoint {

    private final TokenStore tokenStore;
    private final RedisTemplate redisTemplate;
    private final CacheManager cacheManager;
    private final HttpServletRequest request;
    private final ConsumerTokenServices consumerTokenServices;

    /**
     * 认证页面
     * @return ModelAndView
     */
    @GetMapping("/login")
    public ModelAndView require() {
        return new ModelAndView("login");
    }

    /**
     * 登出 token
     *
     * @param token Authorization
     */
    @RequestMapping("/logout")
    public RestResult logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token,HttpServletRequest request) {
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
            if (StringUtils.isBlank(token)) {
                return RestResult.failed(HttpStatus.HTTP_INTERNAL_ERROR,"退出失败，token 为空");
            }
        }
        String tokenValue = token.replace("Bearer", "").trim();
        tokenValue = tokenValue.replace("bearer", "").trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
            return RestResult.failed(HttpStatus.HTTP_INTERNAL_ERROR,"退出失败，token 无效");
        }

        OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
        cacheManager.getCache("user_details")
                .evict(auth2Authentication.getName());
        // 移除access_token
        tokenStore.removeAccessToken(accessToken);
        // 移除refresh_token
        if (accessToken.getRefreshToken() != null) {
            tokenStore.removeRefreshToken(accessToken.getRefreshToken());
        }
        consumerTokenServices.revokeToken(tokenValue);
        // 如果存在session，清楚该次session，重新登录
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return RestResult.succeed(null,"退出成功");
    }

    /**
     * 获取用户信息
     * @return ModelAndView
     */
    @RequestMapping("/user")
    public Principal principal(Map map, Principal principal, Authentication authentication) {
        return principal;
    }

}
