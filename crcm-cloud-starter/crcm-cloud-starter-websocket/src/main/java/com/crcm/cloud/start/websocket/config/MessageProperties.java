package com.crcm.cloud.start.websocket.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MessageProperties
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/18
 **/
@Configuration
@ConfigurationProperties(prefix = "message")
@Data
public class MessageProperties {
    /**
     * 是否开启消息队列
     */
    private boolean enableMq = false;
}
