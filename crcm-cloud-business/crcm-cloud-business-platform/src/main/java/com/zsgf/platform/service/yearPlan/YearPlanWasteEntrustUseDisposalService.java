package com.zsgf.platform.service.yearPlan;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.yearPlan.YearPlanWasteEntrustUseDisposal;

import java.util.List;

/**
 * 危险废物_管理计划_05危险废物委托利用处置Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface YearPlanWasteEntrustUseDisposalService extends IService<YearPlanWasteEntrustUseDisposal> {

    /**
     * 新增危险废物_管理计划_05危险废物委托利用处置
     *
     * @param yearPlanWasteEntrustUseDisposal 危险废物_管理计划_05危险废物委托利用处置
     * @return 结果
     */
    boolean saveYearPlanWasteEntrustUseDisposal(YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal);

    /**
     * 修改危险废物_管理计划_05危险废物委托利用处置
     *
     * @param yearPlanWasteEntrustUseDisposal 危险废物_管理计划_05危险废物委托利用处置
     * @return 结果
     */
    boolean updateYearPlanWasteEntrustUseDisposal(YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal);

    /**
     * 删除危险废物_管理计划_05危险废物委托利用处置信息
     *
     * @param id 危险废物_管理计划_05危险废物委托利用处置ID
     * @return 结果
     */
    boolean deleteYearPlanWasteEntrustUseDisposal(String id);

    /**
     * 查询危险废物_管理计划_05危险废物委托利用处置
     *
     * @param id 危险废物_管理计划_05危险废物委托利用处置ID
     * @return 危险废物_管理计划_05危险废物委托利用处置
     */
    YearPlanWasteEntrustUseDisposal findYearPlanWasteEntrustUseDisposalById(String id);

    /**
     * 查询危险废物_管理计划_05危险废物委托利用处置列表
     *
     * @param yearPlanWasteEntrustUseDisposal 危险废物_管理计划_05危险废物委托利用处置
     * @return 危险废物_管理计划_05危险废物委托利用处置集合
     */
    List<YearPlanWasteEntrustUseDisposal> findYearPlanWasteEntrustUseDisposalList(YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal);

    /**
     * 分页查询危险废物_管理计划_05危险废物委托利用处置列表
     *
     * @param page                            分页参数
     * @param yearPlanWasteEntrustUseDisposal 危险废物_管理计划_05危险废物委托利用处置
     * @return 危险废物_管理计划_05危险废物委托利用处置集合
     */
    PageT<YearPlanWasteEntrustUseDisposal> findYearPlanWasteEntrustUseDisposalPage(PageT<YearPlanWasteEntrustUseDisposal> page, YearPlanWasteEntrustUseDisposal yearPlanWasteEntrustUseDisposal);
}
