package com.crcm.auth.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ReqVerificationCodeLoginDTO
 * @Description 用户名/手机号+ 密码 + 验证码登录DTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqVerificationCodeLoginDTO", description = "用户名/手机号+ 密码 + 验证码登录DTO")
public class ReqVerificationCodeLoginDTO extends ReqPasswordLoginDTO{
    @NotBlank(message = "验证码为空")
    @ApiModelProperty(value = "验证码",required = true)
    private String verificationCode;

    @NotBlank(message = "验证码唯一键为空")
    @ApiModelProperty(value = "验证码唯一键",required = true)
    private String key;
}
