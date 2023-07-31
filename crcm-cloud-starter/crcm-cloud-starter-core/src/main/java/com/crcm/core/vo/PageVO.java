package com.crcm.core.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页号
     */
    private int pageNumber;

    /**
     * 页面大小
     */
    private int pageSize;

    /**
     * 排序字段
     */
    private String sort;

    /**
     * 排序方式 asc/desc
     */
    private String order;
}
