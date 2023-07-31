package com.crcm.bpm.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @program: open-ai-center
 * @description:
 * @author: zxb
 * @create: 2021-05-14 17:25
 **/
public class FormDataUtil {

    private static final String replaceAttribute = "[{\"original\": \"\\\"listType\\\"\", \"target\": \"\\\"list-type\\\"\"}," +
            "{\"original\": \"\\\"valueFormat\\\"\", \"target\": \"\\\"value-format\\\"\"}," +
            "{\"original\": \"\\\"isRange\\\"\", \"target\": \"\\\"is-range\\\"\"}," +
            "{\"original\": \"\\\"startPlaceholder\\\"\", \"target\": \"\\\"start-placeholder\\\"\"}," +
            "{\"original\": \"\\\"endPlaceholder\\\"\", \"target\": \"\\\"end-placeholder\\\"\"}," +
            "{\"original\": \"\\\"stepStrictly\\\"\", \"target\": \"\\\"step-strictly\\\"\"}," +
            "{\"original\": \"\\\"autoUpload\\\"\", \"target\": \"\\\"auto-upload\\\"\"}]";

    // 格式话异常属性
    public static String replaceFormAttribute(String formJson) {
        if (!StringUtils.isEmpty(formJson)) {
            JSONArray replaceJSON = JSONArray.parseArray(replaceAttribute);
            for (int i = 0; i < replaceJSON.size(); i++) {
                JSONObject jsonObject = replaceJSON.getJSONObject(i);
                formJson = formJson.replaceAll(jsonObject.getString("original"), jsonObject.getString("target"));
            }
        }
        return formJson;
    }

    // 判断能否将对象转换为BigDecimal,若能则转换为BigDecimal返回
    public static Object toDecimal(Object value) {
        if (value != null && value instanceof String) {
            if (isBigDecimal(String.valueOf(value))) {
                BigDecimal bd = new BigDecimal(String.valueOf(value));
                return bd;
            }
        }
        return value;
    }

    // 判断字符串能否转换为BigDecimal
    public static boolean isBigDecimal(String str){
        if(str==null || str.trim().length() == 0){
            return false;
        }
        char[] chars = str.toCharArray();
        int sz = chars.length;
        int i = (chars[0] == '-') ? 1 : 0;
        if(i == sz) return false;

        if(chars[i] == '.') return false;//除了负号，第一位不能为'小数点'

        boolean radixPoint = false;
        for(; i < sz; i++){
            if(chars[i] == '.'){
                if(radixPoint) return false;
                radixPoint = true;
            }else if(!(chars[i] >= '0' && chars[i] <= '9')){
                return false;
            }
        }
        return true;
    }
}
