package com.crcm.security.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName BusinessCommonException
 * @Description 业务通用异常
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/13
 **/
@Getter
@Setter
public class BusinessCommonException extends RuntimeException {
    /**
     * 本异常错误代码
     */
    private Integer errorCode;
    /**
     * 附加描述信息
     */
    private String additionMessage;


    /**
     * 异常状态码、异常消息 构造器
     *
     * @param errCode         异常状态码
     * @param additionMessage 异常消息
     */
    public BusinessCommonException(Integer errCode, String additionMessage) {
        super(additionMessage);
        this.errorCode = errCode;
        this.additionMessage = additionMessage;
    }

    /**
     * 异常状态码，消息，异常对象 构造器
     *
     * @param errCode 异常状态码
     * @param cause   异常对象
     */
    public BusinessCommonException(Integer errCode, String additionMessage, Throwable cause) {
        super(cause);
        this.errorCode = errCode;
        this.additionMessage = additionMessage;
    }

    /**
     * 异常消息，异常对象 构造器
     */
    public BusinessCommonException(String additionMessage, Throwable cause) {
        super(cause);
        this.additionMessage = additionMessage;
    }

    /**
     * 获取异常消息
     */
    @Override
    public String getMessage() {
        return this.additionMessage;
    }
}
