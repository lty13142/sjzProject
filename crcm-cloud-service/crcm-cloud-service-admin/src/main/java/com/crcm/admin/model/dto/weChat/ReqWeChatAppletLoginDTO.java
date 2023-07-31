package com.crcm.admin.model.dto.weChat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 微信小程序登录DTO
 *
 * @author rmc
 * @version 1.0
 * @date 2023/1/9 15:46
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ReqWeChatAppletLoginDTO", description = "微信小程序登录DTO")
public class ReqWeChatAppletLoginDTO{

    @NotBlank(message = "微信小程序登录授权code不能为空")
    @ApiModelProperty(value = "用户微信登录授权,签发的code", required = true)
    private String loginCode;

    @NotBlank(message = "微信小程序获取手机号授权code不能为空")
    @ApiModelProperty(value = "用户微信授权手机号,签发的code", required = true)
    private String code;

    @NotBlank(message = "微信用户敏感字段不能为空")
    @ApiModelProperty(value = "微信用户敏感字段（解密用）", required = true)
    private String encryptedData;

    @NotBlank(message = "微信解密向量不能为空")
    @ApiModelProperty(value = "微信解密向量（解密用）", required = true)
    private String iv;

}
