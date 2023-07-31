package com.zsgf.platform.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsgf.platform.dto.AreaCodeQueryDTO;
import com.zsgf.platform.model.entity.area.AreaCode;
import com.zsgf.platform.vo.AreaCodeVo;

import java.util.List;

/**
 * 系统行政区划(省平台代码一致)Mapper接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface AreaCodeMapper extends BaseMapper<AreaCode> {


    /**
     * 查询系统行政区划列表
     * @Author GZL
     * @Date 2023/2/9 17:28
     * @Param queryDTO 条件
     * @return 行政区划列表
     **/
    List<AreaCodeVo> findAreaCodeList(AreaCodeQueryDTO queryDTO);
}
