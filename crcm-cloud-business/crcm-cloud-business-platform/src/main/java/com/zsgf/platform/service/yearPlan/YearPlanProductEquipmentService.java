package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanProductEquipment;

import java.util.List;

/**
 * 危险废物_管理计划_14产品生产主要生产设备Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanProductEquipmentService extends IService<YearPlanProductEquipment> {

    /**
     * 新增危险废物_管理计划_14产品生产主要生产设备
     *
     * @param yearPlanProductEquipment 危险废物_管理计划_14产品生产主要生产设备
     * @return 结果
     */
    boolean saveYearPlanProductEquipment(YearPlanProductEquipment yearPlanProductEquipment);

    /**
     * 修改危险废物_管理计划_14产品生产主要生产设备
     *
     * @param yearPlanProductEquipment 危险废物_管理计划_14产品生产主要生产设备
     * @return 结果
     */
    boolean updateYearPlanProductEquipment(YearPlanProductEquipment yearPlanProductEquipment);

    /**
     * 删除危险废物_管理计划_14产品生产主要生产设备信息
     *
     * @param id 危险废物_管理计划_14产品生产主要生产设备ID
     * @return 结果
     */
    boolean deleteYearPlanProductEquipment(String id);

    /**
     * 查询危险废物_管理计划_14产品生产主要生产设备
     *
     * @param id 危险废物_管理计划_14产品生产主要生产设备ID
     * @return 危险废物_管理计划_14产品生产主要生产设备
     */
    YearPlanProductEquipment findYearPlanProductEquipmentById(String id);

    /**
     * 查询危险废物_管理计划_14产品生产主要生产设备列表
     *
     * @param yearPlanProductEquipment 危险废物_管理计划_14产品生产主要生产设备
     * @return 危险废物_管理计划_14产品生产主要生产设备集合
     */
    List<YearPlanProductEquipment> findYearPlanProductEquipmentList(YearPlanProductEquipment yearPlanProductEquipment);

    /**
     * 分页查询危险废物_管理计划_14产品生产主要生产设备列表
     *
     * @param page                     分页参数
     * @param yearPlanProductEquipment 危险废物_管理计划_14产品生产主要生产设备
     * @return 危险废物_管理计划_14产品生产主要生产设备集合
     */
    PageT<YearPlanProductEquipment> findYearPlanProductEquipmentPage(PageT<YearPlanProductEquipment> page, YearPlanProductEquipment yearPlanProductEquipment);
}
