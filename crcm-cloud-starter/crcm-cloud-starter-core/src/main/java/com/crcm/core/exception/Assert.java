package com.crcm.core.exception;

import cn.hutool.core.collection.CollectionUtil;
import com.crcm.core.constant.enums.BaseStatusEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * <p>
 *      断言判断
 * </p>
 *
 * @author zyred
 * @createTime 2020/8/19 9:03
 * @company 中再云图技术有限公司
 **/
public class Assert {

    public static void isNull(Object entry, String msg){
        if (Objects.isNull(entry)){
            throw new CustomException(msg);
        }
    }

    public static void isNull(String msg, String... str) {
        if (StringUtils.isAllBlank(str)) {
            throw new CustomException(msg);
        }
    }

    public static void isEmpty(String str, String msg){
        if (StringUtils.isBlank(str)){
            throw new CustomException(msg);
        }
    }

    public static void bool(Boolean bool, String msg){
        if (bool){
            throw new CustomException(msg);
        }
    }

    public static void bool(Boolean bool, BaseStatusEnum statusEnum){
        if (bool){
            throw new CustomException(statusEnum.getCode(),statusEnum.getMsg());
        }
    }

    public static void anyEmpty(String msg, String... strs){
        if (StringUtils.isAnyBlank(strs)) {
            throw new CustomException(msg);
        }
    }

    public static void isEmpty(Collection coll, String msg){
        if (CollectionUtil.isEmpty(coll)){
            throw new CustomException(msg);
        }
    }

    public static void isEmpty(Map map, String msg){
        if (CollectionUtil.isEmpty(map)){
            throw new CustomException(msg);
        }
    }

    public static void collEmpty(List<?> coll, String msg){
        if (CollectionUtil.isEmpty(coll)){
            throw new CustomException(msg);
        }
    }

    public static void isUpdateSuccess(int update, String msg){
        if (update <= 0){
            throw new CustomException(msg);
        }
    }

    public static void isSuccess (int flag, String msg) {
        if (flag > 0) {
            throw new CustomException(msg);
        }
    }

    public static void isFailure (int flag, String msg) {
        if (flag < 0) {
            throw new CustomException(msg);
        }
    }

    /**
     * 校验 overrideId 不能是纯数字或数字开头
     * @param overrideId   id
     * @param msg          抛出异常的信息
     */
    public static void nonNumber(String overrideId, String msg) {
        try {
            Integer.valueOf(overrideId);
            throw new CustomException(msg);
        }catch (NumberFormatException ex){
            byte[] bytes = overrideId.getBytes();
            try {
                Integer.valueOf(new String(new byte[]{bytes[0]}));
                throw new CustomException(msg);
            } catch (NumberFormatException ex1){
                return;
            }
        }
    }

    /**
     * 校验多个事件
     * @param time          时间
     * @param msg           提示信息
     */
    public static void isEmpty(String msg, Date... time) {
        for (Date date : time) {
            isNull(date, msg);
        }
    }

    /**
     * 验证每个字符串是否相等
     *
     * @param strVal
     * @param credentials
     * @param msg
     */
    public static void equals(String strVal, String credentials, String msg) {
        strVal = strVal.replaceAll("\"", "");

        char[] chars = strVal.toCharArray();
        char[] chars1 = credentials.toCharArray();
        if (chars.length != chars1.length) {
            throw new CustomException(msg);
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars1[i]) {
                throw new CustomException(msg);
            }
        }
    }

    /**
     * 所有不为空
     * @param targets
     * @return
     */
    public static boolean isAllNotBlank(Object ... targets) {
        for (Object target : targets) {
            if (Objects.isNull(target)){
                return false;
            }
        }
        return true;
    }

    public static void isNotEmpty(Object target, String msg) {
        if (Objects.nonNull(target)){
            throw new CustomException(msg);
        }
    }

    public static void isSimpleTime(String time, String msg) {
        String simple = "yyyy-MM-dd HH:mm:ss";
        isNull(time, "时间不能为空！");
        bool(time.length() < simple.length(), msg);
    }
}