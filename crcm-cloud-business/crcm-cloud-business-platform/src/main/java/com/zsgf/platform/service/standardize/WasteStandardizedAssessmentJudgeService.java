package com.zsgf.platform.service.standardize;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentJudge;

import java.util.List;

/**
 * 规范化考核结果Service接口
 *
 * @author gzl
 * @date 2023-03-24
 */
public interface WasteStandardizedAssessmentJudgeService extends IService<WasteStandardizedAssessmentJudge> {

    /**
     * 新增规范化考核结果
     *
     * @param wasteStandardizedAssessmentJudge 规范化考核结果
     * @return 结果
     */
    boolean saveWasteStandardizedAssessmentJudge(WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge);

    /**
     * 修改规范化考核结果
     *
     * @param wasteStandardizedAssessmentJudge 规范化考核结果
     * @return 结果
     */
    boolean updateWasteStandardizedAssessmentJudge(WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge);

    /**
     * 删除规范化考核结果信息
     *
     * @param id 规范化考核结果ID
     * @return 结果
     */
    boolean deleteWasteStandardizedAssessmentJudge(String id);

    /**
     * 查询规范化考核结果
     *
     * @param id 规范化考核结果ID
     * @return 规范化考核结果
     */
    WasteStandardizedAssessmentJudge findWasteStandardizedAssessmentJudgeById(String id);

    /**
     * 查询规范化考核结果列表
     *
     * @param wasteStandardizedAssessmentJudge 规范化考核结果
     * @return 规范化考核结果集合
     */
    List<WasteStandardizedAssessmentJudge> findWasteStandardizedAssessmentJudgeList(WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge);

    /**
     * 分页查询规范化考核结果列表
     *
     * @param page                             分页参数
     * @param wasteStandardizedAssessmentJudge 规范化考核结果
     * @return 规范化考核结果集合
     */
    PageT<WasteStandardizedAssessmentJudge> findWasteStandardizedAssessmentJudgePage(PageT<WasteStandardizedAssessmentJudge> page, WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge);
}
