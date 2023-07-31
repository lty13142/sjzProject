package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjz.partbuilding.model.entity.WorkFeedback;
import com.sjz.partbuilding.model.vo.WorkFeedBackVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 工作反馈mapper
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/17 11:29
 */
public interface WorkFeedbackMapper extends BaseMapper<WorkFeedback> {

    /**
     * 真实删除
     *
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 根据taskId获取提报情况
     *
     * @param page           分页参数
     * @param workFeedBackVo 任务id
     * @return
     */
    List<WorkFeedBackVo> getWorkReportingSituation(Page page, @Param("workFeedBackVo") WorkFeedBackVo workFeedBackVo);

    /**
     * 根据org_id和12月前的月份查询出所有工作反馈
     *
     * @param orgId      组织id
     * @param monthArray 12月前的月份
     * @return
     */
    List<WorkFeedBackVo> getStatisticsOfWorkFeedback(@Param("orgId") String orgId, @Param("monthArray") String monthArray);

    /**
     * 获取数据统计工作督办
     *
     * @param page         分页参数
     * @param workFeedBackVo 查询条件
     * @return
     */
    List<WorkFeedBackVo> getDataStatisticsWorkSupervision(Page page, @Param("workFeedBackVo") WorkFeedBackVo workFeedBackVo);

    /**
     * 获取数据统计工作督办
     *
     * @param workFeedBackVo 查询条件
     * @return
     */
    List<WorkFeedBackVo> getDataStatisticsWorkSupervisionList(@Param("workFeedBackVo") WorkFeedBackVo workFeedBackVo);
}
