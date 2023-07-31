package com.crcm.core.extend.snowflake;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SnowflakeConfig
 * @Description 雪花算法配置类Config
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/18
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(value = {SnowflakeProperties.class})
public class SnowflakeConfig {
    /**
     * 雪花算法配置
     */
    private final SnowflakeProperties snowflakeProperties;

    /**
     * 配置雪花算法
     * @return cn.hutool.core.lang.Snowflake
     */
    @Bean
    public Snowflake snowflake() {
        // 为终端ID默认值 1
        Long workerId = snowflakeProperties.getWorkerId();
        if (workerId == null) {
            workerId = 1L;
        }

        // 数据中心ID默认值 1
        Long dataCenterId = snowflakeProperties.getDataCenterId();
        if (dataCenterId == null) {
            dataCenterId = 1L;
        }
        log.info("配置雪花算法 workerId -> {}, dataCenterId -> {}", workerId, dataCenterId);
        return IdUtil.createSnowflake(workerId, dataCenterId);
    }
}
