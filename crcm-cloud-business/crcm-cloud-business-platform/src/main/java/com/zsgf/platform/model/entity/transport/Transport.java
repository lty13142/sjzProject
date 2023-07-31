package com.zsgf.platform.model.entity.transport;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 废物运输信息对象 tbl_transport
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Setter
@Getter
@ToString
@ApiModel("废物运输信息")
@TableName("tbl_transport")
public class Transport extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Size(max = 32, message = "id最多输入32个字符")
    private String id;

    /**
     * 运输公司id
     */
    @ApiModelProperty(value = "运输公司id", required = true)
    @NotBlank(message = "运输公司id不能为空")
    @Size(max = 36, message = "运输公司id最多输入36个字符")
    private String companyId;

    /**
     * 运输类型：1-危废运输 2 固废运输 3-医废运输
     */
    @ApiModelProperty(value = "运输类型：1-危废运输 2 固废运输 3-医废运输")
    private Long transportType;

    /**
     * 运输编号
     */
    @ApiModelProperty(value = "运输编号", required = true)
    @NotBlank(message = "运输编号不能为空")
    @Size(max = 50, message = "运输编号最多输入50个字符")
    private String transportNo;

    /**
     * 运输司机姓名
     */
    @ApiModelProperty(value = "运输司机姓名", required = true)
    @NotBlank(message = "运输司机姓名不能为空")
    @Size(max = 20, message = "运输司机姓名最多输入20个字符")
    private String driverName;

    /**
     * 司机电话号码
     */
    @ApiModelProperty(value = "司机电话号码", required = true)
    @NotBlank(message = "司机电话号码不能为空")
    @Size(max = 20, message = "司机电话号码最多输入20个字符")
    private String driverPhone;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank(message = "车牌号不能为空")
    @Size(max = 20, message = "车牌号最多输入20个字符")
    private String plateNumber;

    /**
     * 运输危废总量
     */
    @ApiModelProperty(value = "运输危废总量")
    @Digits(integer = 15, fraction = 4, message = "运输危废总量整数最多15位，小数最多4位")
    private BigDecimal totalWeight;

    /**
     * 开始地点
     */
    @ApiModelProperty(value = "开始地点")
    @Size(max = 255, message = "开始地点最多输入255个字符")
    private String transportStartPlace;

    /**
     * 结束地点
     */
    @ApiModelProperty(value = "结束地点")
    @Size(max = 255, message = "结束地点最多输入255个字符")
    private String transportEndPlace;

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
     * 运输状态
     */
    @ApiModelProperty(value = "运输状态")
    @Size(max = 5, message = "运输状态最多输入5个字符")
    private String status;

    /**
     * 备注（预留）
     */
    @ApiModelProperty(value = "备注（预留）")
    @Size(max = 255, message = "备注（预留）最多输入255个字符")
    private String remark;

    /**
     * 司机状态  0-未接单 1-已接单 2-已完成
     */
    @ApiModelProperty(value = "司机状态  0-未接单 1-已接单 2-已完成")
    private Long driverStatus;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    private Integer deleted;

}
