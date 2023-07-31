package com.crcm.develop.core.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SQLHandler
 * @Description sql处理器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/6
 **/
@Slf4j
public class SQLHandler {
    /**
     * 解析SQL语句
     *
     * @param sql SQL语句
     * @return
     */
    public static SQLAndKey parseSQL(String sql) {
        //newSql是为了拼接sql语句，keyList为了存储键值
        StringBuilder newSql = new StringBuilder();
        List<String> keyList = new ArrayList<>();
        //解析SQL
        while (true) {
            //查找#{和}的位置
            int left = sql.indexOf("#{");
            int right = sql.indexOf("}");
            //判断两个符号的位置是否合法
            if (left != -1 && right != -1 && left < right) {
                //截取#{之前的字符串
                newSql.append(sql.substring(0, left));
                //sql后面追加?
                newSql.append("?");
                keyList.add(sql.substring(left + 2, right));
            } else {
                //证明已经没有#{和}成对出现了
                newSql.append(sql);
                break;
            }
            //将}及}之前的全部截取掉
            sql = sql.substring(right + 1);
        }
        return new SQLAndKey(newSql, keyList);
    }

    //==============SQL语句的拼接==================
    //分别负责map 和 domain类型的拼接
    private static void setMap(PreparedStatement pstate, Object obj, List<String> keyList) throws SQLException {
        Map map = (Map) obj;
        for (int i = 0; i < keyList.size(); i++) {
            pstate.setObject(i + 1, map.get(keyList.get(i)));
        }
    }

    private static void setObject(PreparedStatement pstat, Object obj, List<String> keyList) {
        try {
            //找到obj对应的类
            Class clazz = obj.getClass();
            for (int i = 0; i < keyList.size(); i++) {
                //找到key
                String key = keyList.get(i);
                //通过反射找到Obj对象中的属性
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);
                //获取私有属性的值
                Object value = field.get(obj);
                pstat.setObject(i + 1, value);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //设计一个方法，负责将SQL和问号组装完整
    //参数：pstat对象，Object对象，KeyList全部的Key

    /**
     * 将SQL和问号组装完整
     *
     * @param pstat   pstat对象
     * @param obj     参数对象
     * @param keyList 全部的Key
     * @throws SQLException
     */
    public static void handleParameter(PreparedStatement pstat, Object obj, List<String> keyList) throws SQLException {
        //1、通过反射获取obj对应的class
        if (obj == null) {
            return;
        }
        Class clazz = obj.getClass();
        //2、判断该clazz是什么类型
        if (clazz == int.class || clazz == Integer.class) {
            pstat.setInt(1, (Integer) obj);
        } else if (clazz == float.class || clazz == Float.class) {
            pstat.setFloat(1, (Float) obj);
        } else if (clazz == double.class || clazz == Double.class) {
            pstat.setString(1, (String) obj);
        } else if (clazz == String.class) {
            pstat.setString(1, (String) obj);
        } else if (clazz.isArray()) {

        } else {
            if (obj instanceof Map) {
                setMap(pstat, obj, keyList);
            } else {
                setObject(pstat, obj, keyList);
            }
        }
    }

    /**
     * 通过反射获取对象
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private static Map getMap(ResultSet rs) throws SQLException {
        //1、创建Map
        Map<String, Object> map = new HashMap<>();
        //2、获取结果集中的全部信息
        ResultSetMetaData rsmd = rs.getMetaData();
        //3、遍历结果集
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            //获取列名
            String columnName = rsmd.getColumnLabel(i);
            //获取列值
            Object value = rs.getObject(i);
            //存入map中 驼峰命名
            map.put(convertToCamelCase(StringUtils.lowerCase(columnName)), value);
        }
        return map;
    }

    private static Object getObject(ResultSet rs, Class resultType) throws SQLException {
        //1、创建Object
        Object obj = null;
        try {
            //2、通过反射创建对象
            obj = resultType.newInstance();
            //3、获取结果集中的全部信息
            ResultSetMetaData rsmd = rs.getMetaData();
            //4、遍历结果集
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                //获取列名
                String columnName = rsmd.getColumnLabel(i);

                //通过反射找到列名字对应的属性 驼峰命名
                Field field = null;
                try {
                    field = resultType.getDeclaredField(convertToCamelCase(StringUtils.lowerCase(columnName)));
                    //操作私有属性
                    field.setAccessible(true);
                    field.getClass();
                    //给属性赋值
                    field.set(obj, ConvertUtils.convert(rs.getObject(columnName), field.getType()));
                } catch (Exception e) {
//                    log.error(e.getMessage());
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 通过反射获取对象
     *
     * @param rs
     * @param resultType
     * @return
     * @throws SQLException
     */
    public static Object handleResult(ResultSet rs, Class resultType) throws SQLException {
        //1、通过反射创建对象
        Object result = null;
        if (resultType == int.class || resultType == Integer.class) {
            result = rs.getInt(1);
        } else if (resultType == float.class || resultType == Float.class) {
            result = rs.getFloat(1);
        } else if (resultType == double.class || resultType == Double.class) {
            result = rs.getDouble(1);
        } else if (resultType == long.class || resultType == Long.class) {
            result = rs.getLong(1);
        } else if (resultType == String.class) {
            result = rs.getString(1);
        } else {
            if (resultType == Map.class) {
                result = getMap(rs);
            } else {
                result = getObject(rs, resultType);
            }
        }
        return result;
    }

    /**
     * 下划线转驼峰命名
     */
    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母大写
            return name;
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            if (result.length() == 0) {
                result.append(camel.toLowerCase());
            } else {
                // 首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }

        }
        return result.toString();
    }

}
