package com.crcm.admin.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;

import javax.validation.constraints.Size;

/**
 * 协议管理对象 sys_agreement
 *
 * @author cb
 * @date 2023-06-28
 */
@Setter
@Getter
@ToString
@ApiModel("协议管理")
@TableName("sys_agreement")
public class Agreement extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value = "主键id")
	@Size(max = 100, message = "主键id最多输入100个字符")
	private String id;

	/**
	 * 协议类型（AGREEMENT_TYPE:1、隐私政策 2、服务条款 3、用户手册 4、关于我们）
	 */
	@ApiModelProperty(value = "协议类型（AGREEMENT_TYPE:1、隐私政策 2、服务条款 3、用户手册 4、关于我们）")
	@Size(max = 100, message = "协议类型（AGREEMENT_TYPE:1、隐私政策 2、服务条款 3、用户手册 4、关于我们）最多输入100个字符")
	private String agreementType;

	/**
	 * 协议内容
	 */
	@ApiModelProperty(value = "协议内容")
	private String content;

	/**
	 * 是否删除
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "是否删除")
	private Integer deleted;

}
