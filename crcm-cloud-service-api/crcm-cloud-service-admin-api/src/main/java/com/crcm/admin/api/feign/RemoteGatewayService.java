package com.crcm.admin.api.feign;

import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.response.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName RemoteGatewayService
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2022/6/9
 **/
@FeignClient(contextId = "remoteGatewayService", value = ServiceNameConstants.GATEWAY_SERVICE)
public interface RemoteGatewayService {

    /**
     * 刷新路由
     *
     * @return R
     */
    @GetMapping(value = "/replaceRoute")
    RestResult replaceRoute();
}
