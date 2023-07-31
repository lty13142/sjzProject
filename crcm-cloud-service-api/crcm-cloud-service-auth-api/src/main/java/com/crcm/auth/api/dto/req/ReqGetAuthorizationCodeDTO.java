package com.crcm.auth.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName ReqGetAuthorizationCodeDTO
 * @Description 授权码获取token DTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqAuthorizationCodeDTO", description = "授权码获取token DTO")
public class ReqGetAuthorizationCodeDTO extends ReqBaseOauthDTO{
    @Pattern(regexp = "^[A-Za-z0-9]+$" ,message = "state参数需要匹配正则表达式\"^[A-Za-z0-9]+$\"")
    @Size(max = 128,message = "state参数最大长度128")
    @ApiModelProperty(value = "重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，" +
            "最多128字节，该值会被我们原样返回，我们可以将其进行比对，防止别人的攻击。")
    private String state;

    @ApiModelProperty(value = "应用授权作用域")
    private String scope;

    @NotBlank(message = "重定向URL")
    @ApiModelProperty(value = "重定向URL", required = true)
    private String redirectUri;
}
