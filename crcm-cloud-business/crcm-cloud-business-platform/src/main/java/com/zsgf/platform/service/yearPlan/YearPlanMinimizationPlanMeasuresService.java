package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanMinimizationPlanMeasures;

import java.util.List;

/**
 * 危险废物_管理计划_19减量化计划和措施Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanMinimizationPlanMeasuresService extends IService<YearPlanMinimizationPlanMeasures> {

    /**
     * 新增危险废物_管理计划_19减量化计划和措施
     *
     * @param yearPlanMinimizationPlanMeasures 危险废物_管理计划_19减量化计划和措施
     * @return 结果
     */
    boolean saveYearPlanMinimizationPlanMeasures(YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures);

    /**
     * 修改危险废物_管理计划_19减量化计划和措施
     *
     * @param yearPlanMinimizationPlanMeasures 危险废物_管理计划_19减量化计划和措施
     * @return 结果
     */
    boolean updateYearPlanMinimizationPlanMeasures(YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures);

    /**
     * 删除危险废物_管理计划_19减量化计划和措施信息
     *
     * @param id 危险废物_管理计划_19减量化计划和措施ID
     * @return 结果
     */
    boolean deleteYearPlanMinimizationPlanMeasures(String id);

    /**
     * 查询危险废物_管理计划_19减量化计划和措施
     *
     * @param id 危险废物_管理计划_19减量化计划和措施ID
     * @return 危险废物_管理计划_19减量化计划和措施
     */
    YearPlanMinimizationPlanMeasures findYearPlanMinimizationPlanMeasuresById(String id);

    /**
     * 查询危险废物_管理计划_19减量化计划和措施列表
     *
     * @param yearPlanMinimizationPlanMeasures 危险废物_管理计划_19减量化计划和措施
     * @return 危险废物_管理计划_19减量化计划和措施集合
     */
    List<YearPlanMinimizationPlanMeasures> findYearPlanMinimizationPlanMeasuresList(YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures);

    /**
     * 分页查询危险废物_管理计划_19减量化计划和措施列表
     *
     * @param page                             分页参数
     * @param yearPlanMinimizationPlanMeasures 危险废物_管理计划_19减量化计划和措施
     * @return 危险废物_管理计划_19减量化计划和措施集合
     */
    PageT<YearPlanMinimizationPlanMeasures> findYearPlanMinimizationPlanMeasuresPage(PageT<YearPlanMinimizationPlanMeasures> page, YearPlanMinimizationPlanMeasures yearPlanMinimizationPlanMeasures);
}
