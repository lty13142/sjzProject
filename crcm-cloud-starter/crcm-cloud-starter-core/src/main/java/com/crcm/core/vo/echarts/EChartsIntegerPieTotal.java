package com.crcm.core.vo.echarts;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName EChartsIntegerPieTotal
 * @Description ECharts饼图含总数
 * @Author GZL
 * @Date 2023/4/7 18:46
 * @Version 1.0
 **/
@Data
public class EChartsIntegerPieTotal {

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private Integer total;

    /**
     * 数据列表
     */
    @ApiModelProperty(value = "数据列表")
    private List<EChartsIntegerPie> dataList;
}
