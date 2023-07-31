package com.zsgf.platform.vo;

import com.crcm.core.dto.TreeView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName WasteBaseInfoVo
 * @Description 危废名录树
 * @Author GZL
 * @Date 2023/3/20 10:29
 * @Version 1.0
 **/
@Data
public class WasteBaseInfoTreeVo {


    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    private String directoryVersion;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    private Integer level;

    /**
     * 废物种类 0:危废 1：固废 2 医废
     */
    @ApiModelProperty(value = "废物种类 0:危废 1：固废 2 医废")
    private Integer wasteCategory;

    /**
     * 废物类别
     */
    @ApiModelProperty(value = "废物类别", required = true)
    private String wasteType;

    /**
     * 废物类别名称
     */
    @ApiModelProperty(value = "废物类别名称", required = true)
    private String wasteTypeName;

    /**
     * 废物代码
     */
    @ApiModelProperty(value = "废物代码", required = true)
    private String wasteCode;

    /**
     * 废物代码名称
     */
    @ApiModelProperty(value = "废物代码名称")
    private String wasteCodeName;

    /**
     * 危险特性 腐蚀性C 毒性T 易燃性I 反应性R 感染性 In
     */
    @ApiModelProperty(value = "危险特性 腐蚀性C 毒性T 易燃性I 反应性R 感染性 In")
    private String hazards;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Long deleted;


    @ApiModelProperty(value = "子集列表")
    private List<WasteBaseInfoTreeVo> children;

}
