package com.sjz.governance.model.dto.zzy;

import lombok.Data;

/**
 * @ClassName FaceDeleteDTO
 * @Description 中再云人脸删除dto
 * @Author pengl
 * @Date 2023/4/9 17:06
 * @Version 1.0
 **/
@Data
public class FaceDeleteDTO {
    /**
     * 人脸库id
     */
    private String database_id;
    /**
     * 人员id
     */
    private int person_id;
}
