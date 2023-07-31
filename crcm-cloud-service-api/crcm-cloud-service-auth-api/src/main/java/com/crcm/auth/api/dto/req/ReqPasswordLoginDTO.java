package com.crcm.auth.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ReqPasswordLoginDTO
 * @Description 用户名密码登录DTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqPasswordLoginDTO", description = "登录DTO")
public class ReqPasswordLoginDTO extends ReqAuthDTO{
    @NotBlank(message = "用户名为空")
    @ApiModelProperty(value = "用户名（账号）/手机号",required = true)
    private String username;

    @NotBlank(message = "密码为空")
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
