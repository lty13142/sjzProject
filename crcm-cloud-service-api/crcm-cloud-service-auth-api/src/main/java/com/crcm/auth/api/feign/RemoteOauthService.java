package com.crcm.auth.api.feign;

import com.alibaba.fastjson.JSONObject;
import com.crcm.auth.api.feign.factory.RemoteOauthServiceFallbackFactory;
import com.crcm.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName OauthService
 * @Description 远程授权服务
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@FeignClient(contextId = "remoteOauthService", value = ServiceNameConstants.AUTH_SERVICE,
        fallbackFactory = RemoteOauthServiceFallbackFactory.class)
public interface RemoteOauthService {
    /**
     * 密码模式获取token
     *
     * @param type             oauth2认证模式
     * @param authType         认证类型      （用户名密码 -> password , 用户名密码验证码 -> verification）
     * @param username         用户名/手机号
     * @param password         密码
     * @param verificationCode 验证码       （验证码参与授权时使用）
     * @param key              验证码唯一键 （验证码参与授权时使用）
     * @param clientId         客户端
     * @param secret           密钥
     * @param scope            应用授权作用域
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    JSONObject getToken(@RequestParam("grant_type") String type,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("client_id") String clientId,
                        @RequestParam("client_secret") String secret,
                        @RequestParam("scope") String scope,
                        @RequestParam("auth_type") String authType,
                        @RequestParam("user_type") String userType,
                        @RequestParam("verification_code") String verificationCode,
                        @RequestParam("key") String key);

    /**
     * 密码模式获取token
     * <p>手机号 + 验证码</p>
     *
     * @param type        oauth2认证模式 password
     * @param authType    认证类型      （用户名密码 -> password , 用户名密码验证码 -> verification）
     * @param phoneNumber 手机号
     * @param phoneCode   短信验证码
     * @param clientId    客户端
     * @param secret      密钥
     * @param scope       应用授权作用域
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    JSONObject getTokenByPhone(@RequestParam("grant_type") String type,
                               @RequestParam("auth_type") String authType,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("phoneCode") String phoneCode,
                               @RequestParam("client_id") String clientId,
                               @RequestParam("client_secret") String secret,
                               @RequestParam("scope") String scope);

    /**
     * 授权码模式获取token
     *
     * @param type         oauth2认证模式 password
     * @param code         授权码
     * @param redirectUri  重定向URL
     * @param clientId     客户端ID
     * @param clientSecret 客户端密钥
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    JSONObject getTokenByAuthorizationCode(@RequestParam("grant_type") String type,
                                           @RequestParam("code") String code,
                                           @RequestParam("redirect_uri") String redirectUri,
                                           @RequestParam("client_id") String clientId,
                                           @RequestParam("client_secret") String clientSecret);


    /**
     * 刷新token
     *
     * @param grantModeRefreshToken oauth2认证模式
     * @param refreshToken          刷新token
     * @param clientId              客户端ID
     * @param clientSecret          客户端密钥
     * @param scope                 应用授权作用域 (刷新token时可缩减应用授权作用域)
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    JSONObject getTokenByRefreshToken(@RequestParam("grant_type") String grantModeRefreshToken,
                                      @RequestParam("refresh_token") String refreshToken,
                                      @RequestParam("client_id") String clientId,
                                      @RequestParam("client_secret") String clientSecret,
                                      @RequestParam("scope") String scope);

    /**
     * 密码模式微信授权登录
     *
     * @param type     oauth2认证模式 password
     * @param authType weChat
     * @param unionId  微信ID
     * @param clientId 客户端ID
     * @param secret   客户端密钥
     * @param scope    应用授权作用域
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    JSONObject getTokenByWeChat(@RequestParam("grant_type") String type,
                                @RequestParam("auth_type") String authType,
                                @RequestParam("unionId") String unionId,
                                @RequestParam("client_id") String clientId,
                                @RequestParam("client_secret") String secret,
                                @RequestParam("scope") String scope);

    /**
     * 登出
     * @param token     token
     * @return com.alibaba.fastjson.JSONObject
     */
    @RequestMapping(value = "/token/logout", method = RequestMethod.GET)
    JSONObject logout(@RequestParam("token") String token);
}
