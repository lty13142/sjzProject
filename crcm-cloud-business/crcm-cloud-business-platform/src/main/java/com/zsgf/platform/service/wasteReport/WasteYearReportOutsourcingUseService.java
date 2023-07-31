package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportOutsourcingUse;

import java.util.List;

/**
 * 危险废物_产生年报_04委外利用情况Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteYearReportOutsourcingUseService extends IService<WasteYearReportOutsourcingUse> {

    /**
     * 新增危险废物_产生年报_04委外利用情况
     *
     * @param wasteYearReportOutsourcingUse 危险废物_产生年报_04委外利用情况
     * @return 结果
     */
    boolean saveWasteYearReportOutsourcingUse(WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse);

    /**
     * 修改危险废物_产生年报_04委外利用情况
     *
     * @param wasteYearReportOutsourcingUse 危险废物_产生年报_04委外利用情况
     * @return 结果
     */
    boolean updateWasteYearReportOutsourcingUse(WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse);

    /**
     * 删除危险废物_产生年报_04委外利用情况信息
     *
     * @param id 危险废物_产生年报_04委外利用情况ID
     * @return 结果
     */
    boolean deleteWasteYearReportOutsourcingUse(String id);

    /**
     * 查询危险废物_产生年报_04委外利用情况
     *
     * @param id 危险废物_产生年报_04委外利用情况ID
     * @return 危险废物_产生年报_04委外利用情况
     */
    WasteYearReportOutsourcingUse findWasteYearReportOutsourcingUseById(String id);

    /**
     * 查询危险废物_产生年报_04委外利用情况列表
     *
     * @param wasteYearReportOutsourcingUse 危险废物_产生年报_04委外利用情况
     * @return 危险废物_产生年报_04委外利用情况集合
     */
    List<WasteYearReportOutsourcingUse> findWasteYearReportOutsourcingUseList(WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse);

    /**
     * 分页查询危险废物_产生年报_04委外利用情况列表
     *
     * @param page                          分页参数
     * @param wasteYearReportOutsourcingUse 危险废物_产生年报_04委外利用情况
     * @return 危险废物_产生年报_04委外利用情况集合
     */
    PageT<WasteYearReportOutsourcingUse> findWasteYearReportOutsourcingUsePage(PageT<WasteYearReportOutsourcingUse> page, WasteYearReportOutsourcingUse wasteYearReportOutsourcingUse);
}
