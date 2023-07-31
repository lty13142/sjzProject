package com.crcm.core.exception;

/**
 * @ClassName ValidateCodeException
 * @Description 验证码类型异常
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
public class ValidateCodeException extends RuntimeException{
    public ValidateCodeException(String message) {
        super(message);
    }
}
