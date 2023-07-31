package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReport;

import java.util.List;

/**
 * 危险废物_产生年报_01基本信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteYearReportService extends IService<WasteYearReport> {

    /**
     * 新增危险废物_产生年报_01基本信息
     *
     * @param wasteYearReport 危险废物_产生年报_01基本信息
     * @return 结果
     */
    boolean saveWasteYearReport(WasteYearReport wasteYearReport);

    /**
     * 修改危险废物_产生年报_01基本信息
     *
     * @param wasteYearReport 危险废物_产生年报_01基本信息
     * @return 结果
     */
    boolean updateWasteYearReport(WasteYearReport wasteYearReport);

    /**
     * 删除危险废物_产生年报_01基本信息信息
     *
     * @param id 危险废物_产生年报_01基本信息ID
     * @return 结果
     */
    boolean deleteWasteYearReport(String id);

    /**
     * 查询危险废物_产生年报_01基本信息
     *
     * @param id 危险废物_产生年报_01基本信息ID
     * @return 危险废物_产生年报_01基本信息
     */
    WasteYearReport findWasteYearReportById(String id);

    /**
     * 查询危险废物_产生年报_01基本信息列表
     *
     * @param wasteYearReport 危险废物_产生年报_01基本信息
     * @return 危险废物_产生年报_01基本信息集合
     */
    List<WasteYearReport> findWasteYearReportList(WasteYearReport wasteYearReport);

    /**
     * 分页查询危险废物_产生年报_01基本信息列表
     *
     * @param page            分页参数
     * @param wasteYearReport 危险废物_产生年报_01基本信息
     * @return 危险废物_产生年报_01基本信息集合
     */
    PageT<WasteYearReport> findWasteYearReportPage(PageT<WasteYearReport> page, WasteYearReport wasteYearReport);
}
