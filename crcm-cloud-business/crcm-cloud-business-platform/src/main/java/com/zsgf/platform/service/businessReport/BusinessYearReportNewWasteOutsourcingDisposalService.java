package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportNewWasteOutsourcingDisposal;

import java.util.List;

/**
 * 危险废物_经营年报_09新产生危废委外处置Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessYearReportNewWasteOutsourcingDisposalService extends IService<BusinessYearReportNewWasteOutsourcingDisposal> {

    /**
     * 新增危险废物_经营年报_09新产生危废委外处置
     *
     * @param businessYearReportNewWasteOutsourcingDisposal 危险废物_经营年报_09新产生危废委外处置
     * @return 结果
     */
    boolean saveBusinessYearReportNewWasteOutsourcingDisposal(BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal);

    /**
     * 修改危险废物_经营年报_09新产生危废委外处置
     *
     * @param businessYearReportNewWasteOutsourcingDisposal 危险废物_经营年报_09新产生危废委外处置
     * @return 结果
     */
    boolean updateBusinessYearReportNewWasteOutsourcingDisposal(BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal);

    /**
     * 删除危险废物_经营年报_09新产生危废委外处置信息
     *
     * @param id 危险废物_经营年报_09新产生危废委外处置ID
     * @return 结果
     */
    boolean deleteBusinessYearReportNewWasteOutsourcingDisposal(String id);

    /**
     * 查询危险废物_经营年报_09新产生危废委外处置
     *
     * @param id 危险废物_经营年报_09新产生危废委外处置ID
     * @return 危险废物_经营年报_09新产生危废委外处置
     */
    BusinessYearReportNewWasteOutsourcingDisposal findBusinessYearReportNewWasteOutsourcingDisposalById(String id);

    /**
     * 查询危险废物_经营年报_09新产生危废委外处置列表
     *
     * @param businessYearReportNewWasteOutsourcingDisposal 危险废物_经营年报_09新产生危废委外处置
     * @return 危险废物_经营年报_09新产生危废委外处置集合
     */
    List<BusinessYearReportNewWasteOutsourcingDisposal> findBusinessYearReportNewWasteOutsourcingDisposalList(BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal);

    /**
     * 分页查询危险废物_经营年报_09新产生危废委外处置列表
     *
     * @param page                                          分页参数
     * @param businessYearReportNewWasteOutsourcingDisposal 危险废物_经营年报_09新产生危废委外处置
     * @return 危险废物_经营年报_09新产生危废委外处置集合
     */
    PageT<BusinessYearReportNewWasteOutsourcingDisposal> findBusinessYearReportNewWasteOutsourcingDisposalPage(PageT<BusinessYearReportNewWasteOutsourcingDisposal> page, BusinessYearReportNewWasteOutsourcingDisposal businessYearReportNewWasteOutsourcingDisposal);
}
