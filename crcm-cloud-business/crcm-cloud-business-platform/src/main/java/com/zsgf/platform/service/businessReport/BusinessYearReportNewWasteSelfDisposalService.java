package com.zsgf.platform.service.businessReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.businessReport.BusinessYearReportNewWasteSelfDisposal;

import java.util.List;

/**
 * 危险废物_经营年报_08新产生危废自行处置Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface BusinessYearReportNewWasteSelfDisposalService extends IService<BusinessYearReportNewWasteSelfDisposal> {

    /**
     * 新增危险废物_经营年报_08新产生危废自行处置
     *
     * @param businessYearReportNewWasteSelfDisposal 危险废物_经营年报_08新产生危废自行处置
     * @return 结果
     */
    boolean saveBusinessYearReportNewWasteSelfDisposal(BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal);

    /**
     * 修改危险废物_经营年报_08新产生危废自行处置
     *
     * @param businessYearReportNewWasteSelfDisposal 危险废物_经营年报_08新产生危废自行处置
     * @return 结果
     */
    boolean updateBusinessYearReportNewWasteSelfDisposal(BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal);

    /**
     * 删除危险废物_经营年报_08新产生危废自行处置信息
     *
     * @param id 危险废物_经营年报_08新产生危废自行处置ID
     * @return 结果
     */
    boolean deleteBusinessYearReportNewWasteSelfDisposal(String id);

    /**
     * 查询危险废物_经营年报_08新产生危废自行处置
     *
     * @param id 危险废物_经营年报_08新产生危废自行处置ID
     * @return 危险废物_经营年报_08新产生危废自行处置
     */
    BusinessYearReportNewWasteSelfDisposal findBusinessYearReportNewWasteSelfDisposalById(String id);

    /**
     * 查询危险废物_经营年报_08新产生危废自行处置列表
     *
     * @param businessYearReportNewWasteSelfDisposal 危险废物_经营年报_08新产生危废自行处置
     * @return 危险废物_经营年报_08新产生危废自行处置集合
     */
    List<BusinessYearReportNewWasteSelfDisposal> findBusinessYearReportNewWasteSelfDisposalList(BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal);

    /**
     * 分页查询危险废物_经营年报_08新产生危废自行处置列表
     *
     * @param page                                   分页参数
     * @param businessYearReportNewWasteSelfDisposal 危险废物_经营年报_08新产生危废自行处置
     * @return 危险废物_经营年报_08新产生危废自行处置集合
     */
    PageT<BusinessYearReportNewWasteSelfDisposal> findBusinessYearReportNewWasteSelfDisposalPage(PageT<BusinessYearReportNewWasteSelfDisposal> page, BusinessYearReportNewWasteSelfDisposal businessYearReportNewWasteSelfDisposal);
}
