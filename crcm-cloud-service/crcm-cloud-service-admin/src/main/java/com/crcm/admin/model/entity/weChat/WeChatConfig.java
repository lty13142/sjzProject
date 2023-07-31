package com.crcm.admin.model.entity.weChat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信配置类
 *
 * @author rmc
 * @version 1.0
 * @date 2023/1/9 15:57
 */
@Data
@Component
@ConfigurationProperties("wechat")
public class WeChatConfig {

    /**
     * 微信APP授权配置信息
     */
    private WeChatAppConfig applet;
}
