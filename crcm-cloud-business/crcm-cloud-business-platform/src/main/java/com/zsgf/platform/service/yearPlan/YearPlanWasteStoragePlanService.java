package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteStoragePlan;

import java.util.List;

/**
 * 危险废物_管理计划_03危废贮存计划Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanWasteStoragePlanService extends IService<YearPlanWasteStoragePlan> {

    /**
     * 新增危险废物_管理计划_03危废贮存计划
     *
     * @param yearPlanWasteStoragePlan 危险废物_管理计划_03危废贮存计划
     * @return 结果
     */
    boolean saveYearPlanWasteStoragePlan(YearPlanWasteStoragePlan yearPlanWasteStoragePlan);

    /**
     * 修改危险废物_管理计划_03危废贮存计划
     *
     * @param yearPlanWasteStoragePlan 危险废物_管理计划_03危废贮存计划
     * @return 结果
     */
    boolean updateYearPlanWasteStoragePlan(YearPlanWasteStoragePlan yearPlanWasteStoragePlan);

    /**
     * 删除危险废物_管理计划_03危废贮存计划信息
     *
     * @param id 危险废物_管理计划_03危废贮存计划ID
     * @return 结果
     */
    boolean deleteYearPlanWasteStoragePlan(String id);

    /**
     * 查询危险废物_管理计划_03危废贮存计划
     *
     * @param id 危险废物_管理计划_03危废贮存计划ID
     * @return 危险废物_管理计划_03危废贮存计划
     */
    YearPlanWasteStoragePlan findYearPlanWasteStoragePlanById(String id);

    /**
     * 查询危险废物_管理计划_03危废贮存计划列表
     *
     * @param yearPlanWasteStoragePlan 危险废物_管理计划_03危废贮存计划
     * @return 危险废物_管理计划_03危废贮存计划集合
     */
    List<YearPlanWasteStoragePlan> findYearPlanWasteStoragePlanList(YearPlanWasteStoragePlan yearPlanWasteStoragePlan);

    /**
     * 分页查询危险废物_管理计划_03危废贮存计划列表
     *
     * @param page                     分页参数
     * @param yearPlanWasteStoragePlan 危险废物_管理计划_03危废贮存计划
     * @return 危险废物_管理计划_03危废贮存计划集合
     */
    PageT<YearPlanWasteStoragePlan> findYearPlanWasteStoragePlanPage(PageT<YearPlanWasteStoragePlan> page, YearPlanWasteStoragePlan yearPlanWasteStoragePlan);
}
