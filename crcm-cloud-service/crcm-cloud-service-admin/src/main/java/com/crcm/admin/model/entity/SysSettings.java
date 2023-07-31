package com.crcm.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统设置对象 sys_settings
 * true
 *
 * @author zzyt
 * @date 2021-06-25
 */
@Setter
@Getter
@ToString
@TableName("sys_settings")
public class SysSettings extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 设置名称
     */
    @ApiModelProperty(value = "设置名称")
    private String name;

    /**
     * 设置代码
     */
    @ApiModelProperty(value = "设置代码")
    private String code;

    /**
     * 设置值
     */
    @ApiModelProperty(value = "设置值")
    private String value;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Long deleted;

}
