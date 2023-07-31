package com.crcm.core.extend.desensitize.enums;

/**
 * @ClassName SensitiveTypeEnum
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/8/8
 **/

public enum DesensitizedType {
    /**
     * 无
     */
    NONE,
    /**
     * 用户ID
     */
    USER_ID,
    /**
     * 中文名
     */
    CHINESE_NAME,
    /**
     * 身份证号
     */
    ID_CARD,
    /**
     * 座机号
     */
    FIXED_PHONE,
    /**
     * 手机号
     */
    MOBILE_PHONE,
    /**
     * 地址
     */
    ADDRESS,
    /**
     * 电子邮件
     */
    EMAIL,
    /**
     * 银行卡
     */
    BANK_CARD,
    /**
     * 密码
     */
    PASSWORD,
    /**
     * 车牌号
     */
    CAR_LICENSE;
}
