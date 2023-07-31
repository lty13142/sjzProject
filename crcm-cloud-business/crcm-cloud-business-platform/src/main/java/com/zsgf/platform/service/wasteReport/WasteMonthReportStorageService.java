package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReportStorage;

import java.util.List;

/**
 * 危险废物_产生月报_05产废贮存信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteMonthReportStorageService extends IService<WasteMonthReportStorage> {

    /**
     * 新增危险废物_产生月报_05产废贮存信息
     *
     * @param wasteMonthReportStorage 危险废物_产生月报_05产废贮存信息
     * @return 结果
     */
    boolean saveWasteMonthReportStorage(WasteMonthReportStorage wasteMonthReportStorage);

    /**
     * 修改危险废物_产生月报_05产废贮存信息
     *
     * @param wasteMonthReportStorage 危险废物_产生月报_05产废贮存信息
     * @return 结果
     */
    boolean updateWasteMonthReportStorage(WasteMonthReportStorage wasteMonthReportStorage);

    /**
     * 删除危险废物_产生月报_05产废贮存信息信息
     *
     * @param id 危险废物_产生月报_05产废贮存信息ID
     * @return 结果
     */
    boolean deleteWasteMonthReportStorage(String id);

    /**
     * 查询危险废物_产生月报_05产废贮存信息
     *
     * @param id 危险废物_产生月报_05产废贮存信息ID
     * @return 危险废物_产生月报_05产废贮存信息
     */
    WasteMonthReportStorage findWasteMonthReportStorageById(String id);

    /**
     * 查询危险废物_产生月报_05产废贮存信息列表
     *
     * @param wasteMonthReportStorage 危险废物_产生月报_05产废贮存信息
     * @return 危险废物_产生月报_05产废贮存信息集合
     */
    List<WasteMonthReportStorage> findWasteMonthReportStorageList(WasteMonthReportStorage wasteMonthReportStorage);

    /**
     * 分页查询危险废物_产生月报_05产废贮存信息列表
     *
     * @param page                    分页参数
     * @param wasteMonthReportStorage 危险废物_产生月报_05产废贮存信息
     * @return 危险废物_产生月报_05产废贮存信息集合
     */
    PageT<WasteMonthReportStorage> findWasteMonthReportStoragePage(PageT<WasteMonthReportStorage> page, WasteMonthReportStorage wasteMonthReportStorage);
}
