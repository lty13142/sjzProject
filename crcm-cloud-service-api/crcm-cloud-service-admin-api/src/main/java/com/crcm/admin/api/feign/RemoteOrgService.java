package com.crcm.admin.api.feign;

import com.crcm.admin.api.dto.res.SysOrg;
import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.api.feign.factory.RemoteUserServiceFallbackFactory;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.response.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName RemoteUserService
 * @Description 远程调用用户服务
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/26
 **/
@FeignClient(contextId = "remoteOrgService", value = ServiceNameConstants.ADMIN_SERVICE)
public interface RemoteOrgService {

    /**
     * 通过ID查询组织机构信息
     *
     * @param orgId
     * @return R
     */
    @GetMapping(value = "/org/getById",consumes = MediaType.APPLICATION_JSON_VALUE)
    RestResult<SysOrg> getById(@RequestParam("orgId")Long orgId, @RequestHeader(AuthConstants.FROM) String from);
}
