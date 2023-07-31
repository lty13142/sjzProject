package com.sjz.evaluations.service;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

import com.sjz.evaluations.model.entity.AssessmentScore;
import com.sjz.evaluations.model.vo.AssessmentStatics;
import com.sjz.evaluations.model.vo.scoreVo;

/**
 * 考核人员分数确认Service接口
 * 
 * @author guozhilin
 * @date 2023-04-09
 */
public interface AssessmentScoreService extends IService<AssessmentScore>{

    /**
     * 新增考核人员分数确认
     * 
     * @param assessmentScore 考核人员分数确认
     * @return 结果
     */
    boolean saveAssessmentScore(AssessmentScore assessmentScore);

    /**
     * 修改考核人员分数确认
     * 
     * @param assessmentScore 考核人员分数确认
     * @return 结果
     */
    int updateAssessmentScore(AssessmentScore assessmentScore);

    /**
     * 删除考核人员分数确认信息
     * 
     * @param id 考核人员分数确认ID
     * @return 结果
     */
    boolean deleteAssessmentScore(String id);

    /**
     * 查询考核人员分数确认
     *
     * @param id 考核人员分数确认ID
     * @return 考核人员分数确认
     */
    AssessmentScore findAssessmentScoreById(String id);

    /**
     * 查询考核人员分数确认列表
     *
     * @param assessmentScore 考核人员分数确认
     * @return 考核人员分数确认集合
     */
    List<AssessmentScore> findAssessmentScoreList(AssessmentScore assessmentScore);

    /**
     * 分页查询考核人员分数确认列表
     * @param page 分页参数
     * @param assessmentScore 考核人员分数确认
     * @return 考核人员分数确认集合
     */
    PageT<AssessmentScore> findAssessmentScorePage(PageT<AssessmentScore> page, AssessmentScore assessmentScore);

    Boolean edit(AssessmentScore assessmentScore);

    PageT<scoreVo> assessmentScorePage(PageT<AssessmentScore> page, AssessmentScore assessmentScore);

    List<AssessmentScore> lastStatistics();

    List<Map<String,Object>> scoreCount(String areaId,String yearly);

    List<Map<String,Object>> indicatorCategoryStatistics(String yearly);

    List<AssessmentStatics> assessmentStatics();
}
