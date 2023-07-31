package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportSelfUseDisposal;

import java.util.List;

/**
 * 危险废物_产生年报_03自行利用处置情况Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteYearReportSelfUseDisposalService extends IService<WasteYearReportSelfUseDisposal> {

    /**
     * 新增危险废物_产生年报_03自行利用处置情况
     *
     * @param wasteYearReportSelfUseDisposal 危险废物_产生年报_03自行利用处置情况
     * @return 结果
     */
    boolean saveWasteYearReportSelfUseDisposal(WasteYearReportSelfUseDisposal wasteYearReportSelfUseDisposal);

    /**
     * 修改危险废物_产生年报_03自行利用处置情况
     *
     * @param wasteYearReportSelfUseDisposal 危险废物_产生年报_03自行利用处置情况
     * @return 结果
     */
    boolean updateWasteYearReportSelfUseDisposal(WasteYearReportSelfUseDisposal wasteYearReportSelfUseDisposal);

    /**
     * 删除危险废物_产生年报_03自行利用处置情况信息
     *
     * @param id 危险废物_产生年报_03自行利用处置情况ID
     * @return 结果
     */
    boolean deleteWasteYearReportSelfUseDisposal(String id);

    /**
     * 查询危险废物_产生年报_03自行利用处置情况
     *
     * @param id 危险废物_产生年报_03自行利用处置情况ID
     * @return 危险废物_产生年报_03自行利用处置情况
     */
    WasteYearReportSelfUseDisposal findWasteYearReportSelfUseDisposalById(String id);

    /**
     * 查询危险废物_产生年报_03自行利用处置情况列表
     *
     * @param wasteYearReportSelfUseDisposal 危险废物_产生年报_03自行利用处置情况
     * @return 危险废物_产生年报_03自行利用处置情况集合
     */
    List<WasteYearReportSelfUseDisposal> findWasteYearReportSelfUseDisposalList(WasteYearReportSelfUseDisposal wasteYearReportSelfUseDisposal);

    /**
     * 分页查询危险废物_产生年报_03自行利用处置情况列表
     *
     * @param page                           分页参数
     * @param wasteYearReportSelfUseDisposal 危险废物_产生年报_03自行利用处置情况
     * @return 危险废物_产生年报_03自行利用处置情况集合
     */
    PageT<WasteYearReportSelfUseDisposal> findWasteYearReportSelfUseDisposalPage(PageT<WasteYearReportSelfUseDisposal> page, WasteYearReportSelfUseDisposal wasteYearReportSelfUseDisposal);
}
