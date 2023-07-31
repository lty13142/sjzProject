package com.crcm.auth.config;

import com.crcm.auth.jwt.CustomTokenEnhancer;
import com.crcm.auth.jwt.CustomerAccessTokenConverter;
import com.crcm.auth.properties.JwtSecretProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * @ClassName JwtTokenConfig
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "security.oauth2.token-store", name = "type", havingValue = "jwt")
@EnableConfigurationProperties(JwtSecretProperties.class)
public class JwtTokenConfig {
    /**
     * jks证书配置
     */
    private final JwtSecretProperties jwtSecretProperties;

    public JwtTokenConfig(JwtSecretProperties jwtSecretProperties) {
        this.jwtSecretProperties = jwtSecretProperties;
    }

    /**
     * 配置TokenStore
     *
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     */
    @Bean
    public JwtTokenStore tokenStore() {
        log.info("使用  --->  JwtTokenStore 存储token");
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 配置jwt转换器
     *
     * @return org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        log.info("Create --->  JwtAccessTokenConverter ");
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
                new ClassPathResource(jwtSecretProperties.getPath()),
                jwtSecretProperties.getPwd().toCharArray())
                .getKeyPair(jwtSecretProperties.getAlias());
        converter.setKeyPair(keyPair);
        // 替换默认的token生成方式
        converter.setAccessTokenConverter(new CustomerAccessTokenConverter());
        return converter;
    }

    /**
     * 自定义token增强器
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     */
    @Bean
    public TokenEnhancer customTokenEnhancer() {
        log.info("Create --->  customTokenEnhancer ");
        return new CustomTokenEnhancer();
    }
}
