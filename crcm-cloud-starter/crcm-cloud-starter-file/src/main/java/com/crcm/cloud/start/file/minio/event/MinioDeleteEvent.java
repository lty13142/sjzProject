package com.crcm.cloud.start.file.minio.event;

import com.crcm.cloud.start.file.minio.domain.MinioDeleteDTO;
import org.springframework.context.ApplicationEvent;

/**
 * @ClassName MinioDeleteEvent
 * @Description minio删除对象事件
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author Administrator
 * @Date 2020/2020/9/28/9:04
 **/
public class MinioDeleteEvent extends ApplicationEvent {
    public MinioDeleteEvent(MinioDeleteDTO source) {
        super(source);
    }
}
