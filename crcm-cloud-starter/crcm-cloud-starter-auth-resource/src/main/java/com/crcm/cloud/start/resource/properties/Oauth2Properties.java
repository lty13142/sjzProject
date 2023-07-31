package com.crcm.cloud.start.resource.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName Oauth2Properties
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/19
 **/
@Data
@ConfigurationProperties(Oauth2Properties.PREFIX)
public class Oauth2Properties {
    /**
     * 认证配置前缀{@value}
     */
    public final static String PREFIX = "security.oauth2.client";
    /**
     * 资源服务器保存的持有公钥的文件名
     */
    public static final String AUTHORIZATION_SERVER_PUBLIC_KEY_FILENAME = "cuas-jwt.pub";


    private String clientId;
    private String clientSecret;
    private String clientAuthenticationScheme;
    private String accessTokenUri;
    private String userAuthorizationUri;
    private String removeTokenUri;
    private String grantType;
    private String registeredRedirectUri;
    private String redirectUiUri;

    @Value("${security.oauth2.resource.id:''}")
    private String resourceId;
    @Value("${security.oauth2.resource.token-info-uri:''}")
    private String tokenInfoUri;
    @Value("${security.oauth2.resource.jwt.key-uri:''}")
    private String jwtKeyUri;
}
