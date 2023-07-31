package com.sjz.evaluations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.evaluations.model.entity.AssessmentScore;
import com.sjz.evaluations.model.vo.scoreVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 考核人员分数确认Mapper接口
 * 
 * @author guozhilin
 * @date 2023-04-09
 */
public interface AssessmentScoreMapper extends BaseMapper<AssessmentScore> {


    PageT<scoreVo> assessmentScorePage(PageT<AssessmentScore> page, @Param("assessmentScore") AssessmentScore assessmentScore);

    List<AssessmentScore> lastStatistics(@Param("year") String year);

    List<Map<String, Object>> scoreCount(@Param("areaId")String areaId,@Param("year") String year);

    List<Map<String, Object>> indicatorCategoryStatistics(@Param("year") String year);

    List<AssessmentScore> getAllList();

    List<AssessmentScore> getAllListLast();
}

