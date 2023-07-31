package com.crcm.develop.tool.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.develop.tool.domain.GenTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务 数据层
 *
 * @author zzyt
 */
public interface GenTableMapper extends BaseMapper<GenTable> {



    /**
     * 查询表ID业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(String id);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    GenTable selectGenTableByName(String tableName);

    List<GenTable> selectDbTableList(GenTable genTable);

    Page<GenTable> selectPageGenTable(@Param("page") Page page, @Param("genTable") GenTable genTable);
}