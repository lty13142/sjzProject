package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>Title:党建经费收支表 </p>
 * <p>Description:党建经费收支表 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 * @author ${Author}
 * @Date  2020-11-04
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("党建经费收支表")
@TableName("t_party_payments")
public class PartyPayments extends BaseEntity implements Serializable{
    private static final long serialVersionUID=1L;

    /**
     * ID
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "ID")
        @TableId(value = "id", type = IdType.ASSIGN_UUID)
        private String id;
    /**
     * 金额（元）
     * money：decimal(19,2) ==》 money：BigDecimal
     */
    @ApiModelProperty(value = "金额（元）", example = "0")
    private BigDecimal money;
    /**
     * 类型：收入，支出
     * type：varchar(2) ==》 type：String
     */
    @ApiModelProperty(value = "类型：收入，支出")
    private String type;
    /**
     * 组织id
     * org_id：varchar(32) ==》 orgId：String
     */
    @ApiModelProperty(value = "组织id")
    private String orgId;
    /**
     * 组织名
     * org_name：varchar(128) ==》 orgName：String
     */
    @ApiModelProperty(value = "组织名")
    private String orgName;
    /**
     * 收入支出时间
     * payment_time：varchar(64) ==》 paymentTime：String
     */
    @ApiModelProperty(value = "收入支出时间")
    private String paymentTime;
    /**
     * 备注
     * remarks：text ==》 remarks：String
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**
     * 党组织id
     * party_org_id：varchar(32) ==》 partyOrgId：String
     */
    @ApiModelProperty(value = "党组织id")
    private String partyOrgId;
    /**
     * 摘要标题
     * title：varchar(255) ==》 title：String
     */
    @ApiModelProperty(value = "摘要标题")
    private String title;


    /**
     * 结余（元）
     * money：balance(19,2) ==》 balance：BigDecimal
     */
    @ApiModelProperty(value = "结余", example = "0")
    private BigDecimal balance;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;
}