package com.sjz.partbuilding.model.vo;

import com.crcm.core.dto.QueryDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AttachmentsVo
 * @Description：
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：diaoy
 * @Date：2019/6/6 14:17
 **/
@Data
public class AttachmentsVo extends QueryDTO implements Serializable {
    private static final long serialVersionUID = 253247653931056681L;

    private String id;
    private String ids;
    private String fileName;
    private String fileFullName;
    private String size;
    private String path;
    private String minioPath;
}
