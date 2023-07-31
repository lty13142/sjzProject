package com.sjz.evaluations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.evaluations.model.dto.ExamineIndicatorsSaveDTO;
import com.sjz.evaluations.model.entity.ExamineIndicators;
import com.sjz.evaluations.model.vo.RegionIndicatorsVO;
import com.sjz.evaluations.model.vo.VillageIndicatorsVO;
import org.apache.ibatis.annotations.Param;

/**
 * 考核指标Mapper接口
 *
 * @author zzyt
 * @date 2023-04-25
 */
public interface ExamineIndicatorsMapper extends BaseMapper<ExamineIndicators> {


    /**
     * 分页查询管区考核指标
     *
     * @param page              分页参数
     * @param examineIndicators 考核指标
     * @param areaCode          管区区域代码
     * @return 管区考核指标集合
     */
    IPage<RegionIndicatorsVO> findRegionIndicatorsPage(PageT page, @Param("examineIndicators") ExamineIndicators examineIndicators, @Param("areaCode") String areaCode);

    /**
     * 分页查询村级考核指标
     *
     * @param page              分页参数
     * @param examineIndicators 考核指标
     * @return 村级考核指标集合
     */
    IPage<VillageIndicatorsVO> findVillageIndicatorsPage(PageT page, @Param("examineIndicators") ExamineIndicators examineIndicators, @Param("areaCode") String areaCode);

    /**
     * 根据id查询考核指标扩展类
     *
     * @param id 指标id
     * @return 考核指标扩展类
     */
    ExamineIndicatorsSaveDTO findExamineIndicatorsById(@Param("id") String id);
}
