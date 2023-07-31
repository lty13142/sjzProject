package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportMainProductsProduction;

import java.util.List;

/**
 * 危险废物_产生年报_07主要产品生产情况Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteYearReportMainProductsProductionService extends IService<WasteYearReportMainProductsProduction> {

    /**
     * 新增危险废物_产生年报_07主要产品生产情况
     *
     * @param wasteYearReportMainProductsProduction 危险废物_产生年报_07主要产品生产情况
     * @return 结果
     */
    boolean saveWasteYearReportMainProductsProduction(WasteYearReportMainProductsProduction wasteYearReportMainProductsProduction);

    /**
     * 修改危险废物_产生年报_07主要产品生产情况
     *
     * @param wasteYearReportMainProductsProduction 危险废物_产生年报_07主要产品生产情况
     * @return 结果
     */
    boolean updateWasteYearReportMainProductsProduction(WasteYearReportMainProductsProduction wasteYearReportMainProductsProduction);

    /**
     * 删除危险废物_产生年报_07主要产品生产情况信息
     *
     * @param id 危险废物_产生年报_07主要产品生产情况ID
     * @return 结果
     */
    boolean deleteWasteYearReportMainProductsProduction(String id);

    /**
     * 查询危险废物_产生年报_07主要产品生产情况
     *
     * @param id 危险废物_产生年报_07主要产品生产情况ID
     * @return 危险废物_产生年报_07主要产品生产情况
     */
    WasteYearReportMainProductsProduction findWasteYearReportMainProductsProductionById(String id);

    /**
     * 查询危险废物_产生年报_07主要产品生产情况列表
     *
     * @param wasteYearReportMainProductsProduction 危险废物_产生年报_07主要产品生产情况
     * @return 危险废物_产生年报_07主要产品生产情况集合
     */
    List<WasteYearReportMainProductsProduction> findWasteYearReportMainProductsProductionList(WasteYearReportMainProductsProduction wasteYearReportMainProductsProduction);

    /**
     * 分页查询危险废物_产生年报_07主要产品生产情况列表
     *
     * @param page                                  分页参数
     * @param wasteYearReportMainProductsProduction 危险废物_产生年报_07主要产品生产情况
     * @return 危险废物_产生年报_07主要产品生产情况集合
     */
    PageT<WasteYearReportMainProductsProduction> findWasteYearReportMainProductsProductionPage(PageT<WasteYearReportMainProductsProduction> page, WasteYearReportMainProductsProduction wasteYearReportMainProductsProduction);
}
