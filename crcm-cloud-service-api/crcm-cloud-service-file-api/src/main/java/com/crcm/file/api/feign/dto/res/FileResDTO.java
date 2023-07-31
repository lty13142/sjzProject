package com.crcm.file.api.feign.dto.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName FileDTO
 * @Description 文件上传结果DTO
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/7/1
 **/
@Data
public class FileResDTO implements Serializable {
    private static final long serialVersionUID = -4934764944421774807L;
    private String id;
    private String path;
    private String fileName;
}
