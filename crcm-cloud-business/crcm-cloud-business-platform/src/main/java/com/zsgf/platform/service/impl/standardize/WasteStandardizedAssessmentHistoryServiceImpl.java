package com.zsgf.platform.service.impl.standardize;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.standardize.WasteStandardizedAssessmentHistoryMapper;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentHistory;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规范化考核评分记录Service业务层处理
 *
 * @author gzl
 * @date 2023-03-24
 */
@Service
public class WasteStandardizedAssessmentHistoryServiceImpl extends ServiceImpl<WasteStandardizedAssessmentHistoryMapper, WasteStandardizedAssessmentHistory> implements WasteStandardizedAssessmentHistoryService {


    /**
     * 新增规范化考核评分记录
     *
     * @param wasteStandardizedAssessmentHistory 规范化考核评分记录
     * @return 结果
     */
    @Override
    public boolean saveWasteStandardizedAssessmentHistory(WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory) {
        return this.save(wasteStandardizedAssessmentHistory);
    }

    /**
     * 修改规范化考核评分记录
     *
     * @param wasteStandardizedAssessmentHistory 规范化考核评分记录
     * @return 结果
     */
    @Override
    public boolean updateWasteStandardizedAssessmentHistory(WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory) {
        return this.updateById(wasteStandardizedAssessmentHistory);
    }

    /**
     * 删除规范化考核评分记录信息
     *
     * @param id 规范化考核评分记录ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteStandardizedAssessmentHistory(String id) {
        return this.removeById(id);
    }

    /**
     * 查询规范化考核评分记录
     *
     * @param id 规范化考核评分记录ID
     * @return 规范化考核评分记录
     */
    @Override
    public WasteStandardizedAssessmentHistory findWasteStandardizedAssessmentHistoryById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询规范化考核评分记录列表
     *
     * @param wasteStandardizedAssessmentHistory 规范化考核评分记录
     * @return 规范化考核评分记录
     */
    @Override
    public List<WasteStandardizedAssessmentHistory> findWasteStandardizedAssessmentHistoryList(WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory) {
        LambdaQueryWrapper<WasteStandardizedAssessmentHistory> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询规范化考核评分记录
     *
     * @param page                               分页参数
     * @param wasteStandardizedAssessmentHistory 规范化考核评分记录
     * @return 规范化考核评分记录
     */
    @Override
    public PageT<WasteStandardizedAssessmentHistory> findWasteStandardizedAssessmentHistoryPage(PageT<WasteStandardizedAssessmentHistory> page, WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory) {
        LambdaQueryWrapper<WasteStandardizedAssessmentHistory> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
