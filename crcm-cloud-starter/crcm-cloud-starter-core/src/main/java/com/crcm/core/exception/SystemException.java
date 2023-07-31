package com.crcm.core.exception;

import cn.hutool.http.HttpStatus;
import com.crcm.core.constant.enums.BaseStatusEnum;

/**
 * @ClassName SystemException
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/16
 **/
public class SystemException extends BaseException {

    public SystemException(int code) {
        super(code);
    }


    public SystemException(int code, String... args) {
        super(code, args);
    }

    public SystemException(int code, String msg) {
        super(code, msg);
    }

    public SystemException(String msg) {
        super(HttpStatus.HTTP_INTERNAL_ERROR, msg);
    }

    public SystemException(BaseStatusEnum baseStatusEnum) {
        super(baseStatusEnum.getCode(), baseStatusEnum.getMsg());
    }

}