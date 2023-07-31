package com.crcm.admin.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName UserEnabledDTO
 * @Description 用户停用/启用DTO
 * @Author GZL
 * @Date 2023/2/13 9:52
 * @Version 1.0
 **/
@Data
public class UserEnabledDTO {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "ID", required = true)
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 是否启用 0未启用，1启用，-1停用
     */
    @ApiModelProperty(value = "是否启用 0未启用，1启用，-1停用", required = true)
    @NotNull(message = "启用状态不能为空")
    private Integer enabled;
}
