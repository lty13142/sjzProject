package com.sjz.governance.model.vo.zzy;

import lombok.Data;

import java.util.List;


/**
 * @ClassName FaceRegisterVO
 * @Description 中再云人脸注册上传响应Vo
 * @Author pengl
 * @Date 2023/4/10 14:06
 * @Version 1.0
 **/
@Data
public class FaceRegisterVO {
    /**
     * 人脸库id
     */
    private String face_database_id;
    /**
     * 人脸库名称
     */
    private String face_database_name;
    /**
     * 拥有者标识
     */
    private String owner_id;
    /**
     * 人脸库版本
     */
    private String face_database_version;
    /**
     * 人脸组id
     */
    private String face_group_id;
    /**
     * 人脸组名称
     */
    private String face_group_name;
    /**
     * 人员id
     */
    private Integer face_person_id;
    /**
     * 入库图像详情
     */
    private List<FaceRegisterImageResultVO> img_result;

}
