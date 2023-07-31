package com.crcm.auth.api.constant;

/**
 * @ClassName AuthTypeConstant
 * @Description 授权认证常量
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/10
 **/
public interface AuthTypeConstant {
    /**
     * 用户名密码
      */
    String LOGIN_TYPE_PASSWORD = "password";
    /**
     * 用户名密码验证码
     */
    String LOGIN_TYPE_PASSWORD_AND_CODE = "verification";
    /**
     * 手机号 + 验证码登录
     */
    String LOGIN_TYPE_SMS = "sms";
    /**
     * 微信授权登录
     */
    String LOGIN_TYPE_WECHAT = "weChat";
    /**
     * 刷新token
     */
    String REFRESH_TOKEN = "refresh_token";
    /**
     * 授权码模式
     */
    String LOGIN_TYPE_AUTHORIZATION_CODE = "authorization_code";
}
