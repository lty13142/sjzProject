package com.zsgf.platform.model.entity.collect;

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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业收集台账对象 tbl_collect_record
 *
 * @author gzl
 * @date 2023-03-23
 */
@Setter
@Getter
@ToString
@ApiModel("企业收集台账")
@TableName("tbl_collect_record")
public class CollectRecord extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    @Size(max = 32, message = "id最多输入32个字符")
    private String id;

    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id", required = true)
    @NotBlank(message = "公司id不能为空")
    @Size(max = 64, message = "公司id最多输入64个字符")
    private String companyId;

    /**
     * 类型：1 产废车间，2 医院科室
     */
    @ApiModelProperty(value = "类型：1 产废车间，2 医院科室")
    @Size(max = 10, message = "类型：1 产废车间，2 医院科室最多输入10个字符")
    private String type;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    @Size(max = 32, message = "部门id最多输入32个字符")
    private String deptId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    @Size(max = 100, message = "部门名称最多输入100个字符")
    private String deptName;

    /**
     * 废物名称
     */
    @ApiModelProperty(value = "废物名称", required = true)
    @NotBlank(message = "废物名称不能为空")
    @Size(max = 100, message = "废物名称最多输入100个字符")
    private String wasteName;

    /**
     * 废物类别
     */
    @ApiModelProperty(value = "废物类别")
    @Size(max = 255, message = "废物类别最多输入255个字符")
    private String wasteType;

    /**
     * 废物代码
     */
    @ApiModelProperty(value = "废物代码")
    @Size(max = 100, message = "废物代码最多输入100个字符")
    private String wasteCode;

    /**
     * 废物重量
     */
    @ApiModelProperty(value = "废物重量")
    @Digits(integer = 15, fraction = 4, message = "废物重量整数最多15位，小数最多4位")
    private BigDecimal weight;

    /**
     * 重量单位(字典：weight_unit)
     */
    @ApiModelProperty(value = "重量单位(字典：weight_unit)")
    @Size(max = 2, message = "重量单位(字典：weight_unit)最多输入2个字符")
    private String unit;

    /**
     * 经办人
     */
    @ApiModelProperty(value = "经办人")
    @Size(max = 100, message = "经办人最多输入100个字符")
    private String agentPerson;

    /**
     * 收集编号
     */
    @ApiModelProperty(value = "收集编号")
    @Size(max = 100, message = "收集编号最多输入100个字符")
    private String collectNo;

    /**
     * 收集人
     */
    @ApiModelProperty(value = "收集人")
    @Size(max = 100, message = "收集人最多输入100个字符")
    private String collectPerson;

    /**
     * 收集时间
     */
    @ApiModelProperty(value = "收集时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date collectTime;

    /**
     * 最终去向
     */
    @ApiModelProperty(value = "最终去向")
    @Size(max = 255, message = "最终去向最多输入255个字符")
    private String whereabouts;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @Size(max = 10, message = "状态最多输入10个字符")
    private String status;

    /**
     * 签名图片
     */
    @ApiModelProperty(value = "签名图片")
    @Size(max = 200, message = "签名图片最多输入200个字符")
    private String signPic;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
