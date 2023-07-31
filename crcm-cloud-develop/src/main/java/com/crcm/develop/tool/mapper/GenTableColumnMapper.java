package com.crcm.develop.tool.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.develop.tool.domain.GenTableColumn;

import java.util.List;

/**
 * 业务字段 数据层
 *
 * @author zzyt
 */
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn> {
    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    public List<GenTableColumn> selectGenTableColumnListByTableId(String tableId);

}