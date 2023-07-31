package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlan;

import java.util.List;

/**
 * 危险废物_管理计划_01基本信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanService extends IService<YearPlan> {

    /**
     * 新增危险废物_管理计划_01基本信息
     *
     * @param yearPlan 危险废物_管理计划_01基本信息
     * @return 结果
     */
    boolean saveYearPlan(YearPlan yearPlan);

    /**
     * 修改危险废物_管理计划_01基本信息
     *
     * @param yearPlan 危险废物_管理计划_01基本信息
     * @return 结果
     */
    boolean updateYearPlan(YearPlan yearPlan);

    /**
     * 删除危险废物_管理计划_01基本信息信息
     *
     * @param id 危险废物_管理计划_01基本信息ID
     * @return 结果
     */
    boolean deleteYearPlan(String id);

    /**
     * 查询危险废物_管理计划_01基本信息
     *
     * @param id 危险废物_管理计划_01基本信息ID
     * @return 危险废物_管理计划_01基本信息
     */
    YearPlan findYearPlanById(String id);

    /**
     * 查询危险废物_管理计划_01基本信息列表
     *
     * @param yearPlan 危险废物_管理计划_01基本信息
     * @return 危险废物_管理计划_01基本信息集合
     */
    List<YearPlan> findYearPlanList(YearPlan yearPlan);

    /**
     * 分页查询危险废物_管理计划_01基本信息列表
     *
     * @param page     分页参数
     * @param yearPlan 危险废物_管理计划_01基本信息
     * @return 危险废物_管理计划_01基本信息集合
     */
    PageT<YearPlan> findYearPlanPage(PageT<YearPlan> page, YearPlan yearPlan);
}
