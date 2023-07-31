package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.model.entity.SysLog;
import org.apache.ibatis.annotations.Param;


public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

}
