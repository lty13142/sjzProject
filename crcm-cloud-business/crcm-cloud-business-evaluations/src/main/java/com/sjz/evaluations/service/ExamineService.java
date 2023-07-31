package com.sjz.evaluations.service;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.evaluations.model.entity.Examine;
import com.sjz.evaluations.model.vo.ExamineVo;

import java.util.List;


/**
 * 考核Service接口
 * 
 * @author guozhilin
 * @date 2023-04-06
 */
public interface ExamineService extends IService<Examine>{

    /**
     * 新增考核
     * 
     * @param examine 考核
     * @return 结果
     */
    boolean saveExamine(Examine examine);

    /**
     * 修改考核
     * 
     * @param examine 考核
     * @return 结果
     */
    boolean updateExamine(Examine examine);

    /**
     * 删除考核信息
     * 
     * @param id 考核ID
     * @return 结果
     */
    boolean deleteExamine(String id);

    /**
     * 查询考核
     *
     * @param id 考核ID
     * @return 考核
     */
    Examine findExamineById(String id);

    /**
     * 查询考核列表
     *
     * @param examine 考核
     * @return 考核集合
     */
    List<Examine> findExamineList(Examine examine);

    /**
     * 分页查询考核列表
     * @param page 分页参数
     * @param examine 考核
     * @return 考核集合
     */
    PageT<Examine> findExaminePage(PageT<Examine> page, Examine examine);

    PageT<ExamineVo> getAssessedPage(PageT<ExamineVo> page, Examine examine);
}
