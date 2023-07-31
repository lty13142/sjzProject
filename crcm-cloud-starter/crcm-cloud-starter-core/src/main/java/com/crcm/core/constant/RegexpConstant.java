package com.crcm.core.constant;

import java.util.regex.Pattern;

/**
 * @ClassName RegexpConstant
 * @Description 正则表达式常量
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
public interface RegexpConstant {
    /**
     * 注册账户名 4~15位英文字母
     */
    String ACCOUNT = "/^[a-zA-Z][a-zA-Z0-9_]{4,15}$/\n";
    /**
     * 手机号正则
     */
    String PHONE = "/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[";
    /**
     * 中文名 2-4个中文字符正则
     */
    String CHINESE_NAME = "/^[\\u4e00-\\u9fa5]{2,4}$/";
    /**
     * 中文正则
     */
    Pattern CHINESE = Pattern.compile("[\u4e00-\u9fa5]");
    /**
     * 座机号码
     */
    String TELEPHONE = "/^(0\\d{2,3})-?(\\d{7,8})$/";
    /**
     * 邮箱
     */
    String EMAIL = "/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/";
    /**
     * QQ号码
     */
    String QQ_NUMBER = "/^[1-9][0-9]\\d{4,9}$/";
    /**
     * 邮政编码
     */
    String POST_CODE = "/^[1-9]\\d{5}$/";
}
