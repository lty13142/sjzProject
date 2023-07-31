package com.crcm.bpm.core.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName UserSelectorBean
 * @Description：用户选择器实例
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/17/15:25
 **/
@Setter
@Getter
@ToString
public class UserSelectorBean {
    /** 选择器类型 */
    private String type;
    /** 选择器类型名称 */
    private String typeName;
    /** 选择的名称 */
    private String name;
    /** 选择的值 */
    private String value;

}
