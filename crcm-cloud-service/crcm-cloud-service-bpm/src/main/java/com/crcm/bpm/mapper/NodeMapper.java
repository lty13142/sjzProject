package com.crcm.bpm.mapper;

import com.crcm.bpm.domain.entity.NodeDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


public interface NodeMapper extends BaseMapper<NodeDO> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    int updateCustomIfNullById(@Param("Node") NodeDO nodeDO);

}
