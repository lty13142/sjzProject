package com.crcm.admin.api.feign.fallback;

import com.crcm.admin.api.dto.res.AreaDTO;
import com.crcm.admin.api.feign.RemoteAreaService;
import com.crcm.core.response.RestResult;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RemoteAreaServiceFallback implements RemoteAreaService {

    @Setter
    private Throwable cause;

    @Override
    public RestResult<AreaDTO> getInfo(String id, String from) {
        log.error("查询区域信息失败:{}",cause);
        return null;
    }

    @Override
    public RestResult<AreaDTO> getInfoByCode(String code, String from) {
        log.error("查询区域信息失败:{}",cause);
        return null;
    }

    @Override
    public RestResult<List<AreaDTO>> findAreaByType(String type, String from) {
        return null;
    }

    @Override
    public RestResult<List<AreaDTO>> getList(String id, String from) {
        return null;
    }

    @Override
    public RestResult<List<AreaDTO>> getSubsetByPid(String pid, String from) {
        return null;
    }

    @Override
    public RestResult<List<AreaDTO>> getAreaListById(String id, String from) {
        return null;
    }

    @Override
    public RestResult<List<AreaDTO>> getVillageAsc(String regionCode,String from) {
        log.error("查询所有村级区域数据失败:{}",cause);
        return null;
    }

    @Override
    public RestResult<List<AreaDTO>> getRegionAsc(String regionCode,String from) {
        log.error("查询所有管区区域数据失败:{}",cause);
        return null;
    }
}
