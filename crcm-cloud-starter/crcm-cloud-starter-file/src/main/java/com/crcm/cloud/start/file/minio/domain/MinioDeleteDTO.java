package com.crcm.cloud.start.file.minio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName MinioDeleteDTO
 * @Description minio删除数据对象
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author Administrator
 * @Date 2020/2020/9/28/9:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinioDeleteDTO {
    /** 存储桶名称 */
    private String bucKetName;
    /** 需要删除的对象名称 */
    private List<String> objectNames;
}
