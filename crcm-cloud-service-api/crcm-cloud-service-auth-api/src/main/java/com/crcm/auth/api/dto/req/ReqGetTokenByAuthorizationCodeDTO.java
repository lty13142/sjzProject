package com.crcm.auth.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ReqGetTokenByAuthorizationCodeDTO
 * @Description 授权码获取token DTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqGetTokenByAuthorizationCodeDTO", description = "授权码获取token DTO")
public class ReqGetTokenByAuthorizationCodeDTO extends ReqBaseOauthDTO{
    @NotBlank(message = "认证类型为空" + ReqAuthDTO.AUTH_TYPE_INTRODUCE)
    @ApiModelProperty(value = "认证类型" + ReqAuthDTO.AUTH_TYPE_INTRODUCE, required = true)
    private String authType;

    @NotBlank(message = "授权码")
    @ApiModelProperty(value = "授权码", required = true)
    private String code;

    @NotBlank(message = "客户端密钥")
    @ApiModelProperty(value = "客户端密钥", required = true)
    private String clientSecret;

    @NotBlank(message = "重定向URL")
    @ApiModelProperty(value = "重定向URL", required = true)
    private String redirectUri;
}
