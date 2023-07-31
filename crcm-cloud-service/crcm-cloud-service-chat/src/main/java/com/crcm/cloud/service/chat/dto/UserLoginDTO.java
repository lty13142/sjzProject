package com.crcm.cloud.service.chat.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName UserLoginDTO
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/29
 **/
@Data
public class UserLoginDTO implements Serializable {

//    {
//        "secret": "tuoyun",
//            "platform": 1,
//            "uid": "d5645454517"
//    }

    private static final long serialVersionUID = 7669523798107400292L;
    /**
     * AppServer 请求 IMToken 用到的秘钥，最大长度 32 字符，必须保证 AppServer 和 IMServer 秘钥一致， secret 泄露有风险，最好保存在用户服务器端
     */
    @Length(max = 32)
    @NotBlank(message = "秘钥不能为空！")
    private String secret;
    /**
     * 用户 ID，最大长度 64 字符，必须保证一个 APP 内唯一
     */
    @Length(max = 64)
    @NotBlank(message = "用户ID不能为空！")
    private String uid;
    /**
     * 平台类型 1:ios 2:android 3:windows 4:osx 5:web&mini 7:linux 8:管理员
     */
    @NotNull(message = "平台类型不能为空！")
    private Integer platform;
}
