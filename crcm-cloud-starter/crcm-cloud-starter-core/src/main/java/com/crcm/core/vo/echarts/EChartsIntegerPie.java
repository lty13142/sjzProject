package com.crcm.core.vo.echarts;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName EChartsIntegerPie
 * @Description ECharts饼图
 * @Author GZL
 * @Date 2023/4/7 15:35
 * @Version 1.0
 **/
@Data
public class EChartsIntegerPie {

    /**
     * 数据名称
     */
    @ApiModelProperty(value = "数据名称")
    private String nameData;

    /**
     * 数据值
     */
    @ApiModelProperty(value = "数据值")
    private Integer valueData;

    /**
     * 数据类型代码
     */
    @ApiModelProperty(value = "数据类型代码")
    private String typeCode;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
}
