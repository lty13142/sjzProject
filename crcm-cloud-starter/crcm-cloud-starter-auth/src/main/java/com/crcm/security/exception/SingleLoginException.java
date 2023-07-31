package com.crcm.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName: SingleLoginException
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/9/27
 **/
public class SingleLoginException extends AuthenticationException {

    private String msg;

    public SingleLoginException(String msg){
        super(msg);
        this.msg = msg;
    }

    public SingleLoginException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

}
