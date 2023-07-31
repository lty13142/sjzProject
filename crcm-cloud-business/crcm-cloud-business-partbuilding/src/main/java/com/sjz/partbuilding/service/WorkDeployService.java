package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.WorkDeploy;
import com.sjz.partbuilding.model.vo.WorkDeployVo;

import java.util.List;


/**
 * 工作部署service
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/17 11:29
 */
public interface WorkDeployService extends IService<WorkDeploy> {

    /**
     * 新增工作部署
     *
     * @param workDeploy 新增字段
     * @return
     */
    int saveWorkDeploy(WorkDeploy workDeploy);

    /**
     * 修改工作部署
     *
     * @param workDeploy 修改字段
     * @return
     */
    int updateWorkDeploy(WorkDeploy workDeploy);

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
    WorkDeployVo findById(String id);

    /**
     * 查询列表
     *
     * @param workDeploy 查询条件
     * @return
     */
    List<WorkDeployVo> findList(WorkDeploy workDeploy);

    /**
     * 分页查询
     *
     * @param page       分页条件
     * @param workDeploy 查询条件
     * @return
     */
    IPage<WorkDeployVo> findPage(Page page, WorkDeploy workDeploy);

    /**
     * 获取工作反馈分页查询
     *
     * @param page       分页条件
     * @param workDeploy 查询条件
     * @return
     */
    IPage<WorkDeployVo> getWorkFeedbackPage(Page page, WorkDeploy workDeploy);
}
