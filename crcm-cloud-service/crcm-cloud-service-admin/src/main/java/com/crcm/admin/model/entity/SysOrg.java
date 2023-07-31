package com.crcm.admin.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.core.constant.SystemConstant;
import com.crcm.admin.utils.UtilDic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SysOrg
 * @Description 部门组织
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/1
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("部门组织表")
@TableName("sys_org")
public class SysOrg extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id：bigint(20) ==》 id：Long
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 组织名称
     * name：varchar(255) ==》 name：String
     */
    @ApiModelProperty(value = "组织名称")
    @NotBlank(message = "组织名称不能为空")
    @Size(min = 0, max = 255, message = "组织名称长度不能超过30个字符")
    private String name;
    /**
     * 组织代码
     * code：varchar(64) ==》 code：String
     */
    @ApiModelProperty(value = "组织代码")
    private String code;
    /**
     * 上级
     * pid：bigint(20) ==》 pid：Long
     */
    @ApiModelProperty(value = "上级")
    private Long pid;
    /**
     * 编号
     * number：varchar(32) ==》 number：String
     */
    @ApiModelProperty(value = "编号")
    private String number;
    /**
     * 备注
     * comments：varchar(2000) ==》 comments：String
     */
    @ApiModelProperty(value = "备注")
    private String comments;

    /**
     * 组织类型
     * type：varchar(16) ==》 type：String
     */
    @ApiModelProperty(value = "组织类型")
    private String type;

    /**
     * 图标
     * icon：varchar(255) ==》 icon：String
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    /***
     * 逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @ApiModelProperty(value = "支部类别 0直属支部 1地属 2团支部 3工会")
    private String branchType;

    @ApiModelProperty(value = "直属支部名称")
    private String directlyBranch;

    @ApiModelProperty(value = "地属支部名称")
    private String groundBranch;

    @ApiModelProperty(value = "数据类型：1-党建，2-其他")
    private String dataType;

    @TableField(exist = false)
    private String branchTypeCh;
    @TableField(exist = false)
    private String typeCh;
    @TableField(exist = false)
    public List<SysOrg> children;
    @TableField(exist = false)
    public Boolean orgType;


    public String getTypeCh() {
        return UtilDic.getDictName(SystemConstant.ORG_TYPE.CODE, this.type);
    }

}