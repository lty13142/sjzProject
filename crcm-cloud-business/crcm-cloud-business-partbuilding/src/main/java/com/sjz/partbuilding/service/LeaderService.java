package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.Leader;
import com.sjz.partbuilding.model.vo.LeaderVo;
import com.sjz.partbuilding.model.vo.WarnVo;

import java.util.List;
import java.util.Map;


/**
 * 领导班子service
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/24 16:47
 */
public interface LeaderService extends IService<Leader> {

    /**
     * 新增
     *
     * @param leader 领导班子
     * @return
     */
    WarnVo saveLeader(Leader leader);

    /**
     * 修改
     *
     * @param leader 领导班子
     * @return
     */
    WarnVo updateLeader(Leader leader);

    /**
     * 修改
     *
     * @param userId
     * @param orgId
     * @return
     */
    int deleteByUseridAndOrgid(String userId,String orgId);

    /**
     * 根据id删除
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
    Leader findById(String id);

    /**
     * 查询列表
     *
     * @param leader 查询条件
     * @return
     */
    List<LeaderVo> findList(Leader leader);

    /**
     * 分页条件
     *
     * @param page   分页参数
     * @param leader 查询条件
     * @return
     */
    IPage<Leader> findPage(Page page, Leader leader);

    /**
     * 查询当前登录人是否是当前组织支委会成员
     * @param leader
     * @return
     */
    Map getLeaderByOrgId(Leader leader);
}
