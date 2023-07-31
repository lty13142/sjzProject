package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjz.partbuilding.model.entity.Leader;
import com.sjz.partbuilding.model.vo.LeaderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 领导班子mapper
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/24 16:47
 */
public interface LeaderMapper extends BaseMapper<Leader> {

    /**
     * 真实删除
     *
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 分页查询
     *
     * @param page   分页参数
     * @param leader 领导班子
     * @return
     */
    List<Leader> findPage(Page page, @Param("leader") Leader leader);

    /**
     * 查询领导班子人员和人员头像
     * @param leader
     * @return
     */
    List<LeaderVo> getList(Leader leader);

    /**
     * 根据userid和orgid删除，更新deleted字段为1
     *
     * @param userId, orgId
     * @return
     */
    int deleteByUseridAndOrgid(@Param("userId") String userId, @Param("orgId") String orgId);

}
