package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReportSelfUse;

import java.util.List;

/**
 * 危险废物_产生月报_03废物自行利用信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteMonthReportSelfUseService extends IService<WasteMonthReportSelfUse> {

    /**
     * 新增危险废物_产生月报_03废物自行利用信息
     *
     * @param wasteMonthReportSelfUse 危险废物_产生月报_03废物自行利用信息
     * @return 结果
     */
    boolean saveWasteMonthReportSelfUse(WasteMonthReportSelfUse wasteMonthReportSelfUse);

    /**
     * 修改危险废物_产生月报_03废物自行利用信息
     *
     * @param wasteMonthReportSelfUse 危险废物_产生月报_03废物自行利用信息
     * @return 结果
     */
    boolean updateWasteMonthReportSelfUse(WasteMonthReportSelfUse wasteMonthReportSelfUse);

    /**
     * 删除危险废物_产生月报_03废物自行利用信息信息
     *
     * @param id 危险废物_产生月报_03废物自行利用信息ID
     * @return 结果
     */
    boolean deleteWasteMonthReportSelfUse(String id);

    /**
     * 查询危险废物_产生月报_03废物自行利用信息
     *
     * @param id 危险废物_产生月报_03废物自行利用信息ID
     * @return 危险废物_产生月报_03废物自行利用信息
     */
    WasteMonthReportSelfUse findWasteMonthReportSelfUseById(String id);

    /**
     * 查询危险废物_产生月报_03废物自行利用信息列表
     *
     * @param wasteMonthReportSelfUse 危险废物_产生月报_03废物自行利用信息
     * @return 危险废物_产生月报_03废物自行利用信息集合
     */
    List<WasteMonthReportSelfUse> findWasteMonthReportSelfUseList(WasteMonthReportSelfUse wasteMonthReportSelfUse);

    /**
     * 分页查询危险废物_产生月报_03废物自行利用信息列表
     *
     * @param page                    分页参数
     * @param wasteMonthReportSelfUse 危险废物_产生月报_03废物自行利用信息
     * @return 危险废物_产生月报_03废物自行利用信息集合
     */
    PageT<WasteMonthReportSelfUse> findWasteMonthReportSelfUsePage(PageT<WasteMonthReportSelfUse> page, WasteMonthReportSelfUse wasteMonthReportSelfUse);
}
