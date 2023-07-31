package com.sjz.evaluations.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 基层组织管理对象 gr_organization_management
 *
 * @author zzyt
 * @date 2023-04-03
 */
@Setter
@Getter
@ToString
@ApiModel("基层组织管理")
@TableName("gr_organization_management")
public class GrOrganizationManagement extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    @Size(max = 64, message = "主键最多输入64个字符")
    private String id;

    /** 组织名称 */
        @ApiModelProperty(value = "组织名称")
    @Size(max = 64, message = "组织名称最多输入64个字符")
    private String name;

    /** 组织编号 */
        @ApiModelProperty(value = "组织编号")
    @Size(max = 64, message = "组织编号最多输入64个字符")
    private String code;

    /** 所属村 */
        @ApiModelProperty(value = "所属村")
    @Size(max = 64, message = "所属村最多输入64个字符")
    private String village;

    /** 联系人 */
        @ApiModelProperty(value = "联系人")
    @Size(max = 64, message = "联系人最多输入64个字符")
    private String contacts;

    /** 联系方式 */
        @ApiModelProperty(value = "联系方式")
    @Size(max = 64, message = "联系方式最多输入64个字符")
    private String contactInformation;

    /** 经度 */
        @ApiModelProperty(value = "经度")
    @Size(max = 64, message = "经度最多输入64个字符")
    private String longitude;

    /** 维度 */
        @ApiModelProperty(value = "维度")
    @Size(max = 64, message = "维度最多输入64个字符")
    private String latitude;

    /** 父ID */
    @ApiModelProperty(value = "父ID")
    @Size(max = 64, message = "父ID最多输入64个字符")
    private String pid;

    /** 备注 */
    @ApiModelProperty(value = "备注")
    @Size(max = 64, message = "备注最多输入64个字符")
    private String remarks;

    /***
     * 逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @TableField(exist = false)
    @ApiModelProperty(value = "管区名称")
    private String dName;

}
