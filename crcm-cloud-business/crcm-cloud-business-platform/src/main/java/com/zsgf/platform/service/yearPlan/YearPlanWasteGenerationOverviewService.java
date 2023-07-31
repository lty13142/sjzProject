package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteGenerationOverview;

import java.util.List;

/**
 * 危险废物_管理计划_02危险废物产生概况信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanWasteGenerationOverviewService extends IService<YearPlanWasteGenerationOverview> {

    /**
     * 新增危险废物_管理计划_02危险废物产生概况信息
     *
     * @param yearPlanWasteGenerationOverview 危险废物_管理计划_02危险废物产生概况信息
     * @return 结果
     */
    boolean saveYearPlanWasteGenerationOverview(YearPlanWasteGenerationOverview yearPlanWasteGenerationOverview);

    /**
     * 修改危险废物_管理计划_02危险废物产生概况信息
     *
     * @param yearPlanWasteGenerationOverview 危险废物_管理计划_02危险废物产生概况信息
     * @return 结果
     */
    boolean updateYearPlanWasteGenerationOverview(YearPlanWasteGenerationOverview yearPlanWasteGenerationOverview);

    /**
     * 删除危险废物_管理计划_02危险废物产生概况信息信息
     *
     * @param id 危险废物_管理计划_02危险废物产生概况信息ID
     * @return 结果
     */
    boolean deleteYearPlanWasteGenerationOverview(String id);

    /**
     * 查询危险废物_管理计划_02危险废物产生概况信息
     *
     * @param id 危险废物_管理计划_02危险废物产生概况信息ID
     * @return 危险废物_管理计划_02危险废物产生概况信息
     */
    YearPlanWasteGenerationOverview findYearPlanWasteGenerationOverviewById(String id);

    /**
     * 查询危险废物_管理计划_02危险废物产生概况信息列表
     *
     * @param yearPlanWasteGenerationOverview 危险废物_管理计划_02危险废物产生概况信息
     * @return 危险废物_管理计划_02危险废物产生概况信息集合
     */
    List<YearPlanWasteGenerationOverview> findYearPlanWasteGenerationOverviewList(YearPlanWasteGenerationOverview yearPlanWasteGenerationOverview);

    /**
     * 分页查询危险废物_管理计划_02危险废物产生概况信息列表
     *
     * @param page                            分页参数
     * @param yearPlanWasteGenerationOverview 危险废物_管理计划_02危险废物产生概况信息
     * @return 危险废物_管理计划_02危险废物产生概况信息集合
     */
    PageT<YearPlanWasteGenerationOverview> findYearPlanWasteGenerationOverviewPage(PageT<YearPlanWasteGenerationOverview> page, YearPlanWasteGenerationOverview yearPlanWasteGenerationOverview);
}
