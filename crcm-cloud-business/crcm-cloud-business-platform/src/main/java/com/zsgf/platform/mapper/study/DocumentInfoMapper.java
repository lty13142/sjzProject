package com.zsgf.platform.mapper.study;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.study.DocumentInfoDTO;
import com.zsgf.platform.model.entity.study.DocumentInfo;
import com.zsgf.platform.vo.study.DocumentInfoVo;
import org.apache.ibatis.annotations.Param;

/**
 * 文档信息Mapper接口
 *
 * @author gzl
 * @date 2023-03-28
 */
public interface DocumentInfoMapper extends BaseMapper<DocumentInfo> {

    PageT<DocumentInfoVo> findDocumentInfoPage(@Param("page") PageT<DocumentInfoVo> page, @Param("doc") DocumentInfoDTO documentInfo);
}
