package com.crcm.admin.model.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserLoginDTO
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/10
 **/
@Data
public class UserLoginDTO implements Serializable {
    private String username;
    private String password;
    private String code;
    private String key;
    private String scope;
}
