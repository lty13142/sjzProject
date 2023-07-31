package com.crcm.cloud.start.file.fastdfs;

import lombok.Data;

/**
 * @ClassName FastDfsResult
 * @Description fastdfs文件上传响应类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/4/17
 **/
@Data
public class FastDfsUploadResult {
    private String doMain;
    private String md5;
    private long mtime;
    private String path;
    private int retcode;
    private String retmsg;
    private String scene;
    private String scenes;
    private long size;
    private String src;
    private String url;

}
