package com.crcm.admin.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.admin.utils.UtilDic;
import com.crcm.core.constant.SystemConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName SysRole
 * @Description 系统角色
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/1
 **/
@Data
@ApiModel("角色表")
@TableName("sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     * id：bigint ==》 id：Long
     */
    @ApiModelProperty(value = "id", example = "0")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 16, message = "角色名称最多可输入16个字符")
    private String name;
    /**
     * 授权标识
     */
    @ApiModelProperty(value = "授权标识", required = true)
    @NotBlank(message = "授权标识不能为空")
    @Size(max = 32, message = "授权标识最多可输入32个字符")
    private String value;
    /**
     * 机构
     */
    @ApiModelProperty(value = "机构")
    @Size(max = 32, message = "机构最多可输入32个字符")
    private String organize;
    /**
     * 是否可用 1:是 0:否
     */
    @ApiModelProperty(value = "是否可用", example = "0")
    private Integer enabled;

    /**
     * 是否删除 1:是 0:否
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除 1:是 0:否", example = "0")
    private Integer deleted;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否可用中文")
    public String getEnabledCh() {
        return UtilDic.getDictName(SystemConstant.ENABLE_STATUS.CODE, this.enabled + "");
    }

}