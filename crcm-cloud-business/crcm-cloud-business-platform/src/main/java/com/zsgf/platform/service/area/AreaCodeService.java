package com.zsgf.platform.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsgf.platform.dto.AreaCodeQueryDTO;
import com.zsgf.platform.model.entity.area.AreaCode;
import com.zsgf.platform.vo.AreaCodeVo;

import java.util.List;

/**
 * 系统行政区划(省平台代码一致)Service接口
 *
 * @author gzl
 * @date 2023-02-09
 */
public interface AreaCodeService extends IService<AreaCode> {
    /**
     * 查询系统行政区划(省平台代码一致)列表
     *
     * @param queryDTO 系统行政区划(省平台代码一致)
     * @return 系统行政区划(省平台代码一致)集合
     */
    List<AreaCodeVo> findAreaCodeList(AreaCodeQueryDTO queryDTO);

    /**
     * 刷新行政区划redis
     *
     * @Author GZL
     * @Date 2023/2/9 16:55
     **/
    void refreshAreaCodeRedis();
}
