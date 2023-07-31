package com.crcm.develop.tool.service;

import com.crcm.develop.tool.domain.GenTable;
import com.crcm.develop.tool.domain.GenTableColumn;

import java.util.List;

public interface DataTableService {

    /**
     * 查询数据库表
     * @param genTable
     * @param importedNames
     * @param datasourceId
     * @return
     */
    List<GenTable> findDbTableList(GenTable genTable, List<String> importedNames, String datasourceId);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> findDbTableListByNames(String[] tableNames, String datasourceId);

    List<GenTableColumn> selectDbTableColumnsByName(String tableName, String datasourceId);


}
