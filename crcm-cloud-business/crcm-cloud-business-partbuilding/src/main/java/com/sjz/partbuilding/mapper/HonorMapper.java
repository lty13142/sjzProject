package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjz.partbuilding.model.entity.Honor;
import com.sjz.partbuilding.model.vo.HonorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface HonorMapper extends BaseMapper<Honor> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    Honor findById(@Param("id")String id);

    List<HonorVo> findGroupHonorPage(Page page, @Param("honor")HonorVo honor);

    List<HonorVo> findPersonHonorPage(Page page, @Param("honor")HonorVo honor);

}
