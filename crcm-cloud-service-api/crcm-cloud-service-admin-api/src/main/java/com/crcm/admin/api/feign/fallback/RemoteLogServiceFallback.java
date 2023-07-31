package com.crcm.admin.api.feign.fallback;

import com.crcm.admin.api.dto.req.ReqLogSaveDTO;
import com.crcm.admin.api.feign.RemoteLogService;
import com.crcm.core.response.RestResult;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RemoteLogService
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/23
 **/
@Slf4j
public class RemoteLogServiceFallback implements RemoteLogService {

    @Setter
    private Throwable cause;

    @Override
    public RestResult saveLog(ReqLogSaveDTO dto, String from) {
        return null;
    }
}
