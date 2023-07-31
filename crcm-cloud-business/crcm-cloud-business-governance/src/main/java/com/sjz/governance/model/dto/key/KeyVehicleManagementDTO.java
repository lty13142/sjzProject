package com.sjz.governance.model.dto.key;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 综合治理_重点车辆管理对象Vo
 *
 * @author pengl
 * @date 2023-04-03
 */
@Data
public class KeyVehicleManagementDTO  {
    /**
     * 车辆品牌
     */
    @ApiModelProperty(value = "车辆品牌")
    private String vehicleBrand;

    /**
     * 颜色
     */
    @ApiModelProperty(value = "颜色")
    private String color;

    /**
     * 车牌号码
     */
    @ApiModelProperty(value = "车牌号码")
    private String plateNumber;
}
