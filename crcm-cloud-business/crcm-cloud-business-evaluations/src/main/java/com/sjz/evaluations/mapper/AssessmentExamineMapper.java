package com.sjz.evaluations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjz.evaluations.model.entity.AssessmentExamine;

import java.util.List;

/**
 * 考核指标Mapper接口
 * 
 * @author guozhilin
 * @date 2023-04-06
 */
public interface AssessmentExamineMapper extends BaseMapper<AssessmentExamine> {


    List<AssessmentExamine> findParentment(String id);
}
