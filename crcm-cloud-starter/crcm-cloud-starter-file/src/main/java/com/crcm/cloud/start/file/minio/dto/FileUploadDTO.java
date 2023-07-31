package com.crcm.cloud.start.file.minio.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName FileUploadVo
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/29 16:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadDTO implements Serializable {

    private static final long serialVersionUID = -8872826466927709673L;

    @ApiModelProperty(value = "文件存储路径", required = true)
    private String savePath;

    @ApiModelProperty(value = "文件类型", required = true)
    private Integer type;
}
