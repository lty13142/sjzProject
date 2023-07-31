package com.crcm.bpm.api.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReportColumnVO implements Serializable {

    /**
     * 列类型名中文名称
     */
    private String columnTypeName;

    /**
     * 数据库字段名
     */
    private String value;
    /**
     * 字段得值
     */
    private String dataValue;

    /**
     * 一组类型
     */
    private String applyId;
}
