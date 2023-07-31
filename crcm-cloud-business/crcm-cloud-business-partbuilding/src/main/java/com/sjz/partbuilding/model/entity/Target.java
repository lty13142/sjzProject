package com.sjz.partbuilding.model.entity;



import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>Title:党员积分指标管理 </p>
 * @Date  2023-04-04
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("党员积分指标管理")
@TableName("dj_target")
public class Target extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * ID
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "ID")
        @TableId(value = "id", type = IdType.UUID)
        private String id;
    /**
     * 指标名称
     * name：varchar(64) ==》 name：String
     */
    @ApiModelProperty(value = "指标名称")
    private String name;
    /**
     * 是否启用
     * open_status：varchar(10) ==》 openStatus：String
     */
    @ApiModelProperty(value = "是否启用")
    private String openStatus;
    /**
     * 指标描述
     * desc：varchar(100) ==》 desc：String
     */
    @ApiModelProperty(value = "指标描述")
    private String description;
    /**
     * 分值
     * score：decimal(12,2) ==》 score：BigDecimal
     */
    @ApiModelProperty(value = "分值", example = "0")
    private BigDecimal score;
    /**
     * 备注
     * remark：varchar(400) ==》 remark：String
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 附件
     * attachment：varchar(500) ==》 attachment：String
     */
    @ApiModelProperty(value = "附件")
    private String attachment;

    @TableLogic
    private Integer deleted;


}