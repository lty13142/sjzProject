package com.zsgf.platform.model.entity.bill;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 电子联单对象 tbl_bill
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Setter
@Getter
@ToString
@ApiModel("电子联单")
@TableName("tbl_bill")
public class Bill extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Size(max = 200, message = "主键最多输入200个字符")
    private String id;

    /**
     * 管理计划id（预留）
     */
    @ApiModelProperty(value = "管理计划id（预留）")
    @Size(max = 200, message = "管理计划id（预留）最多输入200个字符")
    private String planId;

    /**
     * 省联单编号
     */
    @ApiModelProperty(value = "省联单编号")
    @Size(max = 200, message = "省联单编号最多输入200个字符")
    private String billCode;

    /**
     * 国家联单编号
     */
    @ApiModelProperty(value = "国家联单编号")
    @Size(max = 200, message = "国家联单编号最多输入200个字符")
    private String govCode;

    /**
     * 产生单位id
     */
    @ApiModelProperty(value = "产生单位id")
    @Size(max = 200, message = "产生单位id最多输入200个字符")
    private String productCompanyId;

    /**
     * 产废单位名称
     */
    @ApiModelProperty(value = "产废单位名称")
    @Size(max = 200, message = "产废单位名称最多输入200个字符")
    private String productCompanyName;

    /**
     * 接收单位id
     */
    @ApiModelProperty(value = "接收单位id")
    @Size(max = 200, message = "接收单位id最多输入200个字符")
    private String receiveCompanyId;

    /**
     * 经营单位名称
     */
    @ApiModelProperty(value = "经营单位名称")
    @Size(max = 200, message = "经营单位名称最多输入200个字符")
    private String receiveCompanyName;

    /**
     * 许可证主键
     */
    @ApiModelProperty(value = "许可证主键")
    @Size(max = 200, message = "许可证主键最多输入200个字符")
    private String licenseId;

    /**
     * 许可证编号
     */
    @ApiModelProperty(value = "许可证编号")
    @Size(max = 200, message = "许可证编号最多输入200个字符")
    private String licenseCode;

    /**
     * 许可证绑定id
     */
    @ApiModelProperty(value = "许可证绑定id")
    @Size(max = 200, message = "许可证绑定id最多输入200个字符")
    private String licenseBindingId;

    /**
     * 运单号
     */
    @ApiModelProperty(value = "运单号")
    @Size(max = 100, message = "运单号最多输入100个字符")
    private String transportNo;

    /**
     * 运输单位id
     */
    @ApiModelProperty(value = "运输单位id")
    @Size(max = 200, message = "运输单位id最多输入200个字符")
    private String transportCompanyId;

    /**
     * 运输单位名称
     */
    @ApiModelProperty(value = "运输单位名称")
    @Size(max = 200, message = "运输单位名称最多输入200个字符")
    private String transportCompanyName;

    /**
     * 计划转移日期
     */
    @ApiModelProperty(value = "计划转移日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planTransferDate;

    /**
     * 产生单位办理时间
     */
    @ApiModelProperty(value = "产生单位办理时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productCompanyHandleTime;

    /**
     * 发运人
     */
    @ApiModelProperty(value = "发运人")
    @Size(max = 200, message = "发运人最多输入200个字符")
    private String shipper;

    /**
     * 发运人手机
     */
    @ApiModelProperty(value = "发运人手机")
    @Size(max = 200, message = "发运人手机最多输入200个字符")
    private String shipperPhone;

    /**
     * 应急联系电话
     */
    @ApiModelProperty(value = "应急联系电话")
    @Size(max = 200, message = "应急联系电话最多输入200个字符")
    private String emergencyPhone;

    /**
     * 联单是否作废
     */
    @ApiModelProperty(value = "联单是否作废")
    @Size(max = 200, message = "联单是否作废最多输入200个字符")
    private String cancelFlag;

    /**
     * 联单作废理由说明
     */
    @ApiModelProperty(value = "联单作废理由说明")
    @Size(max = 200, message = "联单作废理由说明最多输入200个字符")
    private String cancelReason;

    /**
     * 1新建2安排运输3运输4确认数量5产废确认5_5处置上报国家6完结7环保确认8-环保退回
     */
    @ApiModelProperty(value = "1新建2安排运输3运输4确认数量5产废确认5_5处置上报国家6完结7环保确认8-环保退回")
    @Size(max = 200, message = "1新建2安排运输3运输4确认数量5产废确认5_5处置上报国家6完结7环保确认8-环保退回最多输入200个字符")
    private String billStatus;

    /**
     * 是否结束
     */
    @ApiModelProperty(value = "是否结束")
    @Size(max = 200, message = "是否结束最多输入200个字符")
    private String endFlag;

    /**
     * 联单类型(0.正常;1.补录普通;2.补录应急;3.补录特殊;4.补录跨境;00.跨省转入)
     */
    @ApiModelProperty(value = "联单类型(0.正常;1.补录普通;2.补录应急;3.补录特殊;4.补录跨境;00.跨省转入)")
    @Size(max = 200, message = "联单类型(0.正常;1.补录普通;2.补录应急;3.补录特殊;4.补录跨境;00.跨省转入)最多输入200个字符")
    private String billType;

    /**
     * 接收单位办理时间
     */
    @ApiModelProperty(value = "接收单位办理时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveCompanyHandleTime;

    /**
     * 产生单位退回时间
     */
    @ApiModelProperty(value = "产生单位退回时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productCompanyReturnTime;

    /**
     * 接收人
     */
    @ApiModelProperty(value = "接收人")
    @Size(max = 200, message = "接收人最多输入200个字符")
    private String receivePerson;

    /**
     * 接收时间
     */
    @ApiModelProperty(value = "接收时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTime;

    /**
     * 运输开始时间
     */
    @ApiModelProperty(value = "运输开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transportStartTime;

    /**
     * 运输结束时间
     */
    @ApiModelProperty(value = "运输结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transportEndTime;

    /**
     * 废物种类 0:危废 1：固废 2 医废
     */
    @ApiModelProperty(value = "废物种类 0:危废 1：固废 2 医废", required = true)
    @NotNull(message = "废物种类 0:危废 1：固废 2 医废不能为空")
    private Long wasteCategory;

    /**
     * 共享标识 省平台数据、本平台数据、其他平台数据
     */
    @ApiModelProperty(value = "共享标识 省平台数据、本平台数据、其他平台数据")
    @Size(max = 2, message = "共享标识 省平台数据、本平台数据、其他平台数据最多输入2个字符")
    private String dataFlag;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    private Integer deleted;

}
