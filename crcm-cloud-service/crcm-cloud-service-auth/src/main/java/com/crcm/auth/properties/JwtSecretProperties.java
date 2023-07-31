package com.crcm.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName JwtSecretProperties
 * @Description JWT存储token方式非对称加密配置文件
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Data
@ConfigurationProperties(JwtSecretProperties.PREFIX)
public class JwtSecretProperties {
    /**
     * 认证配置前缀{@value}
     */
    final static String PREFIX = "security.oauth2.jwt-key-pair";

    /**
     * jks证书文件存放路径.
     */
    private String path;

    /**
     * jks证书密钥.
     */
    private String pwd;

    /**
     * jks证书别名.
     */
    private String alias;
}
