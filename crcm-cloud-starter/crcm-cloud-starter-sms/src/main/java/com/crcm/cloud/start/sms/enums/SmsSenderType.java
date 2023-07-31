package com.crcm.cloud.start.sms.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName SmsTypeEnum
 * @Description 短信发送器类型枚举
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/10
 **/
public enum SmsSenderType {
    /**
     * 阿里云
     */
    ALIYUN("aliyun"),
    /**
     * 聚合
     */
    JUHE("juhe");

    private String value;

    SmsSenderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SmsSenderType getByValue(String value) {
        SmsSenderType[] values = SmsSenderType.values();
        if (values.length > 0) {
            for (SmsSenderType smsSenderType : values) {
                if (StringUtils.equals(smsSenderType.getValue(), value)) {
                    return smsSenderType;
                }
            }
        }
        return null;
    }
}
