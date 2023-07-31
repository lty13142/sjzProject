package com.crcm.bpm.mapper;

import com.crcm.bpm.domain.entity.ApplyDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


public interface ApplyMapper extends BaseMapper<ApplyDO> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

}
