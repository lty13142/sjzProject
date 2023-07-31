package com.zsgf.platform.service.impl.area;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.redis.service.RedisService;
import com.crcm.core.utils.UtilDataFormat;
import com.zsgf.platform.Constant.RedisConstant;
import com.zsgf.platform.dto.AreaCodeQueryDTO;
import com.zsgf.platform.mapper.area.AreaCodeMapper;
import com.zsgf.platform.model.entity.area.AreaCode;
import com.zsgf.platform.service.area.AreaCodeService;
import com.zsgf.platform.vo.AreaCodeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统行政区划(省平台代码一致)Service业务层处理
 *
 * @author gzl
 * @date 2023-02-09
 */
@Service
public class AreaCodeServiceImpl extends ServiceImpl<AreaCodeMapper, AreaCode> implements AreaCodeService {

    @Resource
    private RedisService redisService;

    /**
     * 查询系统行政区划(省平台代码一致)列表
     *
     * @param queryDTO 系统行政区划(省平台代码一致)
     * @return 系统行政区划(省平台代码一致)
     */
    @Override
    public List<AreaCodeVo> findAreaCodeList(AreaCodeQueryDTO queryDTO) {
        return this.baseMapper.findAreaCodeList(queryDTO);
    }

    @Override
    public void refreshAreaCodeRedis() {
        List<AreaCodeVo> areaCodeList = findAreaCodeList(new AreaCodeQueryDTO());
        Map<String, Object> areaCodeVoMap = UtilDataFormat.listToMapObj(areaCodeList, AreaCodeVo::getCode);
        redisService.hmset(RedisConstant.AREA_CODE, areaCodeVoMap, -1L);
    }

}
