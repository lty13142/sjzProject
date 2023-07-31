package com.crcm.gateway.config;

import org.springframework.http.HttpStatus;

/**
 * @ClassName AuthException
 * @Description 权限校验异常
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/6/2
 **/
public class AuthException extends RuntimeException {

    private int code = HttpStatus.UNAUTHORIZED.value();
    private String message;

    public AuthException() {
        super();
    }

    public AuthException(String message) {
        super(message);
        this.message = message;
    }

    public AuthException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}