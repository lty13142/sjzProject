package com.crcm.admin.api.feign;

import com.crcm.admin.api.dto.res.SysPositionDTO;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author yzw
 * @data 2023/4/11
 * @apiNote
 */
@FeignClient(contextId = "remotePositionService", value = ServiceNameConstants.ADMIN_SERVICE)
public interface RemotePositionService {

    @GetMapping(value = "/position/get/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestResult<SysPositionDTO> getInfo(@PathVariable("id") String id, @RequestHeader(AuthConstants.FROM) String from);
}
