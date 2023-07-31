package com.crcm.develop.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.develop.tool.domain.GenTableColumn;
import com.crcm.develop.tool.mapper.GenTableColumnMapper;
import com.crcm.develop.tool.service.GenTableColumnService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务字段 服务层实现
 *
 * @author zzyt
 */
@Service
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn> implements GenTableColumnService {

    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(String tableId) {

        return this.baseMapper.selectGenTableColumnListByTableId(tableId);
    }

    /**
     * 新增业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public int insertGenTableColumn(GenTableColumn genTableColumn) {
        return this.baseMapper.insert(genTableColumn);
    }

    /**
     * 修改业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public int updateGenTableColumn(GenTableColumn genTableColumn) {

        return this.baseMapper.updateById(genTableColumn);
    }

    /**
     * 删除业务字段对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGenTableColumnByIds(List<String> ids) {
        return this.baseMapper.deleteBatchIds(ids);
    }

    @Override
    public int deleteByTableIds(List<String> tableIds) {
        LambdaQueryWrapper<GenTableColumn> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(GenTableColumn::getTableId, tableIds);
        return this.baseMapper.delete(wrapper);
    }
}