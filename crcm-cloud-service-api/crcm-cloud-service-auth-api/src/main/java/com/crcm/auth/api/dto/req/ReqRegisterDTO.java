package com.crcm.auth.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName ReqRegisterDTO
 * @Description 用户注册DTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Data
@ApiModel(value = "ReqRegisterDTO", description = "用户注册DTO")
public class ReqRegisterDTO {
    @NotBlank(message = "电话号为空")
    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$")
    @Size(max = 11,message = "电话号长度11位")
    @ApiModelProperty(value = "电话号",required = true)
    private String phoneNumber;

    @NotBlank(message = "验证码为空")
    @Size(max = 4,message = "验证码长度4")
    @ApiModelProperty(value = "验证码",required = true)
    private String phoneCode;

    @NotBlank(message = "密码为空")
    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "推荐码")
    private String shareCode;

    @NotBlank(message = "客户端ID为空")
    @ApiModelProperty(value = "客户端ID", required = true)
    private String clientId;

    @NotBlank(message = "客户端密钥为空")
    @ApiModelProperty(value = "客户端密钥", required = true)
    private String clientSecret;

    @ApiModelProperty(value = "应用授权作用域")
    private String scope;
}
