package com.crcm.core.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.crcm.core.vo.echarts.EChartsIntegerBar;
import com.crcm.core.vo.echarts.EChartsIntegerPie;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName UtilECharts
 * @Description ECharts工具类
 * @Author GZL
 * @Date 2023/4/7 16:05
 * @Version 1.0
 **/
public class UtilECharts {

    /**
     * ECharts 数据补值
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/4/7 16:35
     * @Param list 原数据
     * @Param dicList 字典数据
     * @Param dicValueFunc 字典值函数
     * @Param dicNameFunc 字典名函数
     **/
    public static <T> List<EChartsIntegerPie> getFullEChartsData(List<EChartsIntegerPie> list, List<T> dicList, Function<T, String> dicValueFunc,
                                                                 Function<T, String> dicNameFunc) {
        List<EChartsIntegerPie> result = new ArrayList<>();
        if (CollectionUtil.isEmpty(dicList)) {
            return result;
        }
        Map<String, EChartsIntegerPie> map = CollectionUtil.isEmpty(list) ? new HashMap<>() :
                list.stream().collect(Collectors.toMap(EChartsIntegerPie::getTypeCode, Function.identity()));
        for (T t : dicList) {
            EChartsIntegerPie eChartsIntegerPie = map.get(dicValueFunc.apply(t));
            if (Objects.isNull(eChartsIntegerPie)) {
                eChartsIntegerPie = new EChartsIntegerPie();
                eChartsIntegerPie.setValueData(0);
                eChartsIntegerPie.setTypeCode(dicValueFunc.apply(t));
            }
            eChartsIntegerPie.setNameData(dicNameFunc.apply(t));
            result.add(eChartsIntegerPie);
        }
        return result;
    }

    /**
     * ECharts饼图转柱状图
     * @Author GZL
     * @Date 2023/4/10 14:21
     * @Param list 饼图数据
     * @return 柱状图数据
     **/
    public static EChartsIntegerBar echartsPieToBar(List<EChartsIntegerPie> list){
        EChartsIntegerBar echartsBar = new EChartsIntegerBar();
        List<String> xAxisData = new ArrayList<>();
        List<Integer> seriesData = new ArrayList<>();
        list.forEach(data -> {
            xAxisData.add(data.getNameData());
            seriesData.add(data.getValueData());
        });
        echartsBar.setXAxisData(xAxisData);
        echartsBar.setSeriesData(seriesData);
        return echartsBar;
    }
}
