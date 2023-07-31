package com.sjz.education.mapper;

import com.sjz.education.model.entity.EduPointsRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjz.education.model.vo.PointsRuleTreeView;

import java.util.List;

/**
* @author SSSCCCC
* @description 针对表【edu_points_rule(积分规则管理)】的数据库操作Mapper
* @createDate 2023-04-03 16:49:38
* @Entity com.sjz.education.model.entity.EduPointsRule
*/
public interface EduPointsRuleMapper extends BaseMapper<EduPointsRule> {
    /**
     * 积分规则树形结构查询
     * @param t,ruleType,ruleName,ruleDetail,scoringMethod,number
     * @return
     */
    List<PointsRuleTreeView> findTree(EduPointsRule t);
}




