package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportWasteUseDisposal;

import java.util.List;

/**
 * 危险废物_经营年报_04废物利用处置情况Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessYearReportWasteUseDisposalService extends IService<BusinessYearReportWasteUseDisposal> {

    /**
     * 新增危险废物_经营年报_04废物利用处置情况
     *
     * @param businessYearReportWasteUseDisposal 危险废物_经营年报_04废物利用处置情况
     * @return 结果
     */
    boolean saveBusinessYearReportWasteUseDisposal(BusinessYearReportWasteUseDisposal businessYearReportWasteUseDisposal);

    /**
     * 修改危险废物_经营年报_04废物利用处置情况
     *
     * @param businessYearReportWasteUseDisposal 危险废物_经营年报_04废物利用处置情况
     * @return 结果
     */
    boolean updateBusinessYearReportWasteUseDisposal(BusinessYearReportWasteUseDisposal businessYearReportWasteUseDisposal);

    /**
     * 删除危险废物_经营年报_04废物利用处置情况信息
     *
     * @param id 危险废物_经营年报_04废物利用处置情况ID
     * @return 结果
     */
    boolean deleteBusinessYearReportWasteUseDisposal(String id);

    /**
     * 查询危险废物_经营年报_04废物利用处置情况
     *
     * @param id 危险废物_经营年报_04废物利用处置情况ID
     * @return 危险废物_经营年报_04废物利用处置情况
     */
    BusinessYearReportWasteUseDisposal findBusinessYearReportWasteUseDisposalById(String id);

    /**
     * 查询危险废物_经营年报_04废物利用处置情况列表
     *
     * @param businessYearReportWasteUseDisposal 危险废物_经营年报_04废物利用处置情况
     * @return 危险废物_经营年报_04废物利用处置情况集合
     */
    List<BusinessYearReportWasteUseDisposal> findBusinessYearReportWasteUseDisposalList(BusinessYearReportWasteUseDisposal businessYearReportWasteUseDisposal);

    /**
     * 分页查询危险废物_经营年报_04废物利用处置情况列表
     *
     * @param page                               分页参数
     * @param businessYearReportWasteUseDisposal 危险废物_经营年报_04废物利用处置情况
     * @return 危险废物_经营年报_04废物利用处置情况集合
     */
    PageT<BusinessYearReportWasteUseDisposal> findBusinessYearReportWasteUseDisposalPage(PageT<BusinessYearReportWasteUseDisposal> page, BusinessYearReportWasteUseDisposal businessYearReportWasteUseDisposal);
}
