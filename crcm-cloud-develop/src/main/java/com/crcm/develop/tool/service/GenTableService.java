package com.crcm.develop.tool.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.develop.tool.domain.GenTable;
import com.crcm.develop.tool.domain.GenTableColumn;

import java.util.List;
import java.util.Map;

/**
 * 业务 服务层
 *
 * @author zzyt
 */
public interface GenTableService extends IService<GenTable> {
    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
    Page<GenTable> selectPageGenTable(Page page, GenTable genTable);


    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableList(GenTable genTable);


    /**
     * 查询所有已经导入的表名
     *
     * @param datasourceId 数据库代码
     * @return 数据库表集合
     */
    List<GenTable> findTablesImported(String datasourceId);

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(String id);

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    void updateGenTable(GenTable genTable);

    /**
     * 删除业务信息
     *
     * @param tableIds 需要删除的表数据ID
     * @return 结果
     */
    void deleteGenTableByIds(String[] tableIds);

    /**
     * 导入表结构
     * @param tableList 导入表列表
     * @param columnMap 导入表列数据
     * @param datasourceId 数据库id
     */
    void importGenTable(List<GenTable> tableList, Map<String,List<GenTableColumn>> columnMap, String datasourceId);

    /**
     * 预览代码
     *
     * @param tableId 表编号
     * @return 预览数据列表
     */
    Map<String, String> previewCode(String tableId);

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    byte[] generatorCode(String tableName);

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    byte[] generatorCode(String[] tableNames);

    /**
     * 修改保存参数校验
     *
     * @param genTable 业务信息
     */
    void validateEdit(GenTable genTable);

}
