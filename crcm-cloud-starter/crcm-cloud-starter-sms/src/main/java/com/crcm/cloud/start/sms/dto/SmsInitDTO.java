package com.crcm.cloud.start.sms.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SmsInitDTO
 * @Description 短信初始化
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/11
 **/
@Data
@Builder
public class SmsInitDTO implements Serializable {
    private static final long serialVersionUID = -7657474375279898143L;
    /**
     * 短信发送API地址
     */
    private String host;
    /**
     * 短信API key
     */
    private String key;
    /**
     * 短信API 密码
     */
    private String secret;
}
