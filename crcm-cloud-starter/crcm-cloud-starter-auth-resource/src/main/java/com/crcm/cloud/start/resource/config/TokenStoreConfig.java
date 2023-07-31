package com.crcm.cloud.start.resource.config;

import com.crcm.security.components.CustomTokenStore;
import com.crcm.cloud.start.resource.config.jwt.CustomerAccessTokenConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @ClassName TokenStoreConfig
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/19
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
public class TokenStoreConfig {
    /**
     * redis
     */
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 使用redis方式存储token
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix="security.oauth2.token-store",name="type" ,havingValue="redis" ,matchIfMissing=true)
    public RedisTokenStore redisTokenStore(){
        log.info("使用  --->  RedisTokenStore 存储token");
        return new RedisTokenStore(redisTemplate.getConnectionFactory());
    }

    /**
     * 使用自定义redis方式存储token
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "security.oauth2.token-store", name = "type", havingValue = "redis-custom")
    public CustomTokenStore customRedisTokenStore() {
        log.info("使用  --->  CustomRedisTokenStore 存储token");
        CustomTokenStore customRedisTokenStore = new CustomTokenStore(redisTemplate.getConnectionFactory());
        return customRedisTokenStore;
    }

    /**
     * JwtTokenStore配置类
     *
     * @author qipp
     * @date 2020/1/10 16:25
     * @since 1.0.1
     */
    @Configuration
    @ConditionalOnProperty(prefix = "security.oauth2.token-store", name = "type", havingValue = "jwt")
    public static class JwtTokenConfig {

        /**
         * 配置TokenStore
         *
         * @return org.springframework.security.oauth2.provider.token.TokenStore
         * @author qipp
         * @date 2020/1/13 16:29
         */
        @Bean
        @Qualifier("tokenStore")
        public TokenStore tokenStore() {
            log.info("使用  --->  JwtTokenStore 存储token");
            return new JwtTokenStore(accessTokenConverter());
        }

        /**
         * 资源服务token转换器
         *
         * @return org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
         * @author qipp
         * @date 2020/1/10 17:46
         */
        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
            log.info("Create --->  JwtAccessTokenConverter ");
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("public.cert").getInputStream()))) {
                String publicKey = br.lines().collect(Collectors.joining("\n"));
                log.info("publicKey  -> {}", publicKey);
                converter.setVerifierKey(publicKey);
                // 替换默认的token生成方式
                converter.setAccessTokenConverter(new CustomerAccessTokenConverter());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return converter;
        }
    }
}
