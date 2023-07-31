package com.zsgf.platform.service.wasteReport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.wasteReport.WasteYearReportWasteGeneration;

import java.util.List;

/**
 * 危险废物_产生年报_02废物产生情况Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface WasteYearReportWasteGenerationService extends IService<WasteYearReportWasteGeneration> {

    /**
     * 新增危险废物_产生年报_02废物产生情况
     *
     * @param wasteYearReportWasteGeneration 危险废物_产生年报_02废物产生情况
     * @return 结果
     */
    boolean saveWasteYearReportWasteGeneration(WasteYearReportWasteGeneration wasteYearReportWasteGeneration);

    /**
     * 修改危险废物_产生年报_02废物产生情况
     *
     * @param wasteYearReportWasteGeneration 危险废物_产生年报_02废物产生情况
     * @return 结果
     */
    boolean updateWasteYearReportWasteGeneration(WasteYearReportWasteGeneration wasteYearReportWasteGeneration);

    /**
     * 删除危险废物_产生年报_02废物产生情况信息
     *
     * @param id 危险废物_产生年报_02废物产生情况ID
     * @return 结果
     */
    boolean deleteWasteYearReportWasteGeneration(String id);

    /**
     * 查询危险废物_产生年报_02废物产生情况
     *
     * @param id 危险废物_产生年报_02废物产生情况ID
     * @return 危险废物_产生年报_02废物产生情况
     */
    WasteYearReportWasteGeneration findWasteYearReportWasteGenerationById(String id);

    /**
     * 查询危险废物_产生年报_02废物产生情况列表
     *
     * @param wasteYearReportWasteGeneration 危险废物_产生年报_02废物产生情况
     * @return 危险废物_产生年报_02废物产生情况集合
     */
    List<WasteYearReportWasteGeneration> findWasteYearReportWasteGenerationList(WasteYearReportWasteGeneration wasteYearReportWasteGeneration);

    /**
     * 分页查询危险废物_产生年报_02废物产生情况列表
     *
     * @param page                           分页参数
     * @param wasteYearReportWasteGeneration 危险废物_产生年报_02废物产生情况
     * @return 危险废物_产生年报_02废物产生情况集合
     */
    PageT<WasteYearReportWasteGeneration> findWasteYearReportWasteGenerationPage(PageT<WasteYearReportWasteGeneration> page, WasteYearReportWasteGeneration wasteYearReportWasteGeneration);
}
