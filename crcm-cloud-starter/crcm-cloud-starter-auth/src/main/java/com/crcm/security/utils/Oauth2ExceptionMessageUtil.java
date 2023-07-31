package com.crcm.security.utils;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @ClassName Oauth2ExceptionMessageUtil
 * @Description 认证异常信息处理工具类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
public class Oauth2ExceptionMessageUtil {
    /**
     * 获取异常信息中关键内容
     * @param message 异常信息
     * @return java.lang.String
     */
    public static String getExceptionMessage(String message){
        String msg = message.substring(message.lastIndexOf(": "));
        return StringUtils.isBlank(msg.replace(":","")) ? ": 空值" : msg;
    }
}
