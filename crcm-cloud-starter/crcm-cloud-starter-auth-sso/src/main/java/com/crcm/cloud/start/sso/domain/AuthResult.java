package com.crcm.cloud.start.sso.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @ClassName AuthResult
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/9/28
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResult<T> implements Serializable {
    private static final long serialVersionUID = 7692787580146849699L;
    /**
     * 状态码
     */
    private Integer code = HttpStatus.OK.value();
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

    public static <T> AuthResult<T> succeed() {
        return create(HttpStatus.OK.value(), true, "请求成功", null);
    }

    public static <T> AuthResult<T> succeed(T data) {
        return create(HttpStatus.OK.value(), true, "请求成功", data);
    }

    public static <T> AuthResult<T> succeed(T data, String message) {
        return create(HttpStatus.OK.value(), true, message, data);
    }


    public static <T> AuthResult<T> failed() {
        return create(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "请求失败", null);
    }

    public static <T> AuthResult<T> failed(String message) {
        return create(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, message, null);
    }

    public static <T> AuthResult<T> failed(Integer code, String message) {
        return create(code, false, message, null);
    }

    public static <T> AuthResult<T> failed(Integer code, String message, T data) {
        return create(code, false, message, data);
    }
    public static <T> AuthResult<T> create(Integer code, Boolean success, String message, T data) {
        AuthResult<T> result = new AuthResult<>();
        result.setCode(code);
        result.setSuccess(success);
        result.setData(data);
        result.setMessage(message);
        return result;
    }
}
