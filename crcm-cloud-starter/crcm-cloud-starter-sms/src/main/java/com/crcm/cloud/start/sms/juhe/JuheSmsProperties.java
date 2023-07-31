package com.crcm.cloud.start.sms.juhe;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName JuheSmsProperties
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/8/31
 **/
@Data
@Configuration
@ConfigurationProperties("sms.juhe")
public class JuheSmsProperties {
    private String key;
}
