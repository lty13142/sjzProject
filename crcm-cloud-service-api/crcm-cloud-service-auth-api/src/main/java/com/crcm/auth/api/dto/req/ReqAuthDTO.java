package com.crcm.auth.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ReqAuthDTO
 * @Description 刷新令牌、密码模式及其扩展模式授权DTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqAuthDTO", description = "登录DTO")
public class ReqAuthDTO extends ReqBaseOauthDTO{
    /**
     * 认证类型介绍 {@value}
     */
    static final String AUTH_TYPE_INTRODUCE =" （" +
            "用户名密码 -> password, " +
            "用户名密码验证码 -> verification, " +
            "手机号 + 验证码登录 -> sms, " +
            "微信授权登录 -> weChat, " +
            "刷新token -> refresh_token, " +
            "授权码模式 -> authorization_code）";

    @NotBlank(message = "客户端密钥为空")
    @ApiModelProperty(value = "客户端密钥", required = true)
    private String clientSecret;

    @NotBlank(message = "认证类型为空" + AUTH_TYPE_INTRODUCE)
    @ApiModelProperty(value = "认证类型" + AUTH_TYPE_INTRODUCE,required = true)
    private String authType;

    @ApiModelProperty(value = "应用授权作用域")
    private String scope;
}
