package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.core.exception.SystemException;
import com.sjz.partbuilding.constants.YTSystemConstant;
import com.sjz.partbuilding.enums.YTSystemStatusEnum;
import com.sjz.partbuilding.mapper.WorkFeedbackMapper;
import com.sjz.partbuilding.model.entity.Attachments;
import com.sjz.partbuilding.model.entity.WorkFeedback;
import com.sjz.partbuilding.model.vo.WorkFeedBackVo;
import com.sjz.partbuilding.service.AttachmentsService;
import com.sjz.partbuilding.service.WorkDeployService;
import com.sjz.partbuilding.service.WorkFeedbackService;
//import com.zzyt.service.system.org.OrgService;
//import com.zzyt.service.system.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


/**
 * 工作反馈serviceImpl
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/17 11:29
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class WorkFeedbackServiceImpl extends ServiceImpl<WorkFeedbackMapper, WorkFeedback> implements WorkFeedbackService {

//    @Resource
//    private UserService userService;

    @Resource
    private WorkDeployService workDeployService;

    @Resource
    private AttachmentsService attachmentsService;

//    @Resource
//    private OrgService orgService;

    /**
     * 新增工作反馈
     *
     * @param workFeedback 新增字段
     * @return
     */
    @Override
    public int saveWorkFeedback(WorkFeedback workFeedback) {
        return this.baseMapper.insert(workFeedback);
    }

    /**
     * 修改工作反馈
     *
     * @param workFeedback 修改字段
     * @return
     */
    @Override
    public int updateWorkFeedback(WorkFeedback workFeedback) {
        //完成时间
        workFeedback.setCompleteTime(new Date());
        return this.baseMapper.updateById(workFeedback);
    }

    /**
     * 根据id删除(逻辑删除)
     *
     * @param id 删除条件
     * @return
     */
    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * 真实删除
     *
     * @param id 删除条件
     * @return
     */
    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    /**
     * 根据id查询
     *
     * @param id 查询条件
     * @return
     */
    @Override
    public WorkFeedback findById(String id) {
        //获取到工作反馈对象
        WorkFeedback workFeedback = this.baseMapper.selectById(id);
        //不为空则组织参数
        if (workFeedback != null && !"".equals(workFeedback)) {
            List<Attachments> attList = attachmentsService.getMinIoListByAttIds(workFeedback.getAttachments());
            workFeedback.setAttachmentsList(attList);
        }
        return workFeedback;
    }

    /**
     * 查询列表
     *
     * @param workFeedback 查询条件
     * @return
     */
    @Override
    public List<WorkFeedback> findList(WorkFeedback workFeedback) {
        QueryWrapper<WorkFeedback> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param page         分页查询
     * @param workFeedback 查询条件
     * @return
     */
    @Override
    public IPage<WorkFeedback> findPage(Page page, WorkFeedback workFeedback) {
        QueryWrapper<WorkFeedback> queryWrapper = new QueryWrapper<>();
        IPage<WorkFeedback> pageWorkFeedback = this.baseMapper.selectPage(page, queryWrapper);
        return pageWorkFeedback;
    }

    /**
     * 获取工作部署的提报情况
     *
     * @param page           分页参数
     * @param workFeedBackVo 查询参数
     * @return
     */
    @Override
    public IPage getWorkReportingSituation(Page page, WorkFeedBackVo workFeedBackVo) {
        //根据taskId获取提报情况
        List<WorkFeedBackVo> workFeedBackVoList = this.baseMapper.getWorkReportingSituation(page, workFeedBackVo);
        if (workFeedBackVoList.size() > 0) {
            for (WorkFeedBackVo feedback : workFeedBackVoList) {
                //组装附件
                if (feedback.getAttachments() != null && !"".equals(feedback.getAttachments())) {
                    List<Attachments> attList = attachmentsService.getMinIoListByAttIds(feedback.getAttachments());
                    feedback.setAttachmentsList(attList);
                }
                if(null!=feedback.getCompleteTime()) {
                    //组装每一项提报情况的超时和完成情况
                    if (feedback.getCloseTime().getTime() <feedback.getCompleteTime().getTime()) {
                        //截止时间 < 当前时间 = 已超时
                        feedback.setTimeOutCh(YTSystemConstant.OVER_TIME_TYPE.TIMED_OUT);
                    } else {
                        //截止时间 > 当前时间 = 未超时
                        feedback.setTimeOutCh(YTSystemConstant.OVER_TIME_TYPE.NO_TIME_OUT);
                    }
                }else {
                    //组装每一项提报情况的超时和完成情况
                    if (feedback.getCloseTime().getTime() <System.currentTimeMillis()) {
                        //截止时间 < 当前时间 = 已超时
                        feedback.setTimeOutCh(YTSystemConstant.OVER_TIME_TYPE.TIMED_OUT);
                    } else {
                        //截止时间 > 当前时间 = 未超时
                        feedback.setTimeOutCh(YTSystemConstant.OVER_TIME_TYPE.NO_TIME_OUT);
                    }
                }
                //组装每一项的完成情况
                assembleWorkFeedBackVo(feedback);
            }
        }
        page.setRecords(workFeedBackVoList);
        return page;
    }

    /**
     * 判断是否可以反馈
     *
     * @param workFeedback 查询参数
     * @return
     */
    @Override
    public WorkFeedback judgeWorkFeedBack(WorkFeedback workFeedback) {
        LambdaQueryWrapper<WorkFeedback> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(WorkFeedback::getTaskId, workFeedback.getTaskId());
//        User user = userService.findById(UserContext.current().getUserId());
//        lambdaQueryWrapper.eq(WorkFeedback::getOrgId, user.getOrgId());
        WorkFeedback feedback = this.baseMapper.selectOne(lambdaQueryWrapper);
        //为空则没有权限反馈
        if (feedback == null || "".equals(feedback)) {
            throw new SystemException(YTSystemStatusEnum.WORK_FEEDBACK_NO.code, YTSystemStatusEnum.WORK_FEEDBACK_NO.desc);
        }
        //已完成的话返回失败
        if (feedback.getCompleteTime() != null && !"".equals(feedback.getCompleteTime())) {
            throw new SystemException(YTSystemStatusEnum.WORK_FEEDBACK__GOT.code, YTSystemStatusEnum.WORK_FEEDBACK__GOT.desc);
        }
        return feedback;
    }

    /**
     * 获取工作反馈情况统计
     *
     * @param workFeedback 查询条件
     * @return
     */
    @Override
    public Map<String, Object> getStatisticsOfWorkFeedback(WorkFeedback workFeedback) {
        //初始化返回集
        Map<String, Object> resultMap = new HashMap<>(16);
        //获取最新的12月数组
//        String[] monthArrays = UtilLawDate.getLatest12Month();
        //根据org_id和12月前的月份查询出所有工作反馈
//        List<WorkFeedBackVo> workFeedBackVoList = this.baseMapper.getStatisticsOfWorkFeedback(workFeedback.getOrgId(), monthArrays[0]);
        //初始化已按时完成和未按时完成
//        int[] toFinishOnTimeArrays = new int[monthArrays.length];
//        int[] notFinishOnTimeArrays = new int[monthArrays.length];
//        //组装柱状图数组
//        for (int i = 0; i < monthArrays.length; i++) {
//            //初始化每月数据
//            int toFinishIndex = 0;
//            int notFinishIndex = 0;
//            for (WorkFeedBackVo vo : workFeedBackVoList) {
//                //获取完成时间的时间字符串
//                String completeDateStr = UtilLawDate.simpleDateFormat.format(vo.getCreateTime());
//                if (!completeDateStr.startsWith(monthArrays[i])) {
//                    continue;
//                }
//                if (vo.getCompleteTime() == null) {
//                    //为空 = 已超时
//                    notFinishIndex++;
//                } else if (vo.getCloseTime().getTime() < vo.getCompleteTime().getTime()) {
//                    //截止时间 < 完成时间 = 已超时
//                    notFinishIndex++;
//                } else if (vo.getCloseTime().getTime() > vo.getCompleteTime().getTime()) {
//                    //截止时间 > 完成时间 = 未超时
//                    toFinishIndex++;
//                }
//            }
//            toFinishOnTimeArrays[i] = toFinishIndex;
//            notFinishOnTimeArrays[i] = notFinishIndex;
//        }
//        //组装结果返回集->一个月份集 两个柱状图集
//        resultMap.put("monthArrays", monthArrays);
//        resultMap.put("toFinishOnTimeArrays", toFinishOnTimeArrays);
//        resultMap.put("notFinishOnTimeArrays", notFinishOnTimeArrays);
        return resultMap;
    }

    /**
     * 获取工作反馈的累计情况
     *
     * @param workFeedback 查询条件
     * @return
     */
    @Override
    public Map<String, Object> getAccumulationOfWorkFeedbackSum(WorkFeedback workFeedback) {
        //初始化结果返回集
        Map<String, Object> resultMap = new HashMap<>(16);
        //已按时完成
        int toFinishOnTimeSum = 0;
        //未按时完成
        int notFinishOnTimeSum = 0;
        //根据org_id查询工作反馈
        List<WorkFeedBackVo> workFeedBackVoList = this.baseMapper.getStatisticsOfWorkFeedback(workFeedback.getOrgId(), null);
        for (WorkFeedBackVo vo : workFeedBackVoList) {
            if (vo.getCompleteTime() == null) {
                //为空 = 已超时
                notFinishOnTimeSum++;
            } else if (vo.getCloseTime().getTime() < vo.getCompleteTime().getTime()) {
                //截止时间 < 完成时间 = 已超时
                notFinishOnTimeSum++;
            } else if (vo.getCloseTime().getTime() > vo.getCompleteTime().getTime()) {
                //截止时间 > 完成时间 = 未超时
                toFinishOnTimeSum++;
            }

        }
        //组装百分比
        BigDecimal toFinishOnTimePercentage = new BigDecimal((double) toFinishOnTimeSum / (toFinishOnTimeSum + notFinishOnTimeSum) * 100);
        double doubleValue = toFinishOnTimePercentage.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        //组装结果返回集
        resultMap.put("toFinishOnTimeSum", toFinishOnTimeSum);
        resultMap.put("toFinishOnTimePercentage", doubleValue);
        resultMap.put("notFinishOnTimeSum", notFinishOnTimeSum);
        return resultMap;
    }

    /**
     * 获取数据统计工作督办
     *
     * @param page           分页参数
     * @param workFeedBackVo 查询条件
     * @return
     */
    @Override
    public List<Map<String, Object>> getDataStatisticsWorkSupervision(Page page, WorkFeedBackVo workFeedBackVo) {
        //获取组织集合
        //获取数据统计工作督办
        List<WorkFeedBackVo> workFeedBackVoList = this.baseMapper.getDataStatisticsWorkSupervision(page, workFeedBackVo);
        //初始化结果集
        List<Map<String, Object>> resultList = new ArrayList<>();
        int toComplete = 0;
        int notComplete = 0;
        int countComplete = 0;
        if (workFeedBackVoList.size() > 0) {
            Map<String, Object> map = new HashMap<>(16);
            for (WorkFeedBackVo vo : workFeedBackVoList) {

                if (vo.getCompleteTime() != null) {
                    countComplete++;
                    //组装每一项完成情况
                    if (vo.getCloseTime().getTime() < vo.getCompleteTime().getTime()) {
                        //逾期完成
                        notComplete++;
                    } else if (vo.getCloseTime().getTime() > vo.getCompleteTime().getTime()) {
                        //按期完成
                        toComplete++;
                    }
                }
            }


            //组装数据
            map.put("orgName", workFeedBackVoList.get(0).getOrgName());
            map.put("toComplete", toComplete);
            map.put("notComplete", notComplete);
            map.put("countComplete", countComplete);
            resultList.add(map);
        }
        return resultList;
    }

    /**
     * 获取数据统计工作督办
     *
     * @param workFeedBackVo 查询条件
     * @return
     */
    @Override
    public List<Map<String, Object>> getDataStatisticsWorkSupervisionList(WorkFeedBackVo workFeedBackVo) {
        //获取组织集合
        //获取数据统计工作督办
        List<WorkFeedBackVo> workFeedBackVoList = this.baseMapper.getDataStatisticsWorkSupervisionList(workFeedBackVo);
        //初始化结果集
        List<Map<String, Object>> resultList = new ArrayList<>();
        int toComplete = 0;
        int notComplete = 0;
        int countComplete = 0;
        if (workFeedBackVoList.size() > 0) {
            Map<String, Object> map = new HashMap<>(16);
            for (WorkFeedBackVo vo : workFeedBackVoList) {
                countComplete++;
                //组装每一项完成情况
                if (vo.getCompleteTime() == null) {
                    //完成时间 = null =  未完成
                    notComplete++;
                } else if (vo.getCloseTime().getTime() < vo.getCompleteTime().getTime()) {
                    //完成时间 = null =  未完成
                    notComplete++;
                } else if (vo.getCloseTime().getTime() > vo.getCompleteTime().getTime()) {
                    //完成时间 != null = 已完成
                    toComplete++;
                }
            }
            //组装数据
            map.put("orgName", workFeedBackVoList.get(0).getOrgName());
            map.put("toComplete", toComplete);
            map.put("notComplete", notComplete);
            map.put("countComplete", countComplete);
            resultList.add(map);
        }
        return resultList;
    }

    /**
     * 组装每一项的完成情况
     *
     * @param feedback
     */
    public void assembleWorkFeedBackVo(WorkFeedBackVo feedback) {
        if (feedback.getCompleteTime() == null) {
            //完成时间 = null =  未完成
            feedback.setCompleteCh(YTSystemConstant.COMPLETE_TYPE.IN_COMPLETE);
        } else if (feedback.getCloseTime().getTime() < feedback.getCompleteTime().getTime()) {
            //完成时间 = null =  未完成
            //超时完成也属于完成
            feedback.setCompleteCh(YTSystemConstant.COMPLETE_TYPE.COMPLETED);
        } else if (feedback.getCloseTime().getTime() > feedback.getCompleteTime().getTime()) {
            //完成时间 != null = 已完成
            feedback.setCompleteCh(YTSystemConstant.COMPLETE_TYPE.COMPLETED);
        }
    }
}
