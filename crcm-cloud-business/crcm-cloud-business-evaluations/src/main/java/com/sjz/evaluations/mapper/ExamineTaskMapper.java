package com.sjz.evaluations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.evaluations.model.dto.ExamineResultDTO;
import com.sjz.evaluations.model.dto.ExamineScoreDTO;
import com.sjz.evaluations.model.dto.RegionScoreDTO;
import com.sjz.evaluations.model.dto.VillageScoreDTO;
import com.sjz.evaluations.model.entity.ExamineTask;
import com.sjz.evaluations.model.vo.ExamineResultInfoVO;
import com.sjz.evaluations.model.vo.ExamineScoreVO;
import com.sjz.evaluations.model.vo.RegionScoreVO;
import com.sjz.evaluations.model.vo.VillageIndicatorsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考核Mapper接口
 *
 * @author zzyt
 * @date 2023-04-25
 */
public interface ExamineTaskMapper extends BaseMapper<ExamineTask> {


    /**
     * 查询管区考核任务分页
     *
     * @param page        分页参数
     * @param examineTask 考核
     * @param areaCode    管区code
     * @return 管区考核任务分页
     */
    IPage<ExamineTask> findRegionExamineTaskPage(PageT page, @Param("examineTask") ExamineTask examineTask, @Param("areaCode") String areaCode);

    /**
     * 查询村级考核任务分页
     *
     * @param page        分页参数
     * @param examineTask 考核
     * @param areaCode    村级code
     * @return 村级考核任务分页
     */
    IPage<ExamineTask> findVillageExamineTaskPage(PageT page, @Param("examineTask") ExamineTask examineTask, @Param("areaCode") String areaCode);

    /**
     * 查询管区的考核评分
     *
     * @param page            分页参数
     * @param examineScoreDTO 考核
     * @param areaCode        管区code
     * @return 管区考核评分分页
     */
    IPage<ExamineScoreVO> findRegionScorePage(PageT page, @Param("examineScoreDTO") ExamineScoreDTO examineScoreDTO, @Param("areaCode") String areaCode);


    /**
     * 查询村级考核评分
     *
     * @param page            分页参数
     * @param villageScoreDTO 请求参数
     * @return 村级-指标考核分页列表
     */
    IPage<VillageIndicatorsVO> findVillageScorePage(PageT page, @Param("villageScoreDTO") VillageScoreDTO villageScoreDTO);

    /**
     * 查询镇级考核任务评分
     *
     * @param page            分页参数
     * @param examineScoreDTO 考核
     * @return 所有考核任务评分分页
     */
    IPage<ExamineScoreVO> findTownScorePage(PageT page, @Param("examineScoreDTO") ExamineScoreDTO examineScoreDTO);

    /**
     * 查询考核任务下所有管区的评分情况分页
     *
     * @param page           分页参数分页参数
     * @param regionScoreDTO 考核
     * @return 该考核任务下所有管区评分详情
     */
    IPage<RegionScoreVO> findTaskRegionScorePage(PageT page, @Param("examineScoreDTO") RegionScoreDTO regionScoreDTO);


    /**
     * 查询当前考核任务下的所有村级考核信息
     *
     * @param examineResultDTO 考核结果请求实体
     * @return 所有村级考核信息集合
     */
    List<ExamineResultInfoVO> findTownExamineResultList(@Param("examineResultDTO") ExamineResultDTO examineResultDTO);

    /**
     * 查询当前考核任务下的所有指标,指标的顺序必须和上面一个接口相同
     *
     * @param examineResultDTO 考核结果请求实体
     * @return 所有村级考核信息集合
     */
    List<ExamineResultInfoVO> findTownExamineIndicatorsList(@Param("examineResultDTO") ExamineResultDTO examineResultDTO);

    /**
     * 查询村级考核结果
     *
     * @param page            分页数据
     * @param villageScoreDTO 查询条件
     * @return 村级考核结果分页
     */
    IPage<VillageIndicatorsVO> findVillageExamineResult(PageT page, @Param("villageScoreDTO") VillageScoreDTO villageScoreDTO);
}
