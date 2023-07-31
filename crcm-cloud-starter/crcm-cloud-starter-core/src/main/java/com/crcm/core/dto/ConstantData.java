package com.crcm.core.dto;

import lombok.Data;

/**
 * @ClassName: ConstantData
 * @Description 系统常量实体类
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/12/13
 **/
@Data
public class ConstantData {
    /**
     * 常量名称
     */
    private String name;
    /**
     * 常量代码
     */
    private String code;
    /**
     * 常量值
     */
    private Object value;
    /**
     * 中文名
     */
    private String chName;
}
