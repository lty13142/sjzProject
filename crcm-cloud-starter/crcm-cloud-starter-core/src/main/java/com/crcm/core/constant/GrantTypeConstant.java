package com.crcm.core.constant;

/**
 * @ClassName GrantTypeConstant
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
public interface GrantTypeConstant {
    /**
     * 刷新模式
     */
    String REFRESH_TOKEN = "refresh_token";
    /**
     * 授权码模式
     */
    String AUTHORIZATION_CODE = "authorization_code";
    /**
     * 客户端模式
     */
    String CLIENT_CREDENTIALS = "client_credentials";
    /**
     * 密码模式
     */
    String PASSWORD = "password";
    /**
     * 简化模式
     */
    String IMPLICIT = "implicit";
}
