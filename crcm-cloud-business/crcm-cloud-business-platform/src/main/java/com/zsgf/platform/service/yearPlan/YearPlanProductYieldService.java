package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductYield;

import java.util.List;

/**
 * 危险废物_管理计划_15产品生产产量Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanProductYieldService extends IService<YearPlanProductYield> {

    /**
     * 新增危险废物_管理计划_15产品生产产量
     *
     * @param yearPlanProductYield 危险废物_管理计划_15产品生产产量
     * @return 结果
     */
    boolean saveYearPlanProductYield(YearPlanProductYield yearPlanProductYield);

    /**
     * 修改危险废物_管理计划_15产品生产产量
     *
     * @param yearPlanProductYield 危险废物_管理计划_15产品生产产量
     * @return 结果
     */
    boolean updateYearPlanProductYield(YearPlanProductYield yearPlanProductYield);

    /**
     * 删除危险废物_管理计划_15产品生产产量信息
     *
     * @param id 危险废物_管理计划_15产品生产产量ID
     * @return 结果
     */
    boolean deleteYearPlanProductYield(String id);

    /**
     * 查询危险废物_管理计划_15产品生产产量
     *
     * @param id 危险废物_管理计划_15产品生产产量ID
     * @return 危险废物_管理计划_15产品生产产量
     */
    YearPlanProductYield findYearPlanProductYieldById(String id);

    /**
     * 查询危险废物_管理计划_15产品生产产量列表
     *
     * @param yearPlanProductYield 危险废物_管理计划_15产品生产产量
     * @return 危险废物_管理计划_15产品生产产量集合
     */
    List<YearPlanProductYield> findYearPlanProductYieldList(YearPlanProductYield yearPlanProductYield);

    /**
     * 分页查询危险废物_管理计划_15产品生产产量列表
     *
     * @param page                 分页参数
     * @param yearPlanProductYield 危险废物_管理计划_15产品生产产量
     * @return 危险废物_管理计划_15产品生产产量集合
     */
    PageT<YearPlanProductYield> findYearPlanProductYieldPage(PageT<YearPlanProductYield> page, YearPlanProductYield yearPlanProductYield);
}
