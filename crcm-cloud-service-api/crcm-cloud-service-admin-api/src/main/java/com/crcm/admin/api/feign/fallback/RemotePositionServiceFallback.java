package com.crcm.admin.api.feign.fallback;

import com.crcm.admin.api.dto.res.SysPositionDTO;
import com.crcm.admin.api.feign.RemotePositionService;
import com.crcm.core.response.RestResult;
import lombok.Setter;

/**
 * @author yzw
 * @data 2023/4/11
 * @apiNote
 */
public class RemotePositionServiceFallback implements RemotePositionService {

    @Setter
    private Throwable cause;
    @Override
    public RestResult<SysPositionDTO> getInfo(String id, String from) {
        return null;
    }
}
