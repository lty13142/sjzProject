package com.crcm.cloud.start.sms.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AliyunSmsProperties
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/8/5
 **/
@Data
@Configuration
@ConfigurationProperties("sms.aliyun")
public class AliyunSmsProperties {
    private String accessKey;
    private String secret;

}
