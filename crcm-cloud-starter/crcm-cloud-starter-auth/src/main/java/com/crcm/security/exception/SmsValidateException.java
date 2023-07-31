package com.crcm.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName SmsValidateException
 * @Description 短信登录验证异常
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/1/19
 **/
public class SmsValidateException extends AuthenticationException {

    public SmsValidateException(String msg, Throwable t) {
        super(msg, t);
    }

    public SmsValidateException(String msg) {
        super(msg);
    }
}
