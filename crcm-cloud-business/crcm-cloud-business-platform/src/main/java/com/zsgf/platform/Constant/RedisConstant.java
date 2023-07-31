package com.zsgf.platform.Constant;

import com.crcm.core.constant.SystemBaseConstants;

/**
 * Redis 常量
 */
public interface RedisConstant {
    /**
     * redis模块根路径
     */
    String BASE_PATH = SystemBaseConstants.BASE_PATH + ":BUSINESS";
    /**
     * 行政区划redis key
     */
    String AREA_CODE = BASE_PATH + ":AREA_CODE";

}
