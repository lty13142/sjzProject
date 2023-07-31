package com.sjz.education.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: sjz-project
 * @description: echarts返回体VO类
 * @author: sssccc
 * @create: 2023-04-06 15:45
 **/
@Data
public class EchartsVO implements Serializable {
    /**
     * 名称
     */
    private String name;
    /**
     * 数值1
     */
    private Integer value;

    /**
     * 数值2
     */
    private Integer value2;
}
