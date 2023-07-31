package com.crcm.admin.api.feign.fallback;

import com.crcm.admin.api.dto.res.SysOrg;
import com.crcm.admin.api.feign.RemoteAreaService;
import com.crcm.admin.api.feign.RemoteOrgService;
import com.crcm.core.response.RestResult;
import lombok.Setter;

/**
 * @author yzw
 * @data 2023/4/11
 * @apiNote
 */
public class RemoteOrgServiceFallback implements RemoteOrgService {

    @Setter
    private Throwable cause;
    @Override
    public RestResult<SysOrg> getById(Long orgId, String from) {
        return null;
    }
}
