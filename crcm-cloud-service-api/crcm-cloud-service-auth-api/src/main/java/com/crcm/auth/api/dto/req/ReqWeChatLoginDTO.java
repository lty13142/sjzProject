package com.crcm.auth.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ReqWeChatLoginDTO
 * @Description 微信授权登录DTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqWeChatLoginDTO", description = "微信授权登录DTO")
public class ReqWeChatLoginDTO extends ReqAuthDTO {

    @NotBlank(message = "微信授权登录unionId值为空")
    @ApiModelProperty(value = "微信 unionId",required = true)
    private String unionId;
}
