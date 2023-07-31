package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReportOutsourcingUse;

import java.util.List;

/**
 * 危险废物_产生月报_04废物委外利用信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteMonthReportOutsourcingUseService extends IService<WasteMonthReportOutsourcingUse> {

    /**
     * 新增危险废物_产生月报_04废物委外利用信息
     *
     * @param wasteMonthReportOutsourcingUse 危险废物_产生月报_04废物委外利用信息
     * @return 结果
     */
    boolean saveWasteMonthReportOutsourcingUse(WasteMonthReportOutsourcingUse wasteMonthReportOutsourcingUse);

    /**
     * 修改危险废物_产生月报_04废物委外利用信息
     *
     * @param wasteMonthReportOutsourcingUse 危险废物_产生月报_04废物委外利用信息
     * @return 结果
     */
    boolean updateWasteMonthReportOutsourcingUse(WasteMonthReportOutsourcingUse wasteMonthReportOutsourcingUse);

    /**
     * 删除危险废物_产生月报_04废物委外利用信息信息
     *
     * @param id 危险废物_产生月报_04废物委外利用信息ID
     * @return 结果
     */
    boolean deleteWasteMonthReportOutsourcingUse(String id);

    /**
     * 查询危险废物_产生月报_04废物委外利用信息
     *
     * @param id 危险废物_产生月报_04废物委外利用信息ID
     * @return 危险废物_产生月报_04废物委外利用信息
     */
    WasteMonthReportOutsourcingUse findWasteMonthReportOutsourcingUseById(String id);

    /**
     * 查询危险废物_产生月报_04废物委外利用信息列表
     *
     * @param wasteMonthReportOutsourcingUse 危险废物_产生月报_04废物委外利用信息
     * @return 危险废物_产生月报_04废物委外利用信息集合
     */
    List<WasteMonthReportOutsourcingUse> findWasteMonthReportOutsourcingUseList(WasteMonthReportOutsourcingUse wasteMonthReportOutsourcingUse);

    /**
     * 分页查询危险废物_产生月报_04废物委外利用信息列表
     *
     * @param page                           分页参数
     * @param wasteMonthReportOutsourcingUse 危险废物_产生月报_04废物委外利用信息
     * @return 危险废物_产生月报_04废物委外利用信息集合
     */
    PageT<WasteMonthReportOutsourcingUse> findWasteMonthReportOutsourcingUsePage(PageT<WasteMonthReportOutsourcingUse> page, WasteMonthReportOutsourcingUse wasteMonthReportOutsourcingUse);
}
