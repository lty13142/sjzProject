package com.crcm.cloud.start.sms.factory;

import com.crcm.cloud.start.sms.dto.SmsInitDTO;
import com.crcm.cloud.start.sms.enums.SmsSenderType;
import com.crcm.cloud.start.sms.handler.AliYunSmsSender;
import com.crcm.cloud.start.sms.handler.JuHeSmsSender;
import com.crcm.cloud.start.sms.handler.SmsSender;

/**
 * @ClassName SmsSenderFactory
 * @Description 短信发送器工厂
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/10
 **/
public class SmsSenderFactory {

    /**
     * 创建发送器
     * @param type
     * @param initDTO
     * @return
     */
    public static SmsSender createSender(SmsSenderType type, SmsInitDTO initDTO) {
        switch (type) {
            case JUHE:
                return new JuHeSmsSender(initDTO.getHost(),initDTO.getKey());
            case ALIYUN:
                return new AliYunSmsSender(initDTO.getHost(), initDTO.getKey(),initDTO.getSecret());
            default:
                return null;
        }
    }
}
