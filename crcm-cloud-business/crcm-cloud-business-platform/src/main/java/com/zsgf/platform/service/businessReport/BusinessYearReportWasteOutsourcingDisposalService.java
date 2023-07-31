package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportWasteOutsourcingDisposal;

import java.util.List;

/**
 * 危险废物_经营年报_05废物委外处置情况Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessYearReportWasteOutsourcingDisposalService extends IService<BusinessYearReportWasteOutsourcingDisposal> {

    /**
     * 新增危险废物_经营年报_05废物委外处置情况
     *
     * @param businessYearReportWasteOutsourcingDisposal 危险废物_经营年报_05废物委外处置情况
     * @return 结果
     */
    boolean saveBusinessYearReportWasteOutsourcingDisposal(BusinessYearReportWasteOutsourcingDisposal businessYearReportWasteOutsourcingDisposal);

    /**
     * 修改危险废物_经营年报_05废物委外处置情况
     *
     * @param businessYearReportWasteOutsourcingDisposal 危险废物_经营年报_05废物委外处置情况
     * @return 结果
     */
    boolean updateBusinessYearReportWasteOutsourcingDisposal(BusinessYearReportWasteOutsourcingDisposal businessYearReportWasteOutsourcingDisposal);

    /**
     * 删除危险废物_经营年报_05废物委外处置情况信息
     *
     * @param id 危险废物_经营年报_05废物委外处置情况ID
     * @return 结果
     */
    boolean deleteBusinessYearReportWasteOutsourcingDisposal(String id);

    /**
     * 查询危险废物_经营年报_05废物委外处置情况
     *
     * @param id 危险废物_经营年报_05废物委外处置情况ID
     * @return 危险废物_经营年报_05废物委外处置情况
     */
    BusinessYearReportWasteOutsourcingDisposal findBusinessYearReportWasteOutsourcingDisposalById(String id);

    /**
     * 查询危险废物_经营年报_05废物委外处置情况列表
     *
     * @param businessYearReportWasteOutsourcingDisposal 危险废物_经营年报_05废物委外处置情况
     * @return 危险废物_经营年报_05废物委外处置情况集合
     */
    List<BusinessYearReportWasteOutsourcingDisposal> findBusinessYearReportWasteOutsourcingDisposalList(BusinessYearReportWasteOutsourcingDisposal businessYearReportWasteOutsourcingDisposal);

    /**
     * 分页查询危险废物_经营年报_05废物委外处置情况列表
     *
     * @param page                                       分页参数
     * @param businessYearReportWasteOutsourcingDisposal 危险废物_经营年报_05废物委外处置情况
     * @return 危险废物_经营年报_05废物委外处置情况集合
     */
    PageT<BusinessYearReportWasteOutsourcingDisposal> findBusinessYearReportWasteOutsourcingDisposalPage(PageT<BusinessYearReportWasteOutsourcingDisposal> page, BusinessYearReportWasteOutsourcingDisposal businessYearReportWasteOutsourcingDisposal);
}
