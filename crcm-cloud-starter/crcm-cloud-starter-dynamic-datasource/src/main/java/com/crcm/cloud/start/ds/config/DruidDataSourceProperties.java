package com.crcm.cloud.start.ds.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName DruidDataSourceProperties
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/12/3
 **/
@Data
@Component
@ConfigurationProperties("spring.datasource.druid")
public class DruidDataSourceProperties {

    /**
     * 数据源用户名
     */
    private String username;

    /**
     * 数据源密码
     */
    private String password;

    /**
     * jdbcurl
     */
    private String url;

    /**
     * 数据源驱动
     */
    private String driverClassName;

}