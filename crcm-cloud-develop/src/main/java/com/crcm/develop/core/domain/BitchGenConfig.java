package com.crcm.develop.core.domain;

import lombok.Data;


@Data
public class BitchGenConfig {
    /**
     * 数据源ID
     */
    private String dataSourceId;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 作者
     */
    private String author;
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 表前缀
     */
    private String tablePrefix;
    /**
     * 前端传回来的JSON数据
     */
    private String jsonData;


}
