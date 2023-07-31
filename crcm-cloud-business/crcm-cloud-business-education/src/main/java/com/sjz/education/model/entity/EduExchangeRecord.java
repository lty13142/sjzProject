package com.sjz.education.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 德育积分_积分兑换记录
 * @author: sssccc
 * @TableName edu_exchange_record
 */
@TableName(value ="edu_exchange_record")
@Data
@Accessors(chain = true)
public class EduExchangeRecord extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 村民id
     */
    private String userId;

    /**
     * 村民姓名
     */
    private String userName;

    /**
     * 兑换商品id
     */
    @NotBlank(message = "商品id不能为空")
    private String productsId;

    /**
     * 所需积分
     */
    private Integer needPoint;

    /**
     * 总积分消耗
     */
    private Integer totalPoint;

    /**
     * 兑换数量
     */
    private Integer exchangeNumber;

    /**
     * 兑换码
     */
    private String exchangeCode;

    /**
     * 核销码
     */
    private String writeOffCode;

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

    /***
     * 商品详情
     */
    @TableField(exist = false)
    private EduProducts products;

    /***
     * 是否核销：0未核销，1已核销
     */
    @TableField(exist = false)
    private Integer status;
    /***
     * 村庄名称
     */
    @TableField(exist = false)
    private String villageName;
    /***
     * 商品类型
     */
    @TableField(exist = false)
    private String productsType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}