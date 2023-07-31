package com.zsgf.platform.service.standardize;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentHistory;

import java.util.List;

/**
 * 规范化考核评分记录Service接口
 *
 * @author gzl
 * @date 2023-03-24
 */
public interface WasteStandardizedAssessmentHistoryService extends IService<WasteStandardizedAssessmentHistory> {

    /**
     * 新增规范化考核评分记录
     *
     * @param wasteStandardizedAssessmentHistory 规范化考核评分记录
     * @return 结果
     */
    boolean saveWasteStandardizedAssessmentHistory(WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory);

    /**
     * 修改规范化考核评分记录
     *
     * @param wasteStandardizedAssessmentHistory 规范化考核评分记录
     * @return 结果
     */
    boolean updateWasteStandardizedAssessmentHistory(WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory);

    /**
     * 删除规范化考核评分记录信息
     *
     * @param id 规范化考核评分记录ID
     * @return 结果
     */
    boolean deleteWasteStandardizedAssessmentHistory(String id);

    /**
     * 查询规范化考核评分记录
     *
     * @param id 规范化考核评分记录ID
     * @return 规范化考核评分记录
     */
    WasteStandardizedAssessmentHistory findWasteStandardizedAssessmentHistoryById(String id);

    /**
     * 查询规范化考核评分记录列表
     *
     * @param wasteStandardizedAssessmentHistory 规范化考核评分记录
     * @return 规范化考核评分记录集合
     */
    List<WasteStandardizedAssessmentHistory> findWasteStandardizedAssessmentHistoryList(WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory);

    /**
     * 分页查询规范化考核评分记录列表
     *
     * @param page                               分页参数
     * @param wasteStandardizedAssessmentHistory 规范化考核评分记录
     * @return 规范化考核评分记录集合
     */
    PageT<WasteStandardizedAssessmentHistory> findWasteStandardizedAssessmentHistoryPage(PageT<WasteStandardizedAssessmentHistory> page, WasteStandardizedAssessmentHistory wasteStandardizedAssessmentHistory);
}
