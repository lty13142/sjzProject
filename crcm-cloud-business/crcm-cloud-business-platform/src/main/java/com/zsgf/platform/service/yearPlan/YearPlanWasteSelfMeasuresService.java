package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteSelfMeasures;

import java.util.List;

/**
 * 危险废物_管理计划_06危废自行利用/处置措施Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanWasteSelfMeasuresService extends IService<YearPlanWasteSelfMeasures> {

    /**
     * 新增危险废物_管理计划_06危废自行利用/处置措施
     *
     * @param yearPlanWasteSelfMeasures 危险废物_管理计划_06危废自行利用/处置措施
     * @return 结果
     */
    boolean saveYearPlanWasteSelfMeasures(YearPlanWasteSelfMeasures yearPlanWasteSelfMeasures);

    /**
     * 修改危险废物_管理计划_06危废自行利用/处置措施
     *
     * @param yearPlanWasteSelfMeasures 危险废物_管理计划_06危废自行利用/处置措施
     * @return 结果
     */
    boolean updateYearPlanWasteSelfMeasures(YearPlanWasteSelfMeasures yearPlanWasteSelfMeasures);

    /**
     * 删除危险废物_管理计划_06危废自行利用/处置措施信息
     *
     * @param id 危险废物_管理计划_06危废自行利用/处置措施ID
     * @return 结果
     */
    boolean deleteYearPlanWasteSelfMeasures(String id);

    /**
     * 查询危险废物_管理计划_06危废自行利用/处置措施
     *
     * @param id 危险废物_管理计划_06危废自行利用/处置措施ID
     * @return 危险废物_管理计划_06危废自行利用/处置措施
     */
    YearPlanWasteSelfMeasures findYearPlanWasteSelfMeasuresById(String id);

    /**
     * 查询危险废物_管理计划_06危废自行利用/处置措施列表
     *
     * @param yearPlanWasteSelfMeasures 危险废物_管理计划_06危废自行利用/处置措施
     * @return 危险废物_管理计划_06危废自行利用/处置措施集合
     */
    List<YearPlanWasteSelfMeasures> findYearPlanWasteSelfMeasuresList(YearPlanWasteSelfMeasures yearPlanWasteSelfMeasures);

    /**
     * 分页查询危险废物_管理计划_06危废自行利用/处置措施列表
     *
     * @param page                      分页参数
     * @param yearPlanWasteSelfMeasures 危险废物_管理计划_06危废自行利用/处置措施
     * @return 危险废物_管理计划_06危废自行利用/处置措施集合
     */
    PageT<YearPlanWasteSelfMeasures> findYearPlanWasteSelfMeasuresPage(PageT<YearPlanWasteSelfMeasures> page, YearPlanWasteSelfMeasures yearPlanWasteSelfMeasures);
}
