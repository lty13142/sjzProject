package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjz.partbuilding.model.entity.WorkDeploy;
import com.sjz.partbuilding.model.vo.WorkDeployVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 工作部署mapper
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/17 11:29
 */
public interface WorkDeployMapper extends BaseMapper<WorkDeploy> {

    /**
     * 真实删除
     *
     * @param id 删除条件
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 分页查询
     *
     * @param page       分页条件
     * @param workDeploy 查询条件
     * @return
     */
    List<WorkDeployVo> getWorkDeployPage(Page page, @Param("workDeploy") WorkDeploy workDeploy);

    /**
     * 根据orgId查询发布范围获取工作反馈分页
     *
     * @param page         分页参数
     * @param workDeploy 查询参数
     * @return
     */
    List<WorkDeployVo> getWorkFeedbackPage(Page page, @Param("workDeploy") WorkDeploy workDeploy);

    /**
     * 查询全部列表
     * @param workDeploy
     * @return
     */
    List<WorkDeployVo> findList(@Param("workDeploy") WorkDeploy workDeploy);
}
