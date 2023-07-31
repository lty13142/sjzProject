package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteStorageFacilitiesStatus;

import java.util.List;

/**
 * 危险废物_管理计划_08危险废物贮存设施现状Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanWasteStorageFacilitiesStatusService extends IService<YearPlanWasteStorageFacilitiesStatus> {

    /**
     * 新增危险废物_管理计划_08危险废物贮存设施现状
     *
     * @param yearPlanWasteStorageFacilitiesStatus 危险废物_管理计划_08危险废物贮存设施现状
     * @return 结果
     */
    boolean saveYearPlanWasteStorageFacilitiesStatus(YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus);

    /**
     * 修改危险废物_管理计划_08危险废物贮存设施现状
     *
     * @param yearPlanWasteStorageFacilitiesStatus 危险废物_管理计划_08危险废物贮存设施现状
     * @return 结果
     */
    boolean updateYearPlanWasteStorageFacilitiesStatus(YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus);

    /**
     * 删除危险废物_管理计划_08危险废物贮存设施现状信息
     *
     * @param id 危险废物_管理计划_08危险废物贮存设施现状ID
     * @return 结果
     */
    boolean deleteYearPlanWasteStorageFacilitiesStatus(String id);

    /**
     * 查询危险废物_管理计划_08危险废物贮存设施现状
     *
     * @param id 危险废物_管理计划_08危险废物贮存设施现状ID
     * @return 危险废物_管理计划_08危险废物贮存设施现状
     */
    YearPlanWasteStorageFacilitiesStatus findYearPlanWasteStorageFacilitiesStatusById(String id);

    /**
     * 查询危险废物_管理计划_08危险废物贮存设施现状列表
     *
     * @param yearPlanWasteStorageFacilitiesStatus 危险废物_管理计划_08危险废物贮存设施现状
     * @return 危险废物_管理计划_08危险废物贮存设施现状集合
     */
    List<YearPlanWasteStorageFacilitiesStatus> findYearPlanWasteStorageFacilitiesStatusList(YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus);

    /**
     * 分页查询危险废物_管理计划_08危险废物贮存设施现状列表
     *
     * @param page                                 分页参数
     * @param yearPlanWasteStorageFacilitiesStatus 危险废物_管理计划_08危险废物贮存设施现状
     * @return 危险废物_管理计划_08危险废物贮存设施现状集合
     */
    PageT<YearPlanWasteStorageFacilitiesStatus> findYearPlanWasteStorageFacilitiesStatusPage(PageT<YearPlanWasteStorageFacilitiesStatus> page, YearPlanWasteStorageFacilitiesStatus yearPlanWasteStorageFacilitiesStatus);
}
