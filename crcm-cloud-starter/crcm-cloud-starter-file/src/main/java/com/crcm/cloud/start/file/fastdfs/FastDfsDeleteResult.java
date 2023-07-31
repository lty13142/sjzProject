package com.crcm.cloud.start.file.fastdfs;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName FastDfsDeleteResult
 * @Description fastdfs文件删除响应类
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2020/5/8
 **/
@Data
public class FastDfsDeleteResult implements Serializable {
    private Object data;
    private String message;
    private String status;
}
