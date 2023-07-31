package com.crcm.core.exception;

import cn.hutool.http.HttpStatus;
import com.crcm.core.constant.enums.BaseStatusEnum;

/**
 * @ClassName CustomException
 * @Description 自定义异常
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
public class CustomException extends BaseException {

    public CustomException(int code) {
        super(code);
    }

    public CustomException(int code, String... args) {
        super(code, args);
    }

    public CustomException(int code, String msg) {
        super(code, msg);
    }

    public CustomException(String msg) {
        super(HttpStatus.HTTP_INTERNAL_ERROR, msg);
    }

    public CustomException(BaseStatusEnum baseStatusEnum) {
        super(baseStatusEnum.getCode(), baseStatusEnum.getMsg());
    }

}
