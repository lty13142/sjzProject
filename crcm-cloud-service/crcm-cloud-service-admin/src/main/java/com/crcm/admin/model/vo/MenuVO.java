package com.crcm.admin.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MenuVO
 * @Description
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/5/22 14:13
 **/
@Data
public class MenuVO implements Serializable {
    private static final long serialVersionUID = -1602755257642836150L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "级别")
    private Integer level;

    @ApiModelProperty(value = "菜单类别（0 菜单，1 按钮）")
    private Integer type;

    @ApiModelProperty(value = "菜单组件")
    private String component;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "在线图标")
    private String iconUrl;

    @ApiModelProperty(value = "菜单代码")
    private String code;

    @ApiModelProperty(value = "父级ID")
    private String pid;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "是否启用 0未启用 1启用 -1禁用")
    private Integer enabled;

    @ApiModelProperty(value = "浏览器访问路径")
    private String path;

    @ApiModelProperty(value = "重定向页面路径")
    private String redirect;

    @ApiModelProperty(value = "按钮类型")
    private String buttonType;

    @ApiModelProperty(value = "是否隐藏 1是 0否")
    private Integer hidden;

    @ApiModelProperty(value = "子集菜单")
    private List<MenuVO> children;

    @ApiModelProperty(value = "角色列表")
    private List<String> roles;

    @ApiModelProperty(value = "按钮ID列表")
    private List<String> buttons;

    @ApiModelProperty(value = "菜单ID")
    private String menuId;

    @ApiModelProperty(value = "角色id列表")
    private List<Long> roleIds;

    @ApiModelProperty(value = "菜单所属 0:PC  1:APP")
    private Integer menuBelong;

    /**
     * 菜单位置（1：页面，2：底部）
     * menu_position: int ==》menuPosition: Integer
     */
    @ApiModelProperty(value = "菜单位置（1：页面，2：底部）")
    private Integer menuPosition;

    @TableField(exist = false)
    private String title;

    private String outUrl;
}
