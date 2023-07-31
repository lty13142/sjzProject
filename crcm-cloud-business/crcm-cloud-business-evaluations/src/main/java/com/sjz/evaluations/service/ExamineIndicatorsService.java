package com.sjz.evaluations.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.evaluations.model.dto.ExamineIndicatorsSaveDTO;
import com.sjz.evaluations.model.entity.ExamineIndicators;
import com.sjz.evaluations.model.vo.RegionIndicatorsVO;
import com.sjz.evaluations.model.vo.VillageIndicatorsVO;

import java.util.List;

/**
 * 考核指标Service接口
 *
 * @author zzyt
 * @date 2023-04-25
 */
public interface ExamineIndicatorsService extends IService<ExamineIndicators> {

    /**
     * 新增考核指标
     *
     * @param examineIndicatorsSaveDTO 考核指标
     * @return 结果
     */
    boolean saveExamineIndicators(ExamineIndicatorsSaveDTO examineIndicatorsSaveDTO);

    /**
     * 修改考核指标
     *
     * @param examineIndicatorsSaveDTO 考核指标
     * @return 结果
     */
    boolean updateExamineIndicators(ExamineIndicatorsSaveDTO examineIndicatorsSaveDTO);

    /**
     * 删除考核指标信息
     *
     * @param id 考核指标ID
     * @return 结果
     */
    boolean deleteExamineIndicators(String id);

    /**
     * 查询考核指标
     *
     * @param id 考核指标ID
     * @return 考核指标
     */
    ExamineIndicators findExamineIndicatorsById(String id);

    /**
     * 查询考核指标列表
     *
     * @param examineIndicators 考核指标
     * @return 考核指标集合
     */
    List<ExamineIndicators> findExamineIndicatorsList(ExamineIndicators examineIndicators);

    /**
     * 分页查询考核指标列表
     *
     * @param page              分页参数
     * @param examineIndicators 考核指标
     * @return 考核指标集合
     */
    IPage<ExamineIndicators> findExamineIndicatorsPage(PageT page, ExamineIndicators examineIndicators);

    /**
     * 分页查询管区考核指标
     *
     * @param page              分页参数
     * @param examineIndicators 考核指标
     * @return 管区考核指标集合
     */
    IPage<RegionIndicatorsVO> findRegionIndicatorsPage(PageT page, ExamineIndicators examineIndicators);

    /**
     * 分页查询村级考核指标
     *
     * @param page              分页参数
     * @param examineIndicators 考核指标
     * @return 村级考核指标集合
     */
    IPage<VillageIndicatorsVO> findVillageIndicatorsPage(PageT page, ExamineIndicators examineIndicators);

}
