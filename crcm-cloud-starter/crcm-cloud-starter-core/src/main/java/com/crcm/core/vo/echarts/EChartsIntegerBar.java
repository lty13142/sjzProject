package com.crcm.core.vo.echarts;

import lombok.Data;

import java.util.List;

/**
 * @ClassName EChartsIntegerBar
 * @Description ECharts柱状图
 * @Author GZL
 * @Date 2023/4/7 15:38
 * @Version 1.0
 **/
@Data
public class EChartsIntegerBar {

    private List<String> xAxisData;

    private List<Integer> seriesData;
}
