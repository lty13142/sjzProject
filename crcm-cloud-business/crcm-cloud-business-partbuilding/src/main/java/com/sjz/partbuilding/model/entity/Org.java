package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: 部门组织表</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 *
 * @author ${USER}
 * @version 1.0
 * @Date 2019-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("部门组织表")
@TableName("dj_sys_org")
public class Org extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 组织名称
     * name：varchar(255) ==》 name：String
     */
    @ApiModelProperty(value = "组织名称")
    private String name;
    /**
     * 组织代码
     * code：varchar(64) ==》 code：String
     */
    @ApiModelProperty(value = "组织代码")
    private String code;
    /**
     * 上级
     * pid：varchar(32) ==》 pid：String
     */
    @ApiModelProperty(value = "上级")
    private String pid;
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
     * 党委 -> 党总支 -> 党支部 -> 党小组
     * type：varchar(16) ==》 type：String
     */
    @ApiModelProperty(value = "组织类型")
    private String type;

    /**
     * 支部类别
     * 直属和地属
     * branch_type：int(1) ==》 branchType：Integer
     */
    @ApiModelProperty(value = "支部类别")
    private Integer branchType;
    /**
     * 直属支部名称
     * directly_branch：varchar(32) ==》 directlyBranch：String
     */
    @ApiModelProperty(value = "直属支部名称")
    private String directlyBranch;
    /**
     * 地属支部名称
     *
     * ground_branch：varchar(256) ==》 groundBranch：String
     */
    @ApiModelProperty(value = "地属支部名称")
    private String groundBranch;
    /**
     * 公司id
     * company_id：varchar(32) ==》 companyId：String
     */
    @ApiModelProperty(value = "公司id")
    private String companyId;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @Version
    private Integer version;

    @TableField(exist = false)
    private String branchTypeCh;
    @TableField(exist = false)
    private String typeCh;
    @TableField(exist = false)
    public List<Org> children;
    @TableField(exist = false)
    public Boolean orgType;

}