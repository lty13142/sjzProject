package com.sjz.partbuilding.enums;


import com.sjz.partbuilding.constants.YTStatusCode;

/**
 * @ClassName：YTBusinessErrorEnum
 * @Description：
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：diaoy
 * @Date：2019/11/25
 **/
public enum YTBusinessStatusEnum {
    //查询
    FIND_WITH_NULL_ID("id为空，查询失败！", YTStatusCode.BAD_REQUEST),
    // 修改
    EDIT_WITH_NULL_ID("id为空，操作失败！", YTStatusCode.BAD_REQUEST);

    public final int code;
    public final String desc;

    YTBusinessStatusEnum(String desc, int code) {
        this.code = code;
        this.desc = desc;
    }
}
