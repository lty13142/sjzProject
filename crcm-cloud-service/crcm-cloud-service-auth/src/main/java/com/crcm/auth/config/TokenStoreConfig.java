package com.crcm.auth.config;

import com.crcm.security.components.CustomTokenStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @ClassName TokenStoreConfig
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
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
     * 数据源
     */
    private final DataSource dataSource;

    /**
     * jdbc 方式token存储
     * @return
     */
    @Bean
    @Deprecated
    @ConditionalOnProperty(prefix = "security.oauth2.token-store", name = "type", havingValue = "jdbc")
    public JdbcTokenStore jdbcTokenStore() {
        log.info("使用  --->  jdbcTokenStore 存储token");
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 使用redis方式存储token
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "security.oauth2.token-store", name = "type", havingValue = "redis", matchIfMissing = true)
    public RedisTokenStore redisTokenStore() {
        log.info("使用  --->  RedisTokenStore 存储token");
        return new RedisTokenStore(redisTemplate.getConnectionFactory());
    }

    /**
     * 使用自定义 redis方式存储token
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "security.oauth2.token-store", name = "type", havingValue = "redis-custom")
    public CustomTokenStore customRedisTokenStore() {
        log.info("使用  --->  CustomRedisTokenStore 存储token");
        CustomTokenStore customRedisTokenStore = new CustomTokenStore(redisTemplate.getConnectionFactory());
        return customRedisTokenStore;
    }
}
