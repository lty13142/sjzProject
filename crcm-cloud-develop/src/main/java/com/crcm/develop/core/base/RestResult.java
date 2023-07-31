package com.crcm.develop.core.base;

import cn.hutool.http.HttpStatus;

import java.io.Serializable;

/**
 * @ClassName RestResult
 * @Description restful 风格的返回实体
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/20
 **/
public class RestResult<T> implements Serializable {

    private static final long serialVersionUID = -4895970920187980110L;
    /**
     * 状态码
     */
    private Integer code = HttpStatus.HTTP_OK;
    /**
     * 状态标志
     */
    private Boolean success = true;

    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public static <T> RestResult<T> succeed() {
        return create(HttpStatus.HTTP_OK, true, "请求成功", null);
    }

    public static <T> RestResult<T> succeed(T data) {
        return create(HttpStatus.HTTP_OK, true, "请求成功", data);
    }

    public static <T> RestResult<T> succeed(T data, String message) {
        return create(HttpStatus.HTTP_OK, true, message, data);
    }

    public static <T> RestResult<T> failed() {
        return create(HttpStatus.HTTP_INTERNAL_ERROR, false, "请求失败", null);
    }

    public static <T> RestResult<T> failed(String message) {
        return create(HttpStatus.HTTP_INTERNAL_ERROR, false, message, null);
    }

    public static <T> RestResult<T> failed(Integer code, String message) {
        return create(code, false, message, null);
    }

    public static <T> RestResult<T> failed(Integer code, String message, T data) {
        return create(code, false, message, data);
    }


    public static <T> RestResult<T> create(Integer code, Boolean success, String message, T data) {
        RestResult<T> result = new RestResult<>();
        result.setCode(code);
        result.setSuccess(success);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RestResult(Integer code, Boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public RestResult() {
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "code=" + code +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
