package com.crcm.cloud.start.file.minio.listener;

import com.crcm.cloud.start.file.minio.MinioConfig;
import com.crcm.cloud.start.file.minio.MinioUtil;
import com.crcm.cloud.start.file.minio.domain.MinioDeleteDTO;
import com.crcm.cloud.start.file.minio.event.MinioDeleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName MinioDeleteListener
 * @Description minio文件删除监听器
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author Administrator
 * @Date 2020/2020/9/28/9:29
 **/
@Slf4j
@Component
public class MinioDeleteListener {
    @Autowired
    private MinioConfig minioConfig;
    /**
     * 异步方式删除文件
     * @param event
     */
    @Async
    @EventListener(MinioDeleteEvent.class)
    public void removeObjects(MinioDeleteEvent event) {
        MinioDeleteDTO deleteDTO = (MinioDeleteDTO) event.getSource();
        MinioUtil minioUtil = MinioUtil.init(minioConfig);
        minioUtil.removeObjects(deleteDTO.getBucKetName(),deleteDTO.getObjectNames());
    }
}
