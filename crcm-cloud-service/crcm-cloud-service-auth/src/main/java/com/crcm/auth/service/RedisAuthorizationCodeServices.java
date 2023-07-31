package com.crcm.auth.service;

import com.crcm.security.components.JacksonRedisTokenStoreSerializationStrategy;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy;

/**
 * @ClassName RedisAuthorizationCodeServices
 * @Description 授权码存取策略
 * <p>重写授权码存取策略 JdbcAuthorizationCodeServices替换
 * 授权码缓存redis里面，RedisAuthorizationCodeServices，解决启动多个认证中心授权码问题
 *<p>
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

    private static final String AUTHORIZATION_CODE = "authorization:code:";

    /**
     * 授权码有效时长
     */
    private long expiration = 300L;

    /**
     * key 前缀
     */
    private String prefix = "";


    private RedisTemplate<String, Object> redisTemplate;

    private RedisTokenStoreSerializationStrategy serializationStrategy = new JacksonRedisTokenStoreSerializationStrategy();


    public RedisAuthorizationCodeServices(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }


    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    private RedisConnection getConnection() {
        return redisTemplate.getConnectionFactory().getConnection();
    }

    /**
     * value序列化
     *
     * @param object
     * @return
     */
    private byte[] serialize(Object object) {
        return serializationStrategy.serialize(object);
    }

    /**
     * key序列化
     *
     * @param string
     * @return
     */
    private byte[] serialize(String string) {
        return serializationStrategy.serialize(string);
    }

    /**
     * key序列化
     *
     * @param object
     * @return
     */
    private byte[] serializeKey(Object object) {
        return serialize(prefix + object);
    }


    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    private OAuth2Authentication deserializeAuthentication(byte[] bytes) {
        return serializationStrategy.deserialize(bytes, OAuth2Authentication.class);
    }


    /**
     * 将随机生成的授权码存到redis中
     *
     * @param code
     * @param authentication
     * @return void
     */
    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        byte[] serializedKey = serializeKey(AUTHORIZATION_CODE + code);
        byte[] serializedAuthentication = serialize(authentication);
        RedisConnection conn = getConnection();
        try {
            conn.openPipeline();
            conn.set(serializedKey, serializedAuthentication);
            conn.expire(serializedKey, expiration);
            conn.closePipeline();
        } finally {
            conn.close();
        }

    }

    /**
     * 取出授权码并删除授权码(权限码只能用一次，调试时可不删除，code就可多次使用)
     *
     * @param code
     * @return org.springframework.security.oauth2.provider.OAuth2Authentication
     */
    @Override
    protected OAuth2Authentication remove(String code) {
        byte[] serializedKey = serializeKey(AUTHORIZATION_CODE + code);
        RedisConnection conn = getConnection();
        byte[] bytes;
        try {
            bytes = conn.get(serializedKey);
            if (bytes != null) {
                conn.del(serializedKey);
            }
        } finally {
            conn.close();
        }
        return deserializeAuthentication(bytes);
    }

}
