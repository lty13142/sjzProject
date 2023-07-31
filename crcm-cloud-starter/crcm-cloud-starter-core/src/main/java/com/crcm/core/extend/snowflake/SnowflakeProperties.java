package com.crcm.core.extend.snowflake;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName SnowflakeProperties
 * @Description 雪花算法配置类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/18
 **/
@Data
@ConfigurationProperties(SnowflakeProperties.PREFIX)
public class SnowflakeProperties {
    /**
     * 认证配置前缀{@value}
     */
    public final static String PREFIX = "crcm.snowflake";
    /**
     * 终端ID
     */
    private Long workerId;

    /**
     * 数据中心ID
     */
    private Long dataCenterId;
}
