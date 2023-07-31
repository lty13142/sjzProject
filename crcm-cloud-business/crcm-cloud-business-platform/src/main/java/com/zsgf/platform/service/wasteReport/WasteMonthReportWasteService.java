package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteMonthReportWaste;

import java.util.List;

/**
 * 危险废物_产生月报_02产废信息Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteMonthReportWasteService extends IService<WasteMonthReportWaste> {

    /**
     * 新增危险废物_产生月报_02产废信息
     *
     * @param wasteMonthReportWaste 危险废物_产生月报_02产废信息
     * @return 结果
     */
    boolean saveWasteMonthReportWaste(WasteMonthReportWaste wasteMonthReportWaste);

    /**
     * 修改危险废物_产生月报_02产废信息
     *
     * @param wasteMonthReportWaste 危险废物_产生月报_02产废信息
     * @return 结果
     */
    boolean updateWasteMonthReportWaste(WasteMonthReportWaste wasteMonthReportWaste);

    /**
     * 删除危险废物_产生月报_02产废信息信息
     *
     * @param id 危险废物_产生月报_02产废信息ID
     * @return 结果
     */
    boolean deleteWasteMonthReportWaste(String id);

    /**
     * 查询危险废物_产生月报_02产废信息
     *
     * @param id 危险废物_产生月报_02产废信息ID
     * @return 危险废物_产生月报_02产废信息
     */
    WasteMonthReportWaste findWasteMonthReportWasteById(String id);

    /**
     * 查询危险废物_产生月报_02产废信息列表
     *
     * @param wasteMonthReportWaste 危险废物_产生月报_02产废信息
     * @return 危险废物_产生月报_02产废信息集合
     */
    List<WasteMonthReportWaste> findWasteMonthReportWasteList(WasteMonthReportWaste wasteMonthReportWaste);

    /**
     * 分页查询危险废物_产生月报_02产废信息列表
     *
     * @param page                  分页参数
     * @param wasteMonthReportWaste 危险废物_产生月报_02产废信息
     * @return 危险废物_产生月报_02产废信息集合
     */
    PageT<WasteMonthReportWaste> findWasteMonthReportWastePage(PageT<WasteMonthReportWaste> page, WasteMonthReportWaste wasteMonthReportWaste);
}
