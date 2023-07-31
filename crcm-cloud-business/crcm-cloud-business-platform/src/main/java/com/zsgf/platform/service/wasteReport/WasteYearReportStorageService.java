package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportStorage;

import java.util.List;

/**
 * 危险废物_产生年报_05贮存情况Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteYearReportStorageService extends IService<WasteYearReportStorage> {

    /**
     * 新增危险废物_产生年报_05贮存情况
     *
     * @param wasteYearReportStorage 危险废物_产生年报_05贮存情况
     * @return 结果
     */
    boolean saveWasteYearReportStorage(WasteYearReportStorage wasteYearReportStorage);

    /**
     * 修改危险废物_产生年报_05贮存情况
     *
     * @param wasteYearReportStorage 危险废物_产生年报_05贮存情况
     * @return 结果
     */
    boolean updateWasteYearReportStorage(WasteYearReportStorage wasteYearReportStorage);

    /**
     * 删除危险废物_产生年报_05贮存情况信息
     *
     * @param id 危险废物_产生年报_05贮存情况ID
     * @return 结果
     */
    boolean deleteWasteYearReportStorage(String id);

    /**
     * 查询危险废物_产生年报_05贮存情况
     *
     * @param id 危险废物_产生年报_05贮存情况ID
     * @return 危险废物_产生年报_05贮存情况
     */
    WasteYearReportStorage findWasteYearReportStorageById(String id);

    /**
     * 查询危险废物_产生年报_05贮存情况列表
     *
     * @param wasteYearReportStorage 危险废物_产生年报_05贮存情况
     * @return 危险废物_产生年报_05贮存情况集合
     */
    List<WasteYearReportStorage> findWasteYearReportStorageList(WasteYearReportStorage wasteYearReportStorage);

    /**
     * 分页查询危险废物_产生年报_05贮存情况列表
     *
     * @param page                   分页参数
     * @param wasteYearReportStorage 危险废物_产生年报_05贮存情况
     * @return 危险废物_产生年报_05贮存情况集合
     */
    PageT<WasteYearReportStorage> findWasteYearReportStoragePage(PageT<WasteYearReportStorage> page, WasteYearReportStorage wasteYearReportStorage);
}
