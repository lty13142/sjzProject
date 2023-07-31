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
public class FaceRegisterImageResultVO {
    /**
     * 入库图像序号
     */
    private String sequence;
    /**
     * 入库图像特征id
     */
    private String feature_id;
    /**
     * 为true情况下该对象值有效
     */
    private boolean success;
    /**
     * success为false时的错误提示
     */
    private String msg;
}
