package com.crcm.admin.api.feign;

import com.crcm.admin.api.dto.req.ReqLogSaveDTO;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.response.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @ClassName RemoteLogService
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/23
 **/
@FeignClient(contextId = "remoteLogService",value = ServiceNameConstants.ADMIN_SERVICE)
public interface RemoteLogService {
    /**
     * 存储系统日志
     * @param dto 日志存储对象
     * @return R
     */
    @PostMapping(value ="/log/save")
    RestResult saveLog(ReqLogSaveDTO dto, @RequestHeader(AuthConstants.FROM) String from);

}
