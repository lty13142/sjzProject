package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.model.entity.SysPosition;
import com.crcm.admin.model.vo.SysPositionVo;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import org.apache.ibatis.annotations.Param;

/**
 * 岗位Mapper接口
 * 
 * @author cb
 * @date 2023-04-06
 */
public interface SysPositionMapper extends BaseMapper<SysPosition> {

    PageT<SysPositionVo> findPositionPageVo(@Param("page") PageT<SysPositionVo> page, @Param("vo") SysPositionVo position);


}
