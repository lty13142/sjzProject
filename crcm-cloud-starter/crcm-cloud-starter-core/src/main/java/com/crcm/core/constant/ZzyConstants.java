package com.crcm.core.constant;

/**
 * @ClassName FaceConstants
 * @Description 中再云常量
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author pengl
 * @Date 2023/4/9
 **/
public interface ZzyConstants {
    /**
     * 中再云地址
     */
    String ZZY_URL = "https://aiopen.zhongzaiyuntu.com";
    /**
     * 中再云ak
     */
    String ZZY_AK = "39bc38410ed545ef894c1f6c8ed7f581";
    /**
     * 中再云sk
     */
    String ZZY_SK = "91edb2dc0e8c4310b466d900e3ef4231";

    /**
     * 创建人脸数据库uri
     */
    String CREATE_FACE_DATABASE_URI = "/rest/v1/create_face_database";
     /**
     * 人脸注册uri
     */
    String FACE_REGISTER = "/rest/v1/face_register";
    /**
     * 删除人脸数据uri
     */
    String DELETE_FACE_PERSON = "/rest/v1/delete_face_person";
    /**
     * 人脸数据库名称
     */
    String FACE_REGISTER_DATABASE = "FACE_REGISTER_DATABASE";


}
