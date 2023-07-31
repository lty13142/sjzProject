package com.crcm.cloud.service.chat.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ClassName UserRegisterDTO
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/29
 **/
@Data
public class UserRegisterDTO extends UserLoginDTO{
    private static final long serialVersionUID = 3947358910614075509L;
//    {
//        "secret": "tuoyun",
//            "platform": 1,
//            "uid": "d5645454517",
//            "name": "张三",
//            "icon": "https://tupian.zhongzaiyuntu.com/test/image/avatar/0aeb40fa95d1bd508eb4dc005017c383/1634808254230-530fff8c48d5b9041238e57b.jpeg",
//            "gender": 1,
//            "mobile": "15213011023",
//            "birth": "1998.12.15",
//            "email": "xxxx@qq.com",
//            "ex": "xxx"
//    }

    /**
     * 用户昵称，最大长度 64 字符，可设置为空字符串
     */
    @Length(max = 64)
    private String name;
    /**
     * 用户头像，最大长度 1024 字节，可设置为空字符串
     */
    @Length(max = 1024)
    private String icon;
    /**
     * 用户性别，0 表示未知，1 表示男，2 表示女，其它会报参数错误
     */
    private Integer gender = 0;
    /**
     * 用户 mobile，最大长度 32 字符，非中国大陆手机号码需要填写国家代码(如美国：+1-xxxxxxxxxx)或地区代码(如香港：+852-xxxxxxxx)，可设置为空字符串
     */
    @Length(max = 32)
    private String mobile;
    /**
     * 用户生日，最大长度 16 字符，可设置为空字符串
     */
    @Length(max = 16)
    private String birth;
    /**
     * 用户 email，最大长度 64 字符，可设置为空字符串
     */
    @Length(max = 64)
    private String email;
    /**
     * 用户名片扩展字段，最大长度 1024 字符，用户可自行扩展，建议封装成 JSON 字符串，也可以设置为空字符串
     */
    @Length(max = 1024)
    private String ex;
}
