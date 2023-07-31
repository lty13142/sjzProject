package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteStorageMeasures;

import java.util.List;

/**
 * 危险废物_管理计划_07危险废物贮存措施Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanWasteStorageMeasuresService extends IService<YearPlanWasteStorageMeasures> {

    /**
     * 新增危险废物_管理计划_07危险废物贮存措施
     *
     * @param yearPlanWasteStorageMeasures 危险废物_管理计划_07危险废物贮存措施
     * @return 结果
     */
    boolean saveYearPlanWasteStorageMeasures(YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures);

    /**
     * 修改危险废物_管理计划_07危险废物贮存措施
     *
     * @param yearPlanWasteStorageMeasures 危险废物_管理计划_07危险废物贮存措施
     * @return 结果
     */
    boolean updateYearPlanWasteStorageMeasures(YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures);

    /**
     * 删除危险废物_管理计划_07危险废物贮存措施信息
     *
     * @param id 危险废物_管理计划_07危险废物贮存措施ID
     * @return 结果
     */
    boolean deleteYearPlanWasteStorageMeasures(String id);

    /**
     * 查询危险废物_管理计划_07危险废物贮存措施
     *
     * @param id 危险废物_管理计划_07危险废物贮存措施ID
     * @return 危险废物_管理计划_07危险废物贮存措施
     */
    YearPlanWasteStorageMeasures findYearPlanWasteStorageMeasuresById(String id);

    /**
     * 查询危险废物_管理计划_07危险废物贮存措施列表
     *
     * @param yearPlanWasteStorageMeasures 危险废物_管理计划_07危险废物贮存措施
     * @return 危险废物_管理计划_07危险废物贮存措施集合
     */
    List<YearPlanWasteStorageMeasures> findYearPlanWasteStorageMeasuresList(YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures);

    /**
     * 分页查询危险废物_管理计划_07危险废物贮存措施列表
     *
     * @param page                         分页参数
     * @param yearPlanWasteStorageMeasures 危险废物_管理计划_07危险废物贮存措施
     * @return 危险废物_管理计划_07危险废物贮存措施集合
     */
    PageT<YearPlanWasteStorageMeasures> findYearPlanWasteStorageMeasuresPage(PageT<YearPlanWasteStorageMeasures> page, YearPlanWasteStorageMeasures yearPlanWasteStorageMeasures);
}
