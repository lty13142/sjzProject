package com.crcm.develop.core.jdbc;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SQLAndKey
 * @Description 存储SQL和SQL里面的key
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/5/6
 **/
public class SQLAndKey {
    private StringBuilder sql = new StringBuilder();
    //采用List集合是因为List集合是有序的，能够确认参数的顺序
    private List<String> keyList = new ArrayList<>();

    public SQLAndKey(StringBuilder sql, List<String> keyList) {
        this.sql = sql;
        this.keyList = keyList;
    }

    public String getSQL() {
        return sql.toString();
    }

    public List<String> getKeyList() {
        return keyList;
    }
}
