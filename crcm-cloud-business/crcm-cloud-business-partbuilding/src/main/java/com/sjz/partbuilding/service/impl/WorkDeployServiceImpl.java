package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjz.partbuilding.common.utils.SpecialCharacherUtils;
import com.sjz.partbuilding.constants.YTSystemConstant;
import com.sjz.partbuilding.mapper.WorkDeployMapper;
import com.sjz.partbuilding.mapper.WorkFeedbackMapper;
import com.sjz.partbuilding.model.entity.Attachments;
import com.sjz.partbuilding.model.entity.WorkDeploy;
import com.sjz.partbuilding.model.entity.WorkFeedback;
import com.sjz.partbuilding.model.vo.WorkDeployVo;
import com.sjz.partbuilding.service.AttachmentsService;
import com.sjz.partbuilding.service.WorkDeployService;
import com.sjz.partbuilding.service.WorkFeedbackService;
//import com.zzyt.common.utils.UUIDUtil;
//import com.zzyt.core.common.utils.UtilBean;
//import com.zzyt.model.entity.system.org.Org;
//import com.zzyt.service.system.org.OrgService;
//import com.zzyt.service.system.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * 工作部署serviceImpl
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/17 11:29
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class WorkDeployServiceImpl extends ServiceImpl<WorkDeployMapper, WorkDeploy> implements WorkDeployService {

//    @Resource
//    private UserService userService;
//
//    @Resource
//    private OrgService orgService;

    @Resource
    private AttachmentsService attachmentsService;

    @Resource
    private WorkFeedbackMapper workFeedbackMapper;

    /**
     * 新增-》初始化版本号和逻辑删除
     */
    private static final int INITIALIZE_VALUE = 0;


    /**
     * 新增工作部署
     *
     * @param workDeploy 新增字段
     * @return
     */
    @Override
    public int saveWorkDeploy(WorkDeploy workDeploy) {
        //发布时间
        if (YTSystemConstant.NOTIFY_NEWS_TYPE.SYSTEM.equals(workDeploy.getType())) {
            workDeploy.setReleaseTime(new Date());
        }
        //新增
        if(StringUtils.isEmpty(workDeploy.getId())) {
            //生成id
            String workDeployId = UUID.randomUUID().toString().replace("-", "");
            workDeploy.setId(workDeployId);
            if (workDeploy.getIssueRange() != null && !"".equals(workDeploy.getIssueRange())) {
                //获取发布范围组织ids
                String[] issueRangeArrays = workDeploy.getIssueRange().split(",");
                //初始化批量新增集
                List<WorkFeedback> workFeedbackList = new ArrayList<>();
                for (String issueRange : issueRangeArrays) {
                    //初始化反馈对象
                    WorkFeedback workFeedback = new WorkFeedback();
                    //组装数据
                    workFeedback.setOrgId(issueRange);
                    workFeedback.setId(UUID.randomUUID().toString().replace("-", ""));
                    workFeedback.setTaskId(workDeployId);
                    workFeedback.setTaskName(workDeploy.getTaskName());
                    workFeedbackMapper.insert(workFeedback);
                    workFeedbackList.add(workFeedback);
                }
            }
            //保存
            return this.baseMapper.insert(workDeploy);
        }else {
            if(StringUtils.isEmpty(workDeploy.getAttachments())){
                workDeploy.setAttachments(",");
            }
            return this.baseMapper.updateById(workDeploy);
        }
    }

    /**
     * 修改工作部署
     *
     * @param workDeploy 修改字段
     * @return
     */
    @Override
    public int updateWorkDeploy(WorkDeploy workDeploy) {
        //发布时间
        if (YTSystemConstant.NOTIFY_NEWS_TYPE.SYSTEM.equals(workDeploy.getType())) {
            workDeploy.setReleaseTime(new Date());
        }
        return this.baseMapper.updateById(workDeploy);
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
    public WorkDeployVo findById(String id) {
        //根据id查询对象
        WorkDeploy workDeploy = this.baseMapper.selectById(id);
        WorkDeployVo workDeployVo = new WorkDeployVo();
        //复制对象
        BeanUtils.copyProperties(workDeploy, workDeployVo);
        //判断附件ids字符串是否为空
        if (workDeployVo.getAttachments() != null && !"".equals(workDeployVo.getAttachments())) {
            //根据附件ids字符串获得附件列表
            List<Attachments> attList = attachmentsService.getMinIoListByAttIds(workDeployVo.getAttachments());
            workDeployVo.setAttachmentsList(attList);
        }
        //发布范围
        if (workDeployVo.getIssueRange() != null && !"".equals(workDeployVo.getIssueRange())) {
//            List<Org> orgList = orgService.workDeploymentIssueRange(workDeployVo.getIssueRange());
//            workDeployVo.setOrgList(orgList);
        }
        return workDeployVo;
    }

    /**
     * 查询列表
     *
     * @param workDeploy 查询条件
     * @return
     */
    @Override
    public List<WorkDeployVo> findList(WorkDeploy workDeploy) {
        List<WorkDeployVo> workDeployList=this.baseMapper.findList(workDeploy);
        return workDeployList;
    }

    /**
     * 分页查询
     *
     * @param page       分页条件
     * @param workDeploy 查询条件
     * @return
     */
    @Override
    public IPage<WorkDeployVo> findPage(Page page, WorkDeploy workDeploy) {
        if(null!=workDeploy) {
            workDeploy.setTaskName(SpecialCharacherUtils.toMyString(workDeploy.getTaskName()));
        }
        //通过orgId查询各党支部下发的任务部署
        List<WorkDeployVo> workDeployList = this.baseMapper.getWorkDeployPage(page, workDeploy);
        page.setRecords(workDeployList);
        return page;
    }

    /**
     * 获取工作反馈分页查询
     *
     * @param page       分页条件
     * @param workDeploy 查询条件
     * @return
     */
    @Override
    public IPage<WorkDeployVo> getWorkFeedbackPage(Page page, WorkDeploy workDeploy) {
        //根据orgId查询发布范围获取工作反馈分页
        List<WorkDeployVo> workFeedbackList = this.baseMapper.getWorkFeedbackPage(page, workDeploy);
        for (WorkDeployVo deploy : workFeedbackList) {
            //组装每一项的超时情况
            if (deploy.getCloseTime().getTime() < System.currentTimeMillis()) {
                //截止时间 < 当前时间 = 已超时
                deploy.setTimeOutCh(YTSystemConstant.OVER_TIME_TYPE.TIMED_OUT);
            } else {
                //截止时间 > 当前时间 = 未超时
                deploy.setTimeOutCh(YTSystemConstant.OVER_TIME_TYPE.NO_TIME_OUT);
            }
            if (deploy.getCompleteTime() == null) {
                //为空 = 未完成
                deploy.setCompleteCh(YTSystemConstant.COMPLETE_TYPE.IN_COMPLETE);
            } else if (deploy.getCloseTime().getTime() < deploy.getCompleteTime().getTime()) {
                //截止时间 < 完成时间 = 未完成
                //超时完成也算完成
                deploy.setCompleteCh(YTSystemConstant.COMPLETE_TYPE.COMPLETED);
            } else if (deploy.getCloseTime().getTime() > deploy.getCompleteTime().getTime()) {
                //截止时间 > 完成时间 = 已完成
                deploy.setCompleteCh(YTSystemConstant.COMPLETE_TYPE.COMPLETED);
            }
        }
        page.setRecords(workFeedbackList);
        return page;
    }


}
