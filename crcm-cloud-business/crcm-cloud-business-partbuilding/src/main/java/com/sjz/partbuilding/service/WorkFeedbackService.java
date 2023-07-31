package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.WorkFeedback;
import com.sjz.partbuilding.model.vo.WorkFeedBackVo;

import java.util.List;
import java.util.Map;


/**
 * 工作反馈service
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/17 11:29
 */
public interface WorkFeedbackService extends IService<WorkFeedback> {

    /**
     * 新增工作反馈
     *
     * @param workFeedback 新增字段
     * @return
     */
    int saveWorkFeedback(WorkFeedback workFeedback);

    /**
     * 修改工作反馈
     *
     * @param workFeedback 修改字段
     * @return
     */
    int updateWorkFeedback(WorkFeedback workFeedback);

    /**
     * 根据id删除(逻辑删除)
     *
     * @param id 删除条件
     * @return
     */
    int deleteById(String id);

    /**
     * 真实删除
     *
     * @param id 删除条件
     * @return
     */
    int realDelete(String id);

    /**
     * 根据id查询
     *
     * @param id 查询条件
     * @return
     */
    WorkFeedback findById(String id);

    /**
     * 查询列表
     *
     * @param workFeedback 查询条件
     * @return
     */
    List<WorkFeedback> findList(WorkFeedback workFeedback);

    /**
     * 分页查询
     *
     * @param page         分页查询
     * @param workFeedback 查询条件
     * @return
     */
    IPage<WorkFeedback> findPage(Page page, WorkFeedback workFeedback);

    /**
     * 获取工作部署的提报情况
     *
     * @param page           分页参数
     * @param workFeedBackVo 查询参数
     * @return
     */
    IPage getWorkReportingSituation(Page page, WorkFeedBackVo workFeedBackVo);

    /**
     * 判断是否可以反馈
     *
     * @param workFeedback 查询参数
     * @return
     */
    WorkFeedback judgeWorkFeedBack(WorkFeedback workFeedback);

    /**
     * 获取工作反馈情况统计
     *
     * @param workFeedback 查询条件
     * @return
     */
    Map<String, Object> getStatisticsOfWorkFeedback(WorkFeedback workFeedback);

    /**
     * 获取工作反馈的累计情况
     *
     * @param workFeedback 查询条件
     * @return
     */
    Map<String, Object> getAccumulationOfWorkFeedbackSum(WorkFeedback workFeedback);

    /**
     * 获取数据统计工作督办
     *
     * @param page           分页参数
     * @param workFeedBackVo 查询条件
     * @return
     */
    List<Map<String, Object>> getDataStatisticsWorkSupervision(Page page, WorkFeedBackVo workFeedBackVo);

    /**
     * 获取数据统计工作督办
     *
     * @param workFeedBackVo 查询条件
     * @return
     */
    List<Map<String, Object>> getDataStatisticsWorkSupervisionList(WorkFeedBackVo workFeedBackVo);
}
