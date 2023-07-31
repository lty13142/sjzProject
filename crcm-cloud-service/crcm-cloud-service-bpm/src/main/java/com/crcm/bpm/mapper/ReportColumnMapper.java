package com.crcm.bpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.api.vo.ReportColumnVO;
import com.crcm.bpm.domain.entity.ReportColumnDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportColumnMapper extends BaseMapper<ReportColumnDO> {

    List<ReportColumnVO> searchPage(@Param("collect") List<String> collect, @Param("id") Long id, @Param("filed") String filed);
}
