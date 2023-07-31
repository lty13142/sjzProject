package com.crcm.cloud.service.chat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName OpenImConfig
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/29
 **/
@Data
@Configuration
@ConfigurationProperties("im")
public class OpenImConfig {
    private String serverHost;
}
