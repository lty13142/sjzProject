package com.crcm.file.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AttachmentsVo
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/6/6 14:17
 **/
@Data
public class AttachmentsVO implements Serializable {
    private static final long serialVersionUID = 253247653931056681L;
    /**
     * 附件表ID
     */
    private String id;
    /**
     * 逗号分割的ID
     */
    private String ids;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件全名
     */
    private String fileFullName;
    /**
     * 文件大小
     */
    private String size;
    /**
     * 文件路径
     */
    private String path;
}
