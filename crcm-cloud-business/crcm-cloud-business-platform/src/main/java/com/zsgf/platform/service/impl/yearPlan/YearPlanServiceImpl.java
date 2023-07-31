package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlan;
import com.zsgf.platform.service.yearPlan.YearPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_01基本信息Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanServiceImpl extends ServiceImpl<YearPlanMapper, YearPlan> implements YearPlanService {


    /**
     * 新增危险废物_管理计划_01基本信息
     *
     * @param yearPlan 危险废物_管理计划_01基本信息
     * @return 结果
     */
    @Override
    public boolean saveYearPlan(YearPlan yearPlan) {
        return this.save(yearPlan);
    }

    /**
     * 修改危险废物_管理计划_01基本信息
     *
     * @param yearPlan 危险废物_管理计划_01基本信息
     * @return 结果
     */
    @Override
    public boolean updateYearPlan(YearPlan yearPlan) {
        return this.updateById(yearPlan);
    }

    /**
     * 删除危险废物_管理计划_01基本信息信息
     *
     * @param id 危险废物_管理计划_01基本信息ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlan(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_01基本信息
     *
     * @param id 危险废物_管理计划_01基本信息ID
     * @return 危险废物_管理计划_01基本信息
     */
    @Override
    public YearPlan findYearPlanById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_01基本信息列表
     *
     * @param yearPlan 危险废物_管理计划_01基本信息
     * @return 危险废物_管理计划_01基本信息
     */
    @Override
    public List<YearPlan> findYearPlanList(YearPlan yearPlan) {
        LambdaQueryWrapper<YearPlan> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_01基本信息
     *
     * @param page     分页参数
     * @param yearPlan 危险废物_管理计划_01基本信息
     * @return 危险废物_管理计划_01基本信息
     */
    @Override
    public PageT<YearPlan> findYearPlanPage(PageT<YearPlan> page, YearPlan yearPlan) {
        LambdaQueryWrapper<YearPlan> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
