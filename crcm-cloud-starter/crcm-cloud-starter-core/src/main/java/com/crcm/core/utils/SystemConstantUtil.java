package com.crcm.core.utils;


import com.crcm.core.annotation.ChName;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.dto.ConstantData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UtilConstant
 * @Description 系统常量类工具
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/12/13
 **/
public class SystemConstantUtil {

    /**
     * 获取常量的中文名，即注解@ConstantName中的中文名称
     * @param value 常量值
     * @param constantClass 常量类
     * @return
     */
    public static String getChName(Object value, Class<?> constantClass) {
        // 常量的中文名
        String chName = "";
        try {
            // 通过反射获取静态变量里的所有属性
            Field[] fields = constantClass.getDeclaredFields();
            // 对比属性值获取名称
            for (Field field : fields) {
                String name = field.getName();
                Object val = field.get(name);
                if (value.equals(val)) {
                    // 获取属性值上面的注解
                    ChName annotation = field.getAnnotation(ChName.class);
                    if (annotation != null) {
                        // 返回注解值
                        chName = annotation.value();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chName;
    }

    /**
     * 获取常量的属性名
     * @param value 常量值
     * @param constantClass 常量类
     * @return
     */
    public static String getConstantName(Object value, Class<?> constantClass) {
        // 常量名
        String constantName = "";
        try {
            // 通过反射获取静态变量里的所有属性
            Field[] fields = constantClass.getDeclaredFields();
            // 对比属性值获取名称
            for (Field field : fields) {
                String name = field.getName();
                Object val = field.get(name);
                if (val.equals(value)) {
                    constantName = name;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return constantName;
    }

    /**
     * 获取系统常量类中所有的内部类的基本名称及代码
     * @param constantClass 系统常量类
     * @return 常量类所有内部类名称
     */
    public static List<ConstantData> getInnerClazzNames(Class<?> constantClass) {
        List<ConstantData> datas = new ArrayList<>();
        Class[] innerClazz = constantClass.getDeclaredClasses();
        for (Class clazz : innerClazz) {
            ConstantData data = new ConstantData();
            data.setName(clazz.getSimpleName());
            try {
                Field field = clazz.getField("CODE");
                String name = field.getName();
                String code = String.valueOf(field.get(name));
                data.setName(name);
                data.setCode(code);
                // 获取属性值上面的注解
                ChName annotation = field.getAnnotation(ChName.class);
                if (annotation != null) {
                    // 返回注解值
                    String chName = annotation.value();
                    data.setChName(chName);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            datas.add(data);
        }
        return datas;
    }

    /**
     * 获取系统常量类中某个内部常量类的数据
     * @param innerClazzName 内部类名称
     * @param constantClass 系统常量类
     * @return 指定内部常量类数据，name 名称，value 值， chName 中文名
     */
    public static List<ConstantData> getInnerClazzData(String innerClazzName, Class<?> constantClass) {
        ArrayList<ConstantData> dataList = new ArrayList<>();
        Class[] innerClazz = constantClass.getDeclaredClasses();
        for (Class clazz : innerClazz) {
            if (innerClazzName.equals(clazz.getSimpleName())) {
                // 通过反射获取内部类的所有属性
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    ConstantData data = new ConstantData();
                    String name = field.getName();
                    // 跳过常量类的CODE
                    if ("CODE".equals(name)) {
                        continue;
                    }
                    data.setCode(name);
                    data.setName(name);
                    try {
                        Object value = field.get(name);
                        data.setValue(value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    // 获取属性值上面的注解
                    ChName annotation = field.getAnnotation(ChName.class);
                    if (annotation != null) {
                        // 返回注解值
                        String chName = annotation.value();
                        data.setChName(chName);
                    }
                    dataList.add(data);
                }
            }
        }
        return dataList;
    }

    /**
     * 获取系统常量类中所有的code
     * @param constantClass 系统常量类
     * @return code数组
     */
    public static List<String> getConstantCodes(Class<?> constantClass) {
        List<String> codes = new ArrayList<>();
        Class[] innerClazz = constantClass.getDeclaredClasses();
        for (Class clazz : innerClazz) {
            // 通过反射获取内部类的所有属性
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                // 获取常量类的CODE
                if ("CODE".equals(name)) {
                    try {
                        Object value = field.get(name);
                        codes.add(String.valueOf(value));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return codes;
    }

    public static Map<String, List<ConstantData>> getAllConstantData() {

        Map<String, List<ConstantData>> dataMap = new HashMap<>();
        List<ConstantData> innerClazzNames = getInnerClazzNames(SystemConstant.class);
        innerClazzNames.stream().forEach(constantData -> {
            List<ConstantData> clazzData = getInnerClazzData(constantData.getCode(), SystemConstant.class);
            dataMap.put(constantData.getCode(),clazzData);
        });
        return dataMap;
    }


}
