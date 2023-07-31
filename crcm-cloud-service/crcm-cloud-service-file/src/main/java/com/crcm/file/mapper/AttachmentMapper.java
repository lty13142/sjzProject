package com.crcm.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.file.model.entity.Attachment;
import org.apache.ibatis.annotations.Param;


public interface AttachmentMapper extends BaseMapper<Attachment> {

    int realDelete(@Param("id") String id);
}
