package com.crcm.cloud.start.file.fastdfs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName FastDfsConfig
 * @Description fastDfs配置
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/4/17
 **/
@Data
@Component
@ConfigurationProperties(prefix = "file.fastdfs")
public class FastDfsConfig {

    /**
     * 根目录
     */
    private String basePath;
    /**
     * 上传服务器地址
     */
    private String host;
    /**
     * 集群名称
     */
    private String group;
    /**
     * 文件上传Url
     */
    private String uploadUrl = "/upload";
    /**
     * 文件删除Url
     */
    private String deleteUrl = "/delete";
    /**
     * 预览服务器地址
     */
    private String previewHost;
}
