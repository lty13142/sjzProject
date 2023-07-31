package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteSelfUseDisposal;

import java.util.List;

/**
 * 危险废物_管理计划_04危废自行利用处置信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanWasteSelfUseDisposalService extends IService<YearPlanWasteSelfUseDisposal> {

    /**
     * 新增危险废物_管理计划_04危废自行利用处置信息
     *
     * @param yearPlanWasteSelfUseDisposal 危险废物_管理计划_04危废自行利用处置信息
     * @return 结果
     */
    boolean saveYearPlanWasteSelfUseDisposal(YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal);

    /**
     * 修改危险废物_管理计划_04危废自行利用处置信息
     *
     * @param yearPlanWasteSelfUseDisposal 危险废物_管理计划_04危废自行利用处置信息
     * @return 结果
     */
    boolean updateYearPlanWasteSelfUseDisposal(YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal);

    /**
     * 删除危险废物_管理计划_04危废自行利用处置信息信息
     *
     * @param id 危险废物_管理计划_04危废自行利用处置信息ID
     * @return 结果
     */
    boolean deleteYearPlanWasteSelfUseDisposal(String id);

    /**
     * 查询危险废物_管理计划_04危废自行利用处置信息
     *
     * @param id 危险废物_管理计划_04危废自行利用处置信息ID
     * @return 危险废物_管理计划_04危废自行利用处置信息
     */
    YearPlanWasteSelfUseDisposal findYearPlanWasteSelfUseDisposalById(String id);

    /**
     * 查询危险废物_管理计划_04危废自行利用处置信息列表
     *
     * @param yearPlanWasteSelfUseDisposal 危险废物_管理计划_04危废自行利用处置信息
     * @return 危险废物_管理计划_04危废自行利用处置信息集合
     */
    List<YearPlanWasteSelfUseDisposal> findYearPlanWasteSelfUseDisposalList(YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal);

    /**
     * 分页查询危险废物_管理计划_04危废自行利用处置信息列表
     *
     * @param page                         分页参数
     * @param yearPlanWasteSelfUseDisposal 危险废物_管理计划_04危废自行利用处置信息
     * @return 危险废物_管理计划_04危废自行利用处置信息集合
     */
    PageT<YearPlanWasteSelfUseDisposal> findYearPlanWasteSelfUseDisposalPage(PageT<YearPlanWasteSelfUseDisposal> page, YearPlanWasteSelfUseDisposal yearPlanWasteSelfUseDisposal);
}
