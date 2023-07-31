package com.crcm.bpm.mapper;

import com.crcm.bpm.domain.entity.FormDataDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


public interface FormDataMapper extends BaseMapper<FormDataDO> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    int updateCustomIfNullById(@Param("FormData") FormDataDO formDataDO);

}
