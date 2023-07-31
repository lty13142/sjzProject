package com.crcm.develop.tool.mapper;

import com.crcm.develop.tool.domain.GenTable;
import com.crcm.develop.tool.domain.GenTableColumn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName DataTableMapper
 * @Description 数据表查询
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2020/07/2020/7/19
 **/
public interface DataTableMapper {

    List<GenTable> selectDbTableList(@Param("genTable") GenTable genTable,@Param("importedNames") List<String> importedNames);

    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);
}
