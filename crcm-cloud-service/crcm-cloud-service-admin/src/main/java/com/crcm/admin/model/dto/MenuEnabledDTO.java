package com.crcm.admin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName MenuEnabledDTO
 * @Description TODO
 * @Author GZL
 * @Date 2023/2/20 10:20
 * @Version 1.0
 **/
@Data
public class MenuEnabledDTO {
    /**
     * ID
     * id：bigint(20) ==》 id：Long
     */
    @ApiModelProperty(value = "ID", example = "0", required = true)
    @NotNull(message = "菜单id不能为空")
    private Long id;
    /**
     * 是否启用 0未启用 1启用 -1禁用
     * enabled：int(1) ==》 enabled：Integer
     */
    @ApiModelProperty(value = "是否启用 1启用 -1禁用", example = "0", required = true)
    @NotNull(message = "启用状态不能为空")
    private Integer enabled;
}
