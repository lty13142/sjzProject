package com.sjz.evaluations.service;

import cn.hutool.core.lang.tree.Tree;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.sjz.evaluations.model.entity.AssessmentExamine;
import com.sjz.evaluations.model.vo.AssessmentExamineVo;

/**
 * 考核指标Service接口
 * 
 * @author guozhilin
 * @date 2023-04-06
 */
public interface AssessmentExamineService extends IService<AssessmentExamine>{

    /**
     * 新增考核指标
     * 
     * @param assessmentExamine 考核指标
     * @return 结果
     */
    boolean saveAssessmentExamine(AssessmentExamine assessmentExamine);

    /**
     * 修改考核指标
     * 
     * @param assessmentExamine 考核指标
     * @return 结果
     */
    boolean updateAssessmentExamine(AssessmentExamine assessmentExamine);

    /**
     * 删除考核指标信息
     * 
     * @param id 考核指标ID
     * @return 结果
     */
    boolean deleteAssessmentExamine(String id);

    /**
     * 查询考核指标
     *
     * @param id 考核指标ID
     * @return 考核指标
     */
    AssessmentExamine findAssessmentExamineById(String id);

    /**
     * 查询考核指标列表
     *
     * @param assessmentExamine 考核指标
     * @return 考核指标集合
     */
    List<AssessmentExamine> findAssessmentExamineList(AssessmentExamine assessmentExamine);

    /**
     * 分页查询考核指标列表
     * @param page 分页参数
     * @param assessmentExamine 考核指标
     * @return 考核指标集合
     */
    PageT<AssessmentExamineVo> findAssessmentExaminePage(PageT<AssessmentExamine> page, AssessmentExamine assessmentExamine);

    int deleteByLastId(String id);

    boolean conference(AssessmentExamine assessmentExamine);
}
