package com.sjz.governance.model.dto.zzy;

import lombok.Data;

import java.util.List;


/**
 * @ClassName FaceRegisterDTO
 * @Description 中再云人脸注册上传dto
 * @Author pengl
 * @Date 2023/4/9 17:06
 * @Version 1.0
 **/
@Data
public class FaceRegisterDTO {
    /**
     * base64编码的图像,最多一次性可上传30张图像
     */
    private List<String> images;
    /**
     * 图像格式
     */
    private String img_type;
    /**
     * 人脸库名称,同一拥有者下人脸库名称不能重复
     */
    private String database_name;
    /**
     * 人员分组名称
     */
    private String group_name;
    /**
     * 人员的标识号
     */
    private String user_code;
    /**
     * 人员信息额外备注
     */
    private String remarks;
}
