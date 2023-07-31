package com.crcm.core.constant;

/**
 * @ClassName Oauth2Constants
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/13
 **/
public interface Oauth2Constants {
    /**
     * 认证类型{@value}
     */
     String GRANT_MODE = "grant_type";

    /**
     * 扩展授权方式
     */
     String EXTENSION_AUTH_MODE = "auth_type";

    /**
     * 密码模式{@value}
     */
     String GRANT_MODE_PASSWORD = "password";

    /**
     * 授权码模式{@value}
     */
     String GRANT_MODE_AUTHORIZATION_CODE = "authorization_code";

    /**
     * 刷新令牌{@value}
     */
     String GRANT_MODE_REFRESH_TOKEN = "refresh_token";

    /**
     * 密码模式（注册后通过手机号）{@value}
     */
     String GRANT_MODE_REGISTER = "register";


}
