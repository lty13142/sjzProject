package com.crcm.auth.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ReqRefreshTokenDTO
 * @Description 刷新tokenDTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqRefreshTokenDTO", description = "刷新tokenDTO")
public class ReqRefreshTokenDTO extends ReqAuthDTO{
    @NotBlank(message = "刷新token为空")
    @ApiModelProperty(value = "刷新token",required = true)
    private String refreshToken;

    /**
     * 刷新token类别 {@link com.crcm.admin.constants.RefreshTokenType}
     */
    @ApiModelProperty(value = "刷新token类别  1 只返回刷新的token,  2 返回刷新的token及refreshToken")
    private Integer type;
}
