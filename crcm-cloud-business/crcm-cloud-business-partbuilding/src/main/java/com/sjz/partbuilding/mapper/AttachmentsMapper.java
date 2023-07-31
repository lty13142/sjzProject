package com.sjz.partbuilding.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjz.partbuilding.model.entity.Attachments;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 附件Mapper
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/7/21 15:11
 */
public interface AttachmentsMapper extends BaseMapper<Attachments> {

    /**
     * 真实删除
     *
     * @param id
     * @return
     */
    int realDelete(@Param("id") String id);

    List<Attachments> selectCustomBatchByIds(@Param("ids") String[] split);
}
