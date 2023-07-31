package com.sjz.education.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 积分可兑换商品
 *  @author: sssccc
 * @TableName edu_products
 */
@TableName(value = "edu_products")
@Data
@Accessors(chain = true)
public class EduProducts extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    @ApiModelProperty(value = "商品名称",required = true)
    private String productName;

    /**
     * 展示图片地址
     */
    @NotBlank(message = "图片不能为空")
    @ApiModelProperty(value = "展示图片地址",required = true)
    private String image;

    /**
     * 商品分类：1：食品，2：生活用品，3：电器
     */
    @NotBlank(message = "商品分类不能为空")
    @ApiModelProperty(value = "商品分类：1：食品，2：生活用品，3：电器",required = true)
    private String productType;

    /**
     * 商品描述
     */
    @ApiModelProperty(value = "商品描述")
    private String details;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量")
    private Integer stock;

    /**
     * 兑换所需积分
     */
    @ApiModelProperty(value = "兑换所需积分")
    private Integer needPoints;

    /**
     * 商品状态：0：下架，1：上架
     */
    @ApiModelProperty(value = "商品状态：0：下架，1：上架")
    private Integer status;

    /**
     * 所属村庄
     */
    @ApiModelProperty(value = "所属村庄")
    private String villageName;
    /**
     * 版本号  乐观锁注解 每次修改操作都必须传入，version不一致则无法修改，修改后自增
     */
//    @Version
//    @TableField(fill = FieldFill.INSERT_UPDATE, update = "%s+1")
//    @ApiModelProperty(value = "版本号")
//    private Integer version;
    /***
     * 逻辑删除 不使用isDelete，避免RPC框架在反向解析时报错
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;

    /**
     * 历史兑换数量
     */
    @ApiModelProperty(value = "历史兑换数量")
    private Integer exchangeNumber;

    /**
     * 商品兑换次数
     */
    @ApiModelProperty(value = "商品兑换次数")
    private Integer exchangeCount;
    /**
     * 兑换所需积分查询，起始条件
     */
    @TableField(exist = false)
    private Integer startPoints;
    /**
     * 兑换所需积分查询，结束条件
     */
    @TableField(exist = false)
    private Integer endPoints;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}