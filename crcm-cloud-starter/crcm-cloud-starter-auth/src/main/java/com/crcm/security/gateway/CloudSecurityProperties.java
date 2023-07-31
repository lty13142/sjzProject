package com.crcm.security.gateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName CloudSecurityProperties
 * @Description 网关请求限定配置
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/10
 **/
@Data
@ConfigurationProperties(prefix = "security")
public class CloudSecurityProperties {
    /**
     * 是否只能通过网关获取资源
     * 默认为True
     */
    private Boolean gatewayFetchOnly = Boolean.FALSE;
}
