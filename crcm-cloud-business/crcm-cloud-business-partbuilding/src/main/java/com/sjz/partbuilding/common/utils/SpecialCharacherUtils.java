package com.sjz.partbuilding.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @createTime 2021/4/13 17:45
 **/
public class SpecialCharacherUtils {

    public static String[] specialChar={"%","&","@"};

    public static String toMyString(String oldString){
        String newString=oldString;
        if(StringUtils.isEmpty(oldString)){
            return oldString;
        }
        for (String c : specialChar) {
            if(oldString.contains(c)){
                newString = newString.replaceAll(c, "\\" +"\\"+ c);
            }
        }
        return newString;
        /*if(!StringUtils.isEmpty(oldString)&&(oldString.contains("_")||oldString.contains("%")||oldString.contains("[]"))){
            int length = oldString.length();
            String repString = "\\";
            int flag = oldString.indexOf("%");
            String newString1 = oldString.substring(0, flag);
            String newString2 = oldString.substring(flag, length);
            String newString = newString1+repString+newString2;
            return newString;
        }else{
            return oldString;
        }*/
    }
}
