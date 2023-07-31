package com.crcm.admin.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ClientsDTO
 * @Description 新增客户端DTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Data
@ApiModel(value = "ClientsDTO", description = "新增客户端DTO")
public class ClientsDTO {
    @ApiModelProperty(value = "客户端ID")
    @NotBlank(message = "客户端ID必填")
    private String clientId;

    @ApiModelProperty(value = "客户端密钥")
    @NotBlank(message = "客户端密钥必填")
    private String clientSecret;

    @ApiModelProperty(value = "支持的资源服务ID（多个逗号分割）")
    @NotBlank(message = "支持的资源服务ID必填")
    private String resourceIds;

    @ApiModelProperty(value = "作用域（多个逗号分割）")
    private String scope;

    @ApiModelProperty(value = " 支持的授权类型以及刷新token（多个逗号分割）")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = " 授权重定向路径（多个逗号分割）")
    @NotBlank(message = "授权重定向路径必填")
    private String registeredRedirectUris;

    /**
     * 用来配置 authorities ，指定用户的权限范围，如果授权的过程需要用户登陆，该字段不生效，implicit和client_credentials需要
     */
    @ApiModelProperty(value = " 权限范围（多个逗号分割）")
    private String authorities;

    @ApiModelProperty(value = " token 过期时间")
    private Integer accessTokenValiditySeconds;

    @ApiModelProperty(value = " refreshToken 过期时间")
    private Integer refreshTokenValiditySeconds;

    @ApiModelProperty(value = "补充信息")
    private String additionalInformation;

    @ApiModelProperty(value = "是否自动批准 true/false")
    private String autoApproveScopes;
}
