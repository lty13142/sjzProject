package com.crcm.auth.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName ReqPhoneLoginDTO
 * @Description 手机号验证码登录DTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqPhoneLoginDTO", description = "手机号验证码登录DTO")
public class ReqPhoneLoginDTO extends ReqAuthDTO{
    @NotBlank(message = "电话号为空")
    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$")
    @Size(max = 11,message = "电话号长度11位")
    @ApiModelProperty(value = "电话号",required = true)
    private String phoneNumber;

    @NotBlank(message = "验证码为空")
    @ApiModelProperty(value = "验证码",required = true)
    private String phoneCode;
}
