package com.crcm.core.constant.enums;


import cn.hutool.http.HttpStatus;

/**
 * @ClassName: YTBusinessErrorEnum
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/11/25
 **/
public enum BusinessStatusEnum implements BaseStatusEnum{
    //查询
    FIND_WITH_NULL_ID("id为空，查询失败！", HttpStatus.HTTP_BAD_REQUEST),
    // 修改
    EDIT_WITH_NULL_ID("id为空，操作失败！", HttpStatus.HTTP_BAD_REQUEST);

    public final int code;
    public final String msg;

    BusinessStatusEnum(String msg, int code) {
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
