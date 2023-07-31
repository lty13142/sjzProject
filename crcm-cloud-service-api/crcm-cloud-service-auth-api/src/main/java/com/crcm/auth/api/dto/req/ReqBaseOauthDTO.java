package com.crcm.auth.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ReqBaseOauthDTO
 * @Description 获取TOKEN基础DTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Data
@ApiModel(value = "ReqBaseOauthDTO", description = "获取TOKEN基础DTO")
public class ReqBaseOauthDTO {
    @NotBlank(message = "客户端ID为空")
    @ApiModelProperty(value = "客户端ID", required = true)
    private String clientId;
}
