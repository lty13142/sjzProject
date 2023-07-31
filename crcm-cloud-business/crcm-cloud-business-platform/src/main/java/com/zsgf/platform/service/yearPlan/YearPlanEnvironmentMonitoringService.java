package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanEnvironmentMonitoring;

import java.util.List;

/**
 * 危险废物_管理计划_17环境监测情况Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanEnvironmentMonitoringService extends IService<YearPlanEnvironmentMonitoring> {

    /**
     * 新增危险废物_管理计划_17环境监测情况
     *
     * @param yearPlanEnvironmentMonitoring 危险废物_管理计划_17环境监测情况
     * @return 结果
     */
    boolean saveYearPlanEnvironmentMonitoring(YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring);

    /**
     * 修改危险废物_管理计划_17环境监测情况
     *
     * @param yearPlanEnvironmentMonitoring 危险废物_管理计划_17环境监测情况
     * @return 结果
     */
    boolean updateYearPlanEnvironmentMonitoring(YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring);

    /**
     * 删除危险废物_管理计划_17环境监测情况信息
     *
     * @param id 危险废物_管理计划_17环境监测情况ID
     * @return 结果
     */
    boolean deleteYearPlanEnvironmentMonitoring(String id);

    /**
     * 查询危险废物_管理计划_17环境监测情况
     *
     * @param id 危险废物_管理计划_17环境监测情况ID
     * @return 危险废物_管理计划_17环境监测情况
     */
    YearPlanEnvironmentMonitoring findYearPlanEnvironmentMonitoringById(String id);

    /**
     * 查询危险废物_管理计划_17环境监测情况列表
     *
     * @param yearPlanEnvironmentMonitoring 危险废物_管理计划_17环境监测情况
     * @return 危险废物_管理计划_17环境监测情况集合
     */
    List<YearPlanEnvironmentMonitoring> findYearPlanEnvironmentMonitoringList(YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring);

    /**
     * 分页查询危险废物_管理计划_17环境监测情况列表
     *
     * @param page                          分页参数
     * @param yearPlanEnvironmentMonitoring 危险废物_管理计划_17环境监测情况
     * @return 危险废物_管理计划_17环境监测情况集合
     */
    PageT<YearPlanEnvironmentMonitoring> findYearPlanEnvironmentMonitoringPage(PageT<YearPlanEnvironmentMonitoring> page, YearPlanEnvironmentMonitoring yearPlanEnvironmentMonitoring);
}
