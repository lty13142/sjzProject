package com.crcm.develop.core.conf.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName TokenProperties
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/10 16:58
 **/
@Data
@Component
@ConfigurationProperties(prefix = "jwt.token")
@PropertySource(value = {"classpath:jwt.properties" })
public class JwtProperties {

    /**
     * 存放Token的Header Key
     */
    private String header;
    /**
     * 密匙key
     */
    private String secret;
    /**
     * 过期时间
     */
    private Integer tokenExpireTime;
    /**
     * 自定义token 前缀字符
     */
    private String tokenHead;
    /**
     * 超时时间
     */
    private int tokenOverTime;
    /**
     * 刷新时间
     */
    private int tokenRefreshTime;
    /**
     * 默认redis 设置为true后，token将存入redis，并具有单点登录功能 设为false将使用JWT交互
     */
    private boolean enableRedis;
    /**
     * token中存储用户权限数据 设为true开启后可避免每次请求再获取用户权限，但有可能导致编辑权限菜单后无法读取到最新权限数据（需用户重新登录）
     */
    private boolean storePerms;
    /**
     * token中存储用户权限的参数头
     */
    private String authorities;

    /**
     * 单点登录
     */
    @Value("${jwt.singleLogin}")
    private Boolean singleLogin;

    /**
     * 保存登录时间
     */
    @Value("${jwt.saveLoginTime}")
    private Long saveLoginTime;

    /**
     *  限制用户登陆错误次数（次）
     */
    @Value("${jwt.loginTimeLimit}")
    private Integer loginTimeLimit;
    /**
     * 错误超过次数后多少分钟后才能继续登录（分钟）
     */
    @Value("${jwt.loginAfterTime}")
    private Integer loginAfterTime;


}
