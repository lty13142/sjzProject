package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanTransportUnit;

import java.util.List;

/**
 * 危险废物_管理计划_09运输单位信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanTransportUnitService extends IService<YearPlanTransportUnit> {

    /**
     * 新增危险废物_管理计划_09运输单位信息
     *
     * @param yearPlanTransportUnit 危险废物_管理计划_09运输单位信息
     * @return 结果
     */
    boolean saveYearPlanTransportUnit(YearPlanTransportUnit yearPlanTransportUnit);

    /**
     * 修改危险废物_管理计划_09运输单位信息
     *
     * @param yearPlanTransportUnit 危险废物_管理计划_09运输单位信息
     * @return 结果
     */
    boolean updateYearPlanTransportUnit(YearPlanTransportUnit yearPlanTransportUnit);

    /**
     * 删除危险废物_管理计划_09运输单位信息信息
     *
     * @param id 危险废物_管理计划_09运输单位信息ID
     * @return 结果
     */
    boolean deleteYearPlanTransportUnit(String id);

    /**
     * 查询危险废物_管理计划_09运输单位信息
     *
     * @param id 危险废物_管理计划_09运输单位信息ID
     * @return 危险废物_管理计划_09运输单位信息
     */
    YearPlanTransportUnit findYearPlanTransportUnitById(String id);

    /**
     * 查询危险废物_管理计划_09运输单位信息列表
     *
     * @param yearPlanTransportUnit 危险废物_管理计划_09运输单位信息
     * @return 危险废物_管理计划_09运输单位信息集合
     */
    List<YearPlanTransportUnit> findYearPlanTransportUnitList(YearPlanTransportUnit yearPlanTransportUnit);

    /**
     * 分页查询危险废物_管理计划_09运输单位信息列表
     *
     * @param page                  分页参数
     * @param yearPlanTransportUnit 危险废物_管理计划_09运输单位信息
     * @return 危险废物_管理计划_09运输单位信息集合
     */
    PageT<YearPlanTransportUnit> findYearPlanTransportUnitPage(PageT<YearPlanTransportUnit> page, YearPlanTransportUnit yearPlanTransportUnit);
}
