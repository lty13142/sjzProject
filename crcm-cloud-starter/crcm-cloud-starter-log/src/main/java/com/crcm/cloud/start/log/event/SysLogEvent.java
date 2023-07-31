package com.crcm.cloud.start.log.event;

import com.crcm.admin.api.dto.req.ReqLogSaveDTO;
import org.springframework.context.ApplicationEvent;

/**
 * @ClassName SysLogEvent
 * @Description 系统日志事件
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/22
 **/
public class SysLogEvent extends ApplicationEvent {

    private static final long serialVersionUID = 7737755256723709667L;

    public SysLogEvent(ReqLogSaveDTO source) {
        super(source);
    }
}
