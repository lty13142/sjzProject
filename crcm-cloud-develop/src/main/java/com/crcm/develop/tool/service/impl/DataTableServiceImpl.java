package com.crcm.develop.tool.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.crcm.develop.core.jdbc.JDBCUtil;
import com.crcm.develop.tool.domain.GenTable;
import com.crcm.develop.tool.domain.GenTableColumn;
import com.crcm.develop.tool.entity.DatasourceConfEntity;
import com.crcm.develop.tool.mapper.DataTableMapper;
import com.crcm.develop.tool.service.DataTableService;
import com.crcm.develop.tool.service.DatasourceConfService;
import com.crcm.develop.tool.util.UtilAES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName DataTableServiceImpl
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2020/07/2020/7/19
 **/
@Service
public class DataTableServiceImpl implements DataTableService {
    @Autowired
    private DataTableMapper dataTableMapper;
    @Autowired
    private DatasourceConfService datasourceConfService;

    @Override
    public List<GenTable> findDbTableList(GenTable genTable, List<String> importedNames,String datasourceId) {
        StringBuilder sql = new StringBuilder("select table_name, table_comment, create_time, update_time from information_schema.tables " +
                "  where table_schema = (select database()) ");
        if (CollectionUtil.isNotEmpty(importedNames)) {
            sql.append("AND table_name NOT IN (");
            importedNames.stream().forEach(name -> sql.append("'").append(name).append("' ").append(","));
            // 删除最后的逗号
            sql.delete(sql.lastIndexOf(","),sql.length());
            sql.append(" )");
        }
        return executeQuerySql(sql.toString(),datasourceId,GenTable.class);
    }


    @Override
    public List<GenTable> findDbTableListByNames(String[] tableNames, String datasourceId) {
        StringBuilder sql = new StringBuilder("select table_name, table_comment, create_time, update_time from information_schema.tables" +
                " where table_schema = (select database()) and table_name in ( ");
        if (tableNames != null ) {
            Arrays.stream(tableNames).forEach(name -> sql.append(" '").append(name).append("' ").append(","));
            if (sql.toString().endsWith(",")) {
                sql.delete(sql.lastIndexOf(","),sql.length());
            }
        }
        sql.append(");");
        return executeQuerySql(sql.toString(),datasourceId,GenTable.class);
    }

    private <T> List<T> executeQuerySql(String sql ,String datasourceId, Class<T> clazz) {
        DatasourceConfEntity datasource = datasourceConfService.getById(datasourceId);
        List<T> list = new ArrayList<>();
        if (datasource != null) {
            JDBCUtil jdbcUtil = JDBCUtil.getInstance();
            Connection connection = null;
            try {
                connection = jdbcUtil.getConnection(datasource.getUrl(), datasource.getUsername(), UtilAES.aesDecrypt(datasource.getPassword()), jdbcUtil.getDriverByType(datasource.getDbType()));
                if (connection != null) {
                    list = jdbcUtil.selectList(connection, sql, null, clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<GenTableColumn> selectDbTableColumnsByName(String tableName, String datasourceId) {
        StringBuilder sql = new StringBuilder(" select column_name, (case when (is_nullable = 'no' && column_key != 'PRI') then '1' else null end ) " +
                " as is_required, (case when column_key = 'PRI' then '1' else '0' end) as is_pk, ordinal_position as sort, column_comment, (case when extra = 'auto_increment' " +
                " then '1' else '0' end) as is_increment, column_type from information_schema.columns " +
                " where table_schema = (select database()) ");
        sql.append("and table_name = '").append(tableName).append("' ").append(" order by ordinal_position");
        return executeQuerySql(sql.toString(),datasourceId,GenTableColumn.class);
    }
}
