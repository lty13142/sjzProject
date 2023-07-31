package com.crcm.admin.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>Title:字典表 </p>
 * <p>Description:字典表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 *
 * @author ${USER}
 * @version 1.0
 * @Date 2019-05-14
 */
@Data
@ApiModel("字典表")
@TableName("sys_dict")
public class SysDict extends BaseEntity implements Serializable {

    /**
     * ID
     * id：bigint ==》 id：Long
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 父级id
     * pid：bigint ==》 id：Long
     */
    @ApiModelProperty(value = "父级id")
    private Long pid;
    /**
     * 字典名称
     * name：varchar(255) ==》 name：String
     */
    @Size(max = 255, message = "字典名称最多可输入255个字符")
    @ApiModelProperty(value = "字典名称")
    private String name;
    /**
     * 字典值
     * value：varchar(64) ==》 value：String
     */
    @Size(max = 64, message = "字典值最多可输入64个字符")
    @ApiModelProperty(value = "字典值")
    private String value;
    /**
     * 字典内容
     * comments：varchar(1000) ==》 comments：String
     */
    @Size(max = 1000, message = "字典内容多可输入1000个字符")
    @ApiModelProperty(value = "字典内容")
    private String comments;

    /**
     * 排列顺序
     * sort_order：int(11) ==》 sortOrder：Integer
     */
    @ApiModelProperty(value = "排列顺序", example = "0")
    private Integer sortOrder;
    /**
     * 字典代码
     * code：varchar(64) ==》 type：String
     */
    @Size(max = 64, message = "字典代码最多可输入64个字符")
    @ApiModelProperty(value = "字典代码")
    private String code;
    /**
     * 字典类型
     * type：varchar(16) ==》 type：String
     */
    @ApiModelProperty(value = "字典类型")
    private Integer type;
    /**
     * 是否启用 0未启用，1启用，-1停用
     * enabled：varchar(16) ==》 enabled：String
     */
    @ApiModelProperty(value = "是否启用 0未启用，1启用，-1停用")
    private Integer enabled;
    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;


}