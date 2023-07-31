package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanTransportManagementMeasures;

import java.util.List;

/**
 * 危险废物_管理计划_10运输管理措施Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanTransportManagementMeasuresService extends IService<YearPlanTransportManagementMeasures> {

    /**
     * 新增危险废物_管理计划_10运输管理措施
     *
     * @param yearPlanTransportManagementMeasures 危险废物_管理计划_10运输管理措施
     * @return 结果
     */
    boolean saveYearPlanTransportManagementMeasures(YearPlanTransportManagementMeasures yearPlanTransportManagementMeasures);

    /**
     * 修改危险废物_管理计划_10运输管理措施
     *
     * @param yearPlanTransportManagementMeasures 危险废物_管理计划_10运输管理措施
     * @return 结果
     */
    boolean updateYearPlanTransportManagementMeasures(YearPlanTransportManagementMeasures yearPlanTransportManagementMeasures);

    /**
     * 删除危险废物_管理计划_10运输管理措施信息
     *
     * @param id 危险废物_管理计划_10运输管理措施ID
     * @return 结果
     */
    boolean deleteYearPlanTransportManagementMeasures(String id);

    /**
     * 查询危险废物_管理计划_10运输管理措施
     *
     * @param id 危险废物_管理计划_10运输管理措施ID
     * @return 危险废物_管理计划_10运输管理措施
     */
    YearPlanTransportManagementMeasures findYearPlanTransportManagementMeasuresById(String id);

    /**
     * 查询危险废物_管理计划_10运输管理措施列表
     *
     * @param yearPlanTransportManagementMeasures 危险废物_管理计划_10运输管理措施
     * @return 危险废物_管理计划_10运输管理措施集合
     */
    List<YearPlanTransportManagementMeasures> findYearPlanTransportManagementMeasuresList(YearPlanTransportManagementMeasures yearPlanTransportManagementMeasures);

    /**
     * 分页查询危险废物_管理计划_10运输管理措施列表
     *
     * @param page                                分页参数
     * @param yearPlanTransportManagementMeasures 危险废物_管理计划_10运输管理措施
     * @return 危险废物_管理计划_10运输管理措施集合
     */
    PageT<YearPlanTransportManagementMeasures> findYearPlanTransportManagementMeasuresPage(PageT<YearPlanTransportManagementMeasures> page, YearPlanTransportManagementMeasures yearPlanTransportManagementMeasures);
}
