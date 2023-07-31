package com.crcm.auth.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName OauthClientDetails
 * @Description Oauth客户端配置
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
@Data
@TableName("sys_oauth_client_details")
@ApiModel("Oauth客户端配置")
public class SysOauthClientDetails implements Serializable {
    /**
     * 客户端ID
     */
    @NotBlank(message = "client_id 不能为空")
    @TableId(value = "client_id", type = IdType.INPUT)
    @ApiModelProperty(value = "客户端id")
    private String clientId;

    /**
     * 客户端密钥
     */
    @NotBlank(message = "client_secret 不能为空")
    @ApiModelProperty(value = "客户端密钥")
    private String clientSecret;

    /**
     * 资源ID
     */
    @ApiModelProperty(value = "资源id列表")
    private String resourceIds;

    /**
     * 作用域
     */
    @NotBlank(message = "scope 不能为空")
    @ApiModelProperty(value = "作用域")
    private String scope;

    /**
     * 授权方式（A,B,C）
     */
    @ApiModelProperty(value = "授权方式")
    private String authorizedGrantTypes;

    /**
     * 回调地址
     */
    @ApiModelProperty(value = "回调地址")
    private String webServerRedirectUri;

    /**
     * 权限
     */
    @ApiModelProperty(value = "权限列表")
    private String authorities;

    /**
     * 请求令牌有效时间
     */
    @ApiModelProperty(value = "请求令牌有效时间")
    private Integer accessTokenValidity;

    /**
     * 刷新令牌有效时间
     */
    @ApiModelProperty(value = "刷新令牌有效时间")
    private Integer refreshTokenValidity;

    /**
     * 扩展信息
     */
    @ApiModelProperty(value = "扩展信息")
    private String additionalInformation;

    /**
     * 是否自动放行
     */
    @ApiModelProperty(value = "是否自动放行")
    private String autoApprove;
    /**
     * 客户端名称
     */
    @ApiModelProperty(value = "客户端名称")
    private String clientName;
    /**
     * 客户端类型 0 内部客户端 1外部客户端
     */
    @ApiModelProperty(value = "客户端类型 0 内部客户端 1外部客户端")
    private Integer clientType;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
