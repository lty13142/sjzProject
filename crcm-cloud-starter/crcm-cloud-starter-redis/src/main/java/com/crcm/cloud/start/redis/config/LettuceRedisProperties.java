package com.crcm.cloud.start.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName LettuceRedisProperties
 * @Description Lettuce 版本Redis配置
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/24
 **/
@ConfigurationProperties(prefix = "crcm.lettuce.redis")
public class LettuceRedisProperties {
    /**
     * 是否开启Lettuce Redis
     */
    private Boolean enable = true;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "LettuceRedisProperties{" +
                "enable=" + enable +
                '}';
    }
}
