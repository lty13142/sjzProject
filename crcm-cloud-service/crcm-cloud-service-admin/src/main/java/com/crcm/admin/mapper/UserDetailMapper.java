package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.model.entity.SysUserDetail;
import org.apache.ibatis.annotations.Param;


public interface UserDetailMapper extends BaseMapper<SysUserDetail> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

}
