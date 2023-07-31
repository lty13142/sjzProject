package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjz.partbuilding.model.entity.Announcement;
import org.apache.ibatis.annotations.Param;


public interface AnnouncementMapper extends BaseMapper<Announcement> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

}
