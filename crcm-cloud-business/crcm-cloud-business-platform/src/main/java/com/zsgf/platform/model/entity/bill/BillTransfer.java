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
 * 电子联单运输信息对象 tbl_bill_transfer
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Setter
@Getter
@ToString
@ApiModel("电子联单运输信息")
@TableName("tbl_bill_transfer")
public class BillTransfer extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Size(max = 200, message = "主键最多输入200个字符")
    private String id;

    /**
     * 电子联单主键
     */
    @ApiModelProperty(value = "电子联单主键")
    @Size(max = 200, message = "电子联单主键最多输入200个字符")
    private String billId;

    /**
     * 驾驶人
     */
    @ApiModelProperty(value = "驾驶人")
    @Size(max = 255, message = "驾驶人最多输入255个字符")
    private String driverName;

    /**
     * 驾驶人手机
     */
    @ApiModelProperty(value = "驾驶人手机")
    @Size(max = 200, message = "驾驶人手机最多输入200个字符")
    private String driverPhone;

    /**
     * 运输工具（1:汽车、2:火车、3:轮船、 4:管道、 5:飞机、 6:其他）
     */
    @ApiModelProperty(value = "运输工具（1:汽车、2:火车、3:轮船、 4:管道、 5:飞机、 6:其他）")
    @Size(max = 255, message = "运输工具（1:汽车、2:火车、3:轮船、 4:管道、 5:飞机、 6:其他）最多输入255个字符")
    private String carType;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @Size(max = 255, message = "车牌号最多输入255个字符")
    private String plateNumber;

    /**
     * 运输起点
     */
    @ApiModelProperty(value = "运输起点")
    @Size(max = 255, message = "运输起点最多输入255个字符")
    private String transferStartPlace;

    /**
     * 运输终点
     */
    @ApiModelProperty(value = "运输终点")
    @Size(max = 255, message = "运输终点最多输入255个字符")
    private String transferEndPlace;

    /**
     * 运输经由地
     */
    @ApiModelProperty(value = "运输经由地")
    @Size(max = 255, message = "运输经由地最多输入255个字符")
    private String crossPlace;

    /**
     * 道路运输证号
     */
    @ApiModelProperty(value = "道路运输证号")
    @Size(max = 255, message = "道路运输证号最多输入255个字符")
    private String roadTransferCode;

    /**
     * 运输开始时间
     */
    @ApiModelProperty(value = "运输开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transferStartTime;

    /**
     * 运输结束时间
     */
    @ApiModelProperty(value = "运输结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transferEndTime;

    /**
     * 司机签名
     */
    @ApiModelProperty(value = "司机签名")
    @Size(max = 255, message = "司机签名最多输入255个字符")
    private String driverSign;

    /**
     * 运输顺序
     */
    @ApiModelProperty(value = "运输顺序")
    @Size(max = 20, message = "运输顺序最多输入20个字符")
    private String transferOrder;

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
