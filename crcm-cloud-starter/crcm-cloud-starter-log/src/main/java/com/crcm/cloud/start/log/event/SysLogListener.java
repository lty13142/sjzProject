package com.crcm.cloud.start.log.event;

import com.alibaba.fastjson.JSON;
import com.crcm.admin.api.dto.req.ReqLogSaveDTO;
import com.crcm.admin.api.feign.RemoteLogService;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.response.RestResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @ClassName SysLogListener
 * @Description 系统日志事件监听器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/22
 **/
@Slf4j
@RequiredArgsConstructor
public class SysLogListener {

    private final RemoteLogService remoteLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        ReqLogSaveDTO sysLog = (ReqLogSaveDTO) event.getSource();
        RestResult restResult = remoteLogService.saveLog(sysLog, AuthConstants.FROM_IN);
        System.out.println(JSON.toJSONString(restResult));
    }

}
