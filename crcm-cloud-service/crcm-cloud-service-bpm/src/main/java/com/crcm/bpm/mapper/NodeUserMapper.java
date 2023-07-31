package com.crcm.bpm.mapper;

import com.crcm.bpm.domain.entity.NodeUserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


public interface NodeUserMapper extends BaseMapper<NodeUserDO> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    int updateCustomIfNullById(@Param("NodeUser") NodeUserDO nodeUserDO);

}
