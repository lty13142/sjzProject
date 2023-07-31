package com.zsgf.platform.vo.sheets;

import com.crcm.core.constant.SystemConstant;
import com.zsgf.platform.utils.UtilDic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName EquipmentSheetDetailVo
 * @Description 设备确认单详情
 * @Author GZL
 * @Date 2023/3/30 14:21
 * @Version 1.0
 **/
@Getter
@Setter
public class EquipmentSheetDetailVo {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private String companyId;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备类型")
    private String equipmentType;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String equipmentName;

    /**
     * 品牌及型号
     */
    @ApiModelProperty(value = "品牌及型号")
    private String brandModel;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer equipmentNumber;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * 序列号
     */
    @ApiModelProperty(value = "序列号")
    private String serialNumber;

    public String getEquipmentTypeCh() {
        return UtilDic.getDictName(SystemConstant.EQUIPMENT_SHEET_EQUIPMENT_TYPE.CODE, this.getEquipmentType());
    }
}
