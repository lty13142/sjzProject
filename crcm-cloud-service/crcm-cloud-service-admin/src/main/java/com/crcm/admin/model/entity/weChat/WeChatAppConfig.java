package com.crcm.admin.model.entity.weChat;

import lombok.Data;

/**
 * 微信APP授权配置信息配置类
 *
 * @author rmc
 * @version 1.0
 * @date 2023/1/9 15:59
 */
@Data
public class WeChatAppConfig {

    /**
     * 微信绑定的app_id
     */
    String appId;
    /**
     * 微信绑定的app_secret
     */
    String appSecret;
}
