package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteMinimization;

import java.util.List;

/**
 * 危险废物_管理计划_18危险废物减量化信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanWasteMinimizationService extends IService<YearPlanWasteMinimization> {

    /**
     * 新增危险废物_管理计划_18危险废物减量化信息
     *
     * @param yearPlanWasteMinimization 危险废物_管理计划_18危险废物减量化信息
     * @return 结果
     */
    boolean saveYearPlanWasteMinimization(YearPlanWasteMinimization yearPlanWasteMinimization);

    /**
     * 修改危险废物_管理计划_18危险废物减量化信息
     *
     * @param yearPlanWasteMinimization 危险废物_管理计划_18危险废物减量化信息
     * @return 结果
     */
    boolean updateYearPlanWasteMinimization(YearPlanWasteMinimization yearPlanWasteMinimization);

    /**
     * 删除危险废物_管理计划_18危险废物减量化信息信息
     *
     * @param id 危险废物_管理计划_18危险废物减量化信息ID
     * @return 结果
     */
    boolean deleteYearPlanWasteMinimization(String id);

    /**
     * 查询危险废物_管理计划_18危险废物减量化信息
     *
     * @param id 危险废物_管理计划_18危险废物减量化信息ID
     * @return 危险废物_管理计划_18危险废物减量化信息
     */
    YearPlanWasteMinimization findYearPlanWasteMinimizationById(String id);

    /**
     * 查询危险废物_管理计划_18危险废物减量化信息列表
     *
     * @param yearPlanWasteMinimization 危险废物_管理计划_18危险废物减量化信息
     * @return 危险废物_管理计划_18危险废物减量化信息集合
     */
    List<YearPlanWasteMinimization> findYearPlanWasteMinimizationList(YearPlanWasteMinimization yearPlanWasteMinimization);

    /**
     * 分页查询危险废物_管理计划_18危险废物减量化信息列表
     *
     * @param page                      分页参数
     * @param yearPlanWasteMinimization 危险废物_管理计划_18危险废物减量化信息
     * @return 危险废物_管理计划_18危险废物减量化信息集合
     */
    PageT<YearPlanWasteMinimization> findYearPlanWasteMinimizationPage(PageT<YearPlanWasteMinimization> page, YearPlanWasteMinimization yearPlanWasteMinimization);
}
