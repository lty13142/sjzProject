package com.crcm.admin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PasswordVo
 * @Description 密码修改包装类
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author Diaoy
 * @Date 2019/8/30
 **/
@Data
public class PasswordVO implements Serializable {

    private static final long serialVersionUID = 3693841604819434185L;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "新密码")
    private String newPassword;

    @ApiModelProperty(value = "原密码")
    private String password;

}
