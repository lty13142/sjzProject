package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanWasteMinimizationMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteMinimization;
import com.zsgf.platform.service.yearPlan.YearPlanWasteMinimizationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_18危险废物减量化信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanWasteMinimizationServiceImpl extends ServiceImpl<YearPlanWasteMinimizationMapper, YearPlanWasteMinimization>
        implements YearPlanWasteMinimizationService {


    /**
     * 新增危险废物_管理计划_18危险废物减量化信息
     *
     * @param yearPlanWasteMinimization 危险废物_管理计划_18危险废物减量化信息
     * @return 结果
     */
    @Override
    public boolean saveYearPlanWasteMinimization(YearPlanWasteMinimization yearPlanWasteMinimization) {
        return this.save(yearPlanWasteMinimization);
    }

    /**
     * 修改危险废物_管理计划_18危险废物减量化信息
     *
     * @param yearPlanWasteMinimization 危险废物_管理计划_18危险废物减量化信息
     * @return 结果
     */
    @Override
    public boolean updateYearPlanWasteMinimization(YearPlanWasteMinimization yearPlanWasteMinimization) {
        return this.updateById(yearPlanWasteMinimization);
    }

    /**
     * 删除危险废物_管理计划_18危险废物减量化信息信息
     *
     * @param id 危险废物_管理计划_18危险废物减量化信息ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanWasteMinimization(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_18危险废物减量化信息
     *
     * @param id 危险废物_管理计划_18危险废物减量化信息ID
     * @return 危险废物_管理计划_18危险废物减量化信息
     */
    @Override
    public YearPlanWasteMinimization findYearPlanWasteMinimizationById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_18危险废物减量化信息列表
     *
     * @param yearPlanWasteMinimization 危险废物_管理计划_18危险废物减量化信息
     * @return 危险废物_管理计划_18危险废物减量化信息
     */
    @Override
    public List<YearPlanWasteMinimization> findYearPlanWasteMinimizationList(YearPlanWasteMinimization yearPlanWasteMinimization) {
        LambdaQueryWrapper<YearPlanWasteMinimization> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_18危险废物减量化信息
     *
     * @param page                      分页参数
     * @param yearPlanWasteMinimization 危险废物_管理计划_18危险废物减量化信息
     * @return 危险废物_管理计划_18危险废物减量化信息
     */
    @Override
    public PageT<YearPlanWasteMinimization> findYearPlanWasteMinimizationPage(PageT<YearPlanWasteMinimization> page, YearPlanWasteMinimization yearPlanWasteMinimization) {
        LambdaQueryWrapper<YearPlanWasteMinimization> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
