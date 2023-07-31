package com.crcm.gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RsaProperties
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/15
 **/
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "security.rsa")
public class RsaProperties {
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 是否启用
     */
    private Boolean enable;
}
