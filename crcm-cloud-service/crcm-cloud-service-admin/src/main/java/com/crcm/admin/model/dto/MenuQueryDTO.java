package com.crcm.admin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MenuQueryDTO
 * @Description 菜单查询DTO
 * @Author GZL
 * @Date 2023/2/16 11:39
 * @Version 1.0
 **/
@Data
public class MenuQueryDTO {

    @ApiModelProperty(value = "菜单ID")
    private String menuId;
    /**
     * 名称
     * name：varchar(64) ==》 name：String
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;
    /**
     * 类别（0 菜单，1 按钮）
     * type：varchar(16) ==》 type：String
     */
    @ApiModelProperty(value = "类别（0 菜单，1 按钮）", example = "0")
    private Integer type;
    /**
     * 是否启用 0未启用 1启用 -1禁用
     * enabled：int(1) ==》 enabled：Integer
     */
    @ApiModelProperty(value = "是否启用 1启用 -1禁用", example = "0")
    private Integer enabled;
    /**
     * 代码
     * code：varchar(16) ==》 code：String
     */
    @ApiModelProperty(value = "菜单代码")
    private String code;
    /**
     * 菜单所属 0:PC  1:APP
     * menu_belong: int ==》menuBelong: Integer
     */
    @ApiModelProperty(value = "菜单所属 0:PC  1:APP")
    private Integer menuBelong;

    /**
     * 菜单位置（1：页面，2：底部）
     * menu_position: int ==》menuPosition: Integer
     */
    @ApiModelProperty(value = "菜单位置（1：页面，2：底部）")
    private Integer menuPosition;
}
