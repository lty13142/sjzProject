package com.crcm.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @ClassName DateUtil
 * @Description 时间工具
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
public class DateUtil {
    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String FULL_YEAR_MONTH_DAY = "yyyy-MM-dd";

    public static final String CST_TIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    /**
     * 格式化时间，格式为 yyyyMMddHHmmss
     *
     * @param localDateTime LocalDateTime
     * @return 格式化后的字符串
     */
    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    /**
     * 根据传入的格式，格式化时间
     *
     * @param localDateTime LocalDateTime
     * @param format        格式
     * @return 格式化后的字符串
     */
    public static String formatFullTime(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 根据传入的格式，格式化时间
     *
     * @param date   Date
     * @param format 格式
     * @return 格式化后的字符串
     */
    public static String getDateFormat(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化 CST类型的时间字符串
     *
     * @param date   CST类型的时间字符串
     * @param format 格式
     * @return 格式化后的字符串
     * @throws ParseException 异常
     */
    public static String formatCstTime(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CST_TIME_PATTERN, Locale.US);
        Date usDate = simpleDateFormat.parse(date);
        return getDateFormat(usDate, format);
    }

    /**
     * 格式化 Instant
     *
     * @param instant Instant
     * @param format  格式
     * @return 格式化后的字符串
     */
    public static String formatInstant(Instant instant, String format) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 判断当前时间是否在指定时间范围
     *
     * @param from 开始时间
     * @param to   结束时间
     * @return 结果
     */
    public static boolean between(LocalTime from, LocalTime to) {
        LocalTime now = LocalTime.now();
        return now.isAfter(from) && now.isBefore(to);
    }


    /**
     * 根据格式化类型获取String 时间
     * @param format
     * @return
     */
    public static String getDate(String format) {
        Date _date = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat(format);
        return df.format(_date);
    }


    /**
     * 将String时间转成date时间
     * @param date
     * @param pattern
     * @return
     */
    public static Date parse(String date, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 时间格式验证（不精确）  yyyy-MM-dd hh:mm:ss
     * @Author GZL
     * @Date 2023/2/10 10:27
     * @Param dateTime 数据
     * @return 验证结果
     **/
    public static boolean patternMatchesDateTime(String dateTime){
        if(StringUtils.isBlank(dateTime)){
            return false;
        }
        return Pattern.matches("^([12])\\d{3}-((0[1-9])|(1[0-2]))-((0[1-9])|([12]\\d)|(3[01]))" +
                "\\s(([01]\\d)|(2[0-3])):[0-5]\\d:[0-5]\\d$",dateTime);
    }
}
