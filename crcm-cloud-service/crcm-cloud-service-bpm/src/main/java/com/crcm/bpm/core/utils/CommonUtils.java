package com.crcm.bpm.core.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName CommonUtils
 * @Description：通用工具类
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/10
 **/
public class CommonUtils {

    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String yyyy_MM_ddHHmmss = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime parseLocalDate(String localDateTimeStr, String pattern) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime parse = LocalDateTime.parse(localDateTimeStr, df);
            return parse;
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime parseLocalDateyyyyMMdd(String localDateTimeStr) {
        return  parseLocalDate(localDateTimeStr,yyyyMMdd);
    }

    public static LocalDateTime parseLocalDateyyyy_MM_ddHHmmss(String localDateTimeStr) {
        return  parseLocalDate(localDateTimeStr,yyyy_MM_ddHHmmss);
    }

    public static LocalDateTime formatDate(LocalDateTime localDateTime, String pattern) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime parse = LocalDateTime.parse(formatTime(localDateTime, pattern), df);
            return parse;
        } catch (Exception e) {
            return null;
        }
    }

    /* 获取指定时间的指定格式 */
    public static String formatTime(LocalDateTime time, String pattern) {
        if (time == null || pattern == null) {
            return null;
        }
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static long evalLong(Object obj) {
        if (obj == null) {
            return -1000;
        }
        return NumberUtils.toLong(obj.toString().replaceAll(" ", ""), -1000);
    }

    public static long evalLong(Object obj, long defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        return NumberUtils.toLong(obj.toString().replaceAll(" ", ""), defaultValue);
    }
}
