package com.crcm.develop.system.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
    @NotBlank(message = "用户名不能为空！")
    private String username;
    private String password;
}
