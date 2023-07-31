package com.crcm.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName: ValidateCodeException
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/11/11
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String explanation) {
        super(explanation);
    }
}
