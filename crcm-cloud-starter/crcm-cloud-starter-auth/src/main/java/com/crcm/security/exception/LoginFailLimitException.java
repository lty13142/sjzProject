package com.crcm.security.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @ClassName: LoginFailLimitException
 * @Description 登录失败次数超过限制异常,继承自UsernameNotFoundException是因为避免log.error输出报错信息
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author Diaoy
 * @Date 2019/9/26
 **/
public class LoginFailLimitException extends UsernameNotFoundException {

    private String msg;

    public LoginFailLimitException(String msg){
        super(msg);
        this.msg = msg;
    }

    public LoginFailLimitException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }
}
