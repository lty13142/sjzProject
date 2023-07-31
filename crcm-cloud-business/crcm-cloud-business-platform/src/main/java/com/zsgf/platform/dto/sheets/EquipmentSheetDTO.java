package com.zsgf.platform.dto.sheets;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @ClassName EquipmentSheetDTO
 * @Description 设备确认书查询DTO
 * @Author GZL
 * @Date 2023/3/30 9:34
 * @Version 1.0
 **/
@Data
public class EquipmentSheetDTO {
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id", hidden = true)
    private String companyId;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;

    /**
     * 所属区域代码
     */
    @ApiModelProperty(value = "所属区域代码", hidden = true)
    private String belongAreaCode;
}
