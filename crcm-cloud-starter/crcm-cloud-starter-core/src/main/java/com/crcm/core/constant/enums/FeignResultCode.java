package com.crcm.core.constant.enums;

import cn.hutool.http.HttpStatus;

/**
 * @ClassName FeignStatusEnum
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/4
 **/
public enum FeignResultCode implements BaseStatusEnum {
    //用户
    SERVICE_UNAVAILABLE(HttpStatus.HTTP_UNAVAILABLE, "服务不可用");

    private int code;
    private String msg;

    FeignResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
