package com.sjz.evaluations.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.evaluations.model.dto.ExamineResultDTO;
import com.sjz.evaluations.model.dto.ExamineScoreDTO;
import com.sjz.evaluations.model.dto.RegionScoreDTO;
import com.sjz.evaluations.model.dto.VillageScoreDTO;
import com.sjz.evaluations.model.entity.ExamineTask;
import com.sjz.evaluations.model.vo.ExamineResultTableVO;
import com.sjz.evaluations.model.vo.ExamineScoreVO;
import com.sjz.evaluations.model.vo.RegionScoreVO;
import com.sjz.evaluations.model.vo.VillageIndicatorsVO;

import java.util.List;

/**
 * 考核Service接口
 *
 * @author zzyt
 * @date 2023-04-25
 */
public interface ExamineTaskService extends IService<ExamineTask> {

    /**
     * 新增考核
     *
     * @param examineTask 考核
     * @return 结果
     */
    boolean saveExamineTask(ExamineTask examineTask);

    /**
     * 修改考核
     *
     * @param examineTask 考核
     * @return 结果
     */
    boolean updateExamineTask(ExamineTask examineTask);

    /**
     * 删除考核信息
     *
     * @param id 考核ID
     * @return 结果
     */
    boolean deleteExamineTask(String id);

    /**
     * 查询考核
     *
     * @param id 考核ID
     * @return 考核
     */
    ExamineTask findExamineTaskById(String id);

    /**
     * 查询考核列表
     *
     * @param examineTask 考核
     * @return 考核集合
     */
    List<ExamineTask> findExamineTaskList(ExamineTask examineTask);

    /**
     * 分页查询考核列表
     *
     * @param page        分页参数
     * @param examineTask 考核
     * @return 考核集合
     */
    IPage<ExamineTask> findExamineTaskPage(PageT page, ExamineTask examineTask);

    /**
     * 查询管区考核任务分页
     *
     * @param page        分页参数
     * @param examineTask 考核
     * @return 管区考核任务分页
     */
    IPage<ExamineTask> findRegionExamineTaskPage(PageT page, ExamineTask examineTask);

    /**
     * 查询村级考核任务分页
     *
     * @param page        分页参数
     * @param examineTask 考核
     * @return 村级考核任务分页
     */
    IPage<ExamineTask> findVillageExamineTaskPage(PageT page, ExamineTask examineTask);

    /**
     * 查询管区的考核评分
     *
     * @param page            分页参数
     * @param examineScoreDTO 考核
     * @return 管区考核评分分页
     */
    IPage<ExamineScoreVO> findRegionScorePage(PageT page, ExamineScoreDTO examineScoreDTO);


    /**
     * 查询村级考核评分
     *
     * @param page            分页参数
     * @param villageScoreDTO 请求参数
     * @return 村级-指标考核分页列表
     */
    IPage<VillageIndicatorsVO> findVillageScorePage(PageT page, VillageScoreDTO villageScoreDTO);

    /**
     * 查询镇级考核任务评分
     *
     * @param page            分页参数
     * @param examineScoreDTO 考核
     * @return 所有考核任务评分分页
     */
    IPage<ExamineScoreVO> findTownScorePage(PageT page, ExamineScoreDTO examineScoreDTO);

    /**
     * 查询考核任务下所有管区的评分情况分页
     *
     * @param page           分页参数分页参数
     * @param regionScoreDTO 考核
     * @return 该考核任务下所有管区评分详情
     */
    IPage<RegionScoreVO> findTaskRegionScorePage(PageT page, RegionScoreDTO regionScoreDTO);

    /**
     * 查询镇级考核结果
     *
     * @param examineResultDTO 考核结果请求实体
     * @return 镇级考核结果
     */
    ExamineResultTableVO findTownExamineResult(ExamineResultDTO examineResultDTO);

    /**
     * 查询管区考核结果
     *
     * @param examineResultDTO 考核结果请求实体
     * @return 管区考核结果
     */
    ExamineResultTableVO findRegionExamineResult(ExamineResultDTO examineResultDTO);


    /**
     * 查询村级考核结果
     *
     * @param page 分页数据
     * @param villageScoreDTO 查询条件
     * @return 村级考核结果分页
     */
    IPage<VillageIndicatorsVO> findVillageExamineResult(PageT page, VillageScoreDTO villageScoreDTO);
}
