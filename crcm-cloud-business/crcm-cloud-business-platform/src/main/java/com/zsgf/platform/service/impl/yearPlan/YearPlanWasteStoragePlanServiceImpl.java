package com.zsgf.platform.service.impl.yearPlan;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.yearPlan.YearPlanWasteStoragePlanMapper;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteStoragePlan;
import com.zsgf.platform.service.yearPlan.YearPlanWasteStoragePlanService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险废物_管理计划_03危废贮存计划Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class YearPlanWasteStoragePlanServiceImpl extends ServiceImpl<YearPlanWasteStoragePlanMapper, YearPlanWasteStoragePlan>
        implements YearPlanWasteStoragePlanService {


    /**
     * 新增危险废物_管理计划_03危废贮存计划
     *
     * @param yearPlanWasteStoragePlan 危险废物_管理计划_03危废贮存计划
     * @return 结果
     */
    @Override
    public boolean saveYearPlanWasteStoragePlan(YearPlanWasteStoragePlan yearPlanWasteStoragePlan) {
        return this.save(yearPlanWasteStoragePlan);
    }

    /**
     * 修改危险废物_管理计划_03危废贮存计划
     *
     * @param yearPlanWasteStoragePlan 危险废物_管理计划_03危废贮存计划
     * @return 结果
     */
    @Override
    public boolean updateYearPlanWasteStoragePlan(YearPlanWasteStoragePlan yearPlanWasteStoragePlan) {
        return this.updateById(yearPlanWasteStoragePlan);
    }

    /**
     * 删除危险废物_管理计划_03危废贮存计划信息
     *
     * @param id 危险废物_管理计划_03危废贮存计划ID
     * @return 结果
     */
    @Override
    public boolean deleteYearPlanWasteStoragePlan(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险废物_管理计划_03危废贮存计划
     *
     * @param id 危险废物_管理计划_03危废贮存计划ID
     * @return 危险废物_管理计划_03危废贮存计划
     */
    @Override
    public YearPlanWasteStoragePlan findYearPlanWasteStoragePlanById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险废物_管理计划_03危废贮存计划列表
     *
     * @param yearPlanWasteStoragePlan 危险废物_管理计划_03危废贮存计划
     * @return 危险废物_管理计划_03危废贮存计划
     */
    @Override
    public List<YearPlanWasteStoragePlan> findYearPlanWasteStoragePlanList(YearPlanWasteStoragePlan yearPlanWasteStoragePlan) {
        LambdaQueryWrapper<YearPlanWasteStoragePlan> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险废物_管理计划_03危废贮存计划
     *
     * @param page                     分页参数
     * @param yearPlanWasteStoragePlan 危险废物_管理计划_03危废贮存计划
     * @return 危险废物_管理计划_03危废贮存计划
     */
    @Override
    public PageT<YearPlanWasteStoragePlan> findYearPlanWasteStoragePlanPage(PageT<YearPlanWasteStoragePlan> page, YearPlanWasteStoragePlan yearPlanWasteStoragePlan) {
        LambdaQueryWrapper<YearPlanWasteStoragePlan> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
