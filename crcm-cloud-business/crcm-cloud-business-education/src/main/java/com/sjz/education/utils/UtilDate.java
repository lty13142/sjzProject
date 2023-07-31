package com.sjz.education.utils;

import com.sjz.education.model.vo.StatisticsVO;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: sjz-project
 * @description: 日期工具类
 * @author: sssccc
 * @create: 2023-04-06 15:52
 **/
public class UtilDate {
    /**
     * 获取当月第一天
     * @return LocalDate
     */
    public static LocalDate firstDayOfMonth() {
        LocalDate currDate = LocalDate.now();
        // 当月第一天
        LocalDate firstDayOfMonth = currDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMonth);
        return firstDayOfMonth;
    }

    /**
     * 获取当月最后一天
     * @return LocalDate
     */
    public static LocalDate lastDayOfMonth() {
        LocalDate currDate = LocalDate.now();
        // 当月最后一天
        LocalDate lastDayOfMonth = currDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDayOfMonth);
        return lastDayOfMonth;
    }

    /**
     * 获取月度list
     * @return
     */
    public static List<StatisticsVO> monthDays(){
        List<StatisticsVO> list = new ArrayList<>();
        LocalDate date = LocalDate.now();
        int month = date.lengthOfMonth();
        for (int i = 1; i <= month; i++) {
            StatisticsVO statisticsVO = new StatisticsVO();
            statisticsVO.setName(LocalDate.of(date.getYear(),date.getMonthValue(),i).toString());
            list.add(statisticsVO);
        }
        return list;
    }

    /**
     * 获取周list
     * @return
     */
    public static List<StatisticsVO> weekDays(LocalDate startTime, LocalDate endTime){
        List<StatisticsVO> list = new ArrayList<>();
        while (startTime.isBefore(endTime)){
            StatisticsVO statisticsVO = new StatisticsVO();
            statisticsVO.setName(startTime.toString());
            list.add(statisticsVO);
            startTime = startTime.plusDays(1);
        }
        StatisticsVO statisticsVO = new StatisticsVO();
        statisticsVO.setName(endTime.toString());
        list.add(statisticsVO);
        return list;
    }

    /**
     * 获取年list
     * @return
     */
    public static List<StatisticsVO> yearDays(){
        List<StatisticsVO> list = new ArrayList<>();
        int i = 1;
        while (i <= 12){
            StatisticsVO statisticsVO = new StatisticsVO();
            statisticsVO.setName(YearMonth.of(Year.now().getValue(),i).toString());
            list.add(statisticsVO);
            i++;
        }
        return list;
    }

}
