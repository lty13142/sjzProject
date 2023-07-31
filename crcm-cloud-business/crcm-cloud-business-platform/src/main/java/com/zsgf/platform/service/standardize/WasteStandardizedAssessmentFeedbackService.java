package com.zsgf.platform.service.standardize;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentFeedback;

import java.util.List;

/**
 * 规范化考核整改反馈Service接口
 *
 * @author gzl
 * @date 2023-03-24
 */
public interface WasteStandardizedAssessmentFeedbackService extends IService<WasteStandardizedAssessmentFeedback> {

    /**
     * 新增规范化考核整改反馈
     *
     * @param wasteStandardizedAssessmentFeedback 规范化考核整改反馈
     * @return 结果
     */
    boolean saveWasteStandardizedAssessmentFeedback(WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback);

    /**
     * 修改规范化考核整改反馈
     *
     * @param wasteStandardizedAssessmentFeedback 规范化考核整改反馈
     * @return 结果
     */
    boolean updateWasteStandardizedAssessmentFeedback(WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback);

    /**
     * 删除规范化考核整改反馈信息
     *
     * @param id 规范化考核整改反馈ID
     * @return 结果
     */
    boolean deleteWasteStandardizedAssessmentFeedback(String id);

    /**
     * 查询规范化考核整改反馈
     *
     * @param id 规范化考核整改反馈ID
     * @return 规范化考核整改反馈
     */
    WasteStandardizedAssessmentFeedback findWasteStandardizedAssessmentFeedbackById(String id);

    /**
     * 查询规范化考核整改反馈列表
     *
     * @param wasteStandardizedAssessmentFeedback 规范化考核整改反馈
     * @return 规范化考核整改反馈集合
     */
    List<WasteStandardizedAssessmentFeedback> findWasteStandardizedAssessmentFeedbackList(WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback);

    /**
     * 分页查询规范化考核整改反馈列表
     *
     * @param page                                分页参数
     * @param wasteStandardizedAssessmentFeedback 规范化考核整改反馈
     * @return 规范化考核整改反馈集合
     */
    PageT<WasteStandardizedAssessmentFeedback> findWasteStandardizedAssessmentFeedbackPage(PageT<WasteStandardizedAssessmentFeedback> page, WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback);
}
