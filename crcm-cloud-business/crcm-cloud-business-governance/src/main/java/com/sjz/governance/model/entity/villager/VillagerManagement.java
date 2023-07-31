package com.sjz.governance.model.entity.villager;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 村民信息管理对象 ct_villager_management
 *
 * @author guozhilin
 * @date 2023-04-03
 */
@Setter
@Getter
@ToString
@ApiModel("村民信息管理")
@TableName("ct_villager_management")
public class VillagerManagement extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "ID")
    @Size(max = 32, message = "ID最多输入32个字符")
    private String id;

    /**
     * 村民姓名
     */
    @ApiModelProperty(value = "村民姓名", required = true)
    @NotBlank(message = "村民姓名不能为空")
    @Size(max = 64, message = "村民姓名最多输入64个字符")
    private String villagerName;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    /**
     * 性别 字典：SEX
     */
    @ApiModelProperty(value = "性别 字典：SEX")
    @Size(max = 10, message = "性别最多输入10个字符")
    private String sex;

    /**
     * 类别 字典：VILLAGER_TYPE
     */
    @ApiModelProperty(value = "类别 字典：VILLAGER_TYPE")
    @Size(max = 100, message = "类别 字典：VILLAGER_TYPE最多输入100个字符")
    private String type;

    /**
     * 所属管区id
     */
    @ApiModelProperty(value = "所属管区id")
    @Size(max = 64, message = "所属管区id最多输入64个字符")
    private String district;

    /**
     * 所属村id
     */
    @ApiModelProperty(value = "所属村id")
    @Size(max = 64, message = "所属村id最多输入64个字符")
    private String village;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    @Size(max = 20, message = "联系方式最多输入20个字符")
    private String phone;

    /**
     * 家庭住址
     */
    @ApiModelProperty(value = "家庭住址")
    @Size(max = 128, message = "家庭住址最多输入128个字符")
    private String address;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 20, message = "备注最多输入20个字符")
    private String remark;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

    @ApiModelProperty(value = "身份证号")
    private String idNum;

}
