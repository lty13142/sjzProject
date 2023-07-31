package com.crcm.cloud.start.log.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @ClassName LogTypeEnum
 * @Description 日志类型枚举
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/22
 **/
@Getter
@RequiredArgsConstructor
public enum LogTypeEnum {

    /**
     * 正常日志类型
     */
    NORMAL(0, "正常日志"),

    /**
     * 错误日志类型
     */
    ERROR(9, "错误日志");

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 描述
     */
    private final String description;

}
