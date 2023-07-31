package com.crcm.core.exception;

/**
 * @ClassName BaseException
 * @Description 基础异常
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/23
 **/
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BaseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(Integer code, String... args) {
        super(String.valueOf(code));
        this.code = code;
        this.args = args;
    }

    public BaseException(String service, Integer code, Object[] args, String message) {
        this.service = service;
        this.code = code;
        this.args = args;
        this.message = message;
    }

    public BaseException(String message) {
        this.message = message;
    }

    /**
     * 所属模块
     */
    private String service;

    //代码
    private Integer code;
    //信息
    private String message;
    //参数
    protected Object[] args;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }
}
