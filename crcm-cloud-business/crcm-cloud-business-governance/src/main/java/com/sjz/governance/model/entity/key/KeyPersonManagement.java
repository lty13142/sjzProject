package com.sjz.governance.model.entity.key;

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
 * 综合治理_重点人员管理对象 ct_key_person_management
 *
 * @author pengl
 * @date 2023-04-03
 */
@Setter
@Getter
@ToString
@ApiModel("综合治理_重点人员管理")
@TableName("ct_key_person_management")
public class KeyPersonManagement extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Size(max = 100, message = "姓名最多输入100个字符")
    private String name;

    /**
     * 村庄
     */
    @ApiModelProperty(value = "村庄")
    @Size(max = 100, message = "村庄最多输入100个字符")
    private String villageId;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别: 1男 2女")
    @Size(max = 8, message = "性别最多输入8个字符")
    private String gender;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Long age;

    /**
     * 出生年月
     */
    @ApiModelProperty(value = "出生年月")
    @Size(max = 100, message = "出生年月最多输入100个字符")
    private String birthday;

    /**
     * 重点人员类别
     */
    @ApiModelProperty(value = "重点人员类别")
    @Size(max = 8, message = "重点人员类别最多输入8个字符")
    private String keyPersonType;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    @Size(max = 100, message = "联系方式最多输入100个字符")
    private String phone;

    /**
     * 人脸图像
     */
    @ApiModelProperty(value = "人脸图像")
    @Size(max = 100, message = "人脸图像最多输入100个字符")
    private String faceUrl;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 100, message = "备注最多输入100个字符")
    private String remark;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;


    /**
     * 人脸注册id
     */
    @ApiModelProperty(value = "人脸注册id")
    private Integer faceRegisterPersonId;

    /**
     * 人脸注册数据库id
     */
    @ApiModelProperty(value = "人脸注册数据库id")
    private String faceRegisterDatabaseId;

}
