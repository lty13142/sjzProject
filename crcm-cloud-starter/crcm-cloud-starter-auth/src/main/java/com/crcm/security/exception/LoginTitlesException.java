package com.crcm.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <p>
 *      平台参数校验失败异常
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class LoginTitlesException extends AuthenticationException {

    public LoginTitlesException(String msg, Throwable t) {
        super(msg, t);
    }

    public LoginTitlesException(String msg) {
        super(msg);
    }
}
