package com.zsgf.platform.vo.company;

import com.crcm.core.constant.DicConstant;
import com.zsgf.platform.utils.UtilDic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName WasteInfoVo
 * @Description 危废基本信息
 * @Author GZL
 * @Date 2023/3/27 10:20
 * @Version 1.0
 **/
@Getter
@Setter
public class WasteInfoVo {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private String companyId;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private String companyName;

    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    private String directoryVersion;

    /**
     * 废物名称
     */
    @ApiModelProperty(value = "废物名称")
    private String wasteName;

    /**
     * 废物种类
     */
    @ApiModelProperty(value = "废物种类")
    private Integer wasteCategory;

    /**
     * 废物类别
     */
    @ApiModelProperty(value = "废物类别")
    private String wasteType;

    /**
     * 废物类别名称
     */
    @ApiModelProperty(value = "废物类别名称")
    private String wasteTypeName;

    /**
     * 废物代码
     */
    @ApiModelProperty(value = "废物代码")
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
     * 主要成分
     */
    @ApiModelProperty(value = "主要成分")
    private String component;

    /**
     * 有害成分
     */
    @ApiModelProperty(value = "有害成分")
    private String hazardousIngredients;

    /**
     * 危险废物形态
     */
    @ApiModelProperty(value = "危险废物形态")
    private String shape;

    /**
     * 危险废物产生环节
     */
    @ApiModelProperty(value = "危险废物产生环节")
    private String origin;

    /**
     * 禁忌与应急措施
     */
    @ApiModelProperty(value = "禁忌与应急措施")
    private String careful;

    /**
     * 处置方式大类
     */
    @ApiModelProperty(value = "处置方式大类")
    private String disposalMajor;

    /**
     * 处置方式小类
     */
    @ApiModelProperty(value = "处置方式小类")
    private String disposalSmall;

    /**
     * 危险类别（数据字典）
     */
    @ApiModelProperty(value = "危险类别（数据字典）")
    private String dangerousType;

    public String getShapeCh() {
        return UtilDic.getDictName(DicConstant.WASTE_SHAPE.CODE, this.getShape());
    }
}
