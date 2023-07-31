package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReport;

import java.util.List;

/**
 * 危险废物_产生月报_01基本信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteMonthReportService extends IService<WasteMonthReport> {

    /**
     * 新增危险废物_产生月报_01基本信息
     *
     * @param wasteMonthReport 危险废物_产生月报_01基本信息
     * @return 结果
     */
    boolean saveWasteMonthReport(WasteMonthReport wasteMonthReport);

    /**
     * 修改危险废物_产生月报_01基本信息
     *
     * @param wasteMonthReport 危险废物_产生月报_01基本信息
     * @return 结果
     */
    boolean updateWasteMonthReport(WasteMonthReport wasteMonthReport);

    /**
     * 删除危险废物_产生月报_01基本信息信息
     *
     * @param id 危险废物_产生月报_01基本信息ID
     * @return 结果
     */
    boolean deleteWasteMonthReport(String id);

    /**
     * 查询危险废物_产生月报_01基本信息
     *
     * @param id 危险废物_产生月报_01基本信息ID
     * @return 危险废物_产生月报_01基本信息
     */
    WasteMonthReport findWasteMonthReportById(String id);

    /**
     * 查询危险废物_产生月报_01基本信息列表
     *
     * @param wasteMonthReport 危险废物_产生月报_01基本信息
     * @return 危险废物_产生月报_01基本信息集合
     */
    List<WasteMonthReport> findWasteMonthReportList(WasteMonthReport wasteMonthReport);

    /**
     * 分页查询危险废物_产生月报_01基本信息列表
     *
     * @param page             分页参数
     * @param wasteMonthReport 危险废物_产生月报_01基本信息
     * @return 危险废物_产生月报_01基本信息集合
     */
    PageT<WasteMonthReport> findWasteMonthReportPage(PageT<WasteMonthReport> page, WasteMonthReport wasteMonthReport);
}
