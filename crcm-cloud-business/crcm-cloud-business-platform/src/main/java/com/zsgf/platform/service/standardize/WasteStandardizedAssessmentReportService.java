package com.zsgf.platform.service.standardize;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentReport;

import java.util.List;

/**
 * 标准化考核整改报告Service接口
 *
 * @author gzl
 * @date 2023-03-24
 */
public interface WasteStandardizedAssessmentReportService extends IService<WasteStandardizedAssessmentReport> {

    /**
     * 新增标准化考核整改报告
     *
     * @param wasteStandardizedAssessmentReport 标准化考核整改报告
     * @return 结果
     */
    boolean saveWasteStandardizedAssessmentReport(WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport);

    /**
     * 修改标准化考核整改报告
     *
     * @param wasteStandardizedAssessmentReport 标准化考核整改报告
     * @return 结果
     */
    boolean updateWasteStandardizedAssessmentReport(WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport);

    /**
     * 删除标准化考核整改报告信息
     *
     * @param id 标准化考核整改报告ID
     * @return 结果
     */
    boolean deleteWasteStandardizedAssessmentReport(String id);

    /**
     * 查询标准化考核整改报告
     *
     * @param id 标准化考核整改报告ID
     * @return 标准化考核整改报告
     */
    WasteStandardizedAssessmentReport findWasteStandardizedAssessmentReportById(String id);

    /**
     * 查询标准化考核整改报告列表
     *
     * @param wasteStandardizedAssessmentReport 标准化考核整改报告
     * @return 标准化考核整改报告集合
     */
    List<WasteStandardizedAssessmentReport> findWasteStandardizedAssessmentReportList(WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport);

    /**
     * 分页查询标准化考核整改报告列表
     *
     * @param page                              分页参数
     * @param wasteStandardizedAssessmentReport 标准化考核整改报告
     * @return 标准化考核整改报告集合
     */
    PageT<WasteStandardizedAssessmentReport> findWasteStandardizedAssessmentReportPage(PageT<WasteStandardizedAssessmentReport> page, WasteStandardizedAssessmentReport wasteStandardizedAssessmentReport);
}
