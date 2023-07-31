package com.crcm.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName: TokenExpiredException
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/9/27
 **/
public class TokenExpiredException extends AuthenticationException {

    private String msg;

    public TokenExpiredException(String msg){
        super(msg);
        this.msg = msg;
    }

    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

}
