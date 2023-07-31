package com.sjz.evaluations.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 区域考核实体对象 kh_region_examine
 *
 * @author zzyt
 * @date 2023-04-25
 */
@Data
@Accessors(chain = true)
@TableName("kh_region_examine")
public class RegionExamine extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 指标id
     */
    @ApiModelProperty(value = "指标id")
    private String indicatorsId;

    /**
     * 指标类型(0:定性指标 1:定量指标)
     */
    @ApiModelProperty(value = "指标类型(0:定性指标 1:定量指标)")
    private Integer indicatorsType;

    /**
     * 管区区域code
     */
    @ApiModelProperty(value = "管区区域code")
    private String regionAreaCode;

    /**
     * 管区区域名称
     */
    @ApiModelProperty(value = "管区区域名称")
    private String regionAreaName;

    /**
     * 定性/定量目标
     */
    @ApiModelProperty(value = "定性/定量目标")
    private String target;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * 审核状态(0:未审核 1:已审核)
     */
    @ApiModelProperty(value = "审核状态(0:未审核 1:已审核)")
    private Integer status;

    /**
     * 镇级审核内容
     */
    @ApiModelProperty(value = "镇级审核内容")
    private String townContent;

}
