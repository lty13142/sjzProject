package com.sjz.partbuilding.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title:党费管理 </p>
 * <p>Description:党费管理 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company:中国再生资源 </p>
 *
 * @author ${Author}
 * @version 1.0
 * @Date 2020-09-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("党费管理")
@TableName("t_party_dues")
public class PartyDues extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id：varchar(32) ==》 id：String
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 姓名
     * name：varchar(32) ==》 name：String
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 应缴金额（元）
     * payable：decimal(19,2) ==》 payable：BigDecimal
     */
    @ApiModelProperty(value = "应缴金额（元）", example = "0")
    private BigDecimal payable;
    /**
     * 实缴金额（元）
     * paid_in：decimal(19,2) ==》 paidIn：BigDecimal
     */
    @ApiModelProperty(value = "实缴金额（元）", example = "0")
    private BigDecimal paidIn;
    /**
     * 缴纳时间
     * payment_time：varchar(32) ==》 paymentTime：String
     */
    @ApiModelProperty(value = "缴纳时间")
    private String paymentTime;
    /**
     * 备注
     * remarks：text ==》 remarks：String
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**
     * 组织id
     * org_id：varchar(32) ==》 orgId：String
     */
    @ApiModelProperty(value = "组织id")
    private String orgId;
    /**
     * 组织名称
     * org_name：varchar(128) ==》 orgName：String
     */
    @ApiModelProperty(value = "组织名称")
    private String orgName;
    /**
     * 几月党费
     * month：Date ==》 month：Date
     */
    @ApiModelProperty(value = "几月党费")
    private Date month;

    /**
     * 党组织id
     */
    private String partyOrgId;

    /**
     * 导入文件路径
     */
    @TableField(exist = false)
    private String path;

    /**
     * 导入文件名
     */
    @TableField(exist = false)
    private String fileName;

    /**
     * 日期模糊查询
     */
    @TableField(exist = false)
    private String monthStr;
    /**
     * 年份
     */
    @TableField(exist = false)
    private String year;

    @ApiModelProperty(value = "导入模板id")
    private String templateId;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;



    /**
     * 几月党费处理
     * @return
     */
//    public String getMonthCh() {
//        return UtilDic.getYearsDate(this.month);
//    }

}