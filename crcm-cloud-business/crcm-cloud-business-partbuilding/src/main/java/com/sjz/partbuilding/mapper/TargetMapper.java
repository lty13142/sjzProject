package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjz.partbuilding.model.entity.Target;
import org.apache.ibatis.annotations.Param;


public interface TargetMapper extends BaseMapper<Target> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    /**
     * 查询指标是否使用
     * @param id
     * @return
     */
    int selectUsedCount(@Param("id") String id);
}
