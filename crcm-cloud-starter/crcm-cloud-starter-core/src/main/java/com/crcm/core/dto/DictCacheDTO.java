package com.crcm.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DictCacheDTO
 * @Description 字典缓存类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/07/2020/7/1
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictCacheDTO {

    /**
     * 字典名称
     */
    private String name;
    /**
     * 字典中文名称
     */
    private String chName;
    /**
     * 字典值
     */
    private String value;
    /**
     * 备注
     */
    private String comments;
    /**
     * 字典代码
     */
    private String code;
}
