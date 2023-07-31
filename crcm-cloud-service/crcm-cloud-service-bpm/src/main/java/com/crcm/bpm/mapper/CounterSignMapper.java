package com.crcm.bpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.bpm.domain.entity.CounterSignDO;
import org.apache.ibatis.annotations.Param;


public interface CounterSignMapper extends BaseMapper<CounterSignDO> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

}
