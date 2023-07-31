package com.crcm.security.components;

import com.crcm.core.constant.AuthConstants;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @ClassName CustomTokenStore
 * @Description 自定义token存储，基于RedisTokenStore
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/23
 **/
public class CustomTokenStore extends RedisTokenStore {

    public CustomTokenStore(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
        // 设置缓存前缀
        super.setPrefix(AuthConstants.PROJECT_PREFIX+ AuthConstants.OAUTH_PREFIX);
        // 设置缓存序列化实现
        super.setSerializationStrategy(new JacksonRedisTokenStoreSerializationStrategy());
//        super.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
//            @Override
//            public String extractKey(OAuth2Authentication authentication) {
////                return super.extractKey(authentication) + ":" + TenantContextHolder.getTenantId();
//                return super.extractKey(authentication);
//            }
//        });
        // 设置token key 的生成规则
        super.setAuthenticationKeyGenerator(new CustomAuthenticationKeyGenerator());
    }


}
