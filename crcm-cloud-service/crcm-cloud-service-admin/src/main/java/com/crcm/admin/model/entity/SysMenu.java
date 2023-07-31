package com.crcm.admin.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.crcm.admin.utils.UtilDic;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.core.constant.SystemConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @ClassName SysMenu
 * @Description 系统菜单
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/1
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("系统菜单")
@TableName("sys_menu")
public class SysMenu extends BaseEntity implements Serializable {
    /**
     * ID
     * id：bigint(20) ==》 id：Long
     */
    @ApiModelProperty(value = "ID", example = "0")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     * name：varchar(64) ==》 name：String
     */
    @ApiModelProperty(value = "菜单名称", required = true)
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 64, message = "菜单名称最多可输入64个字符")
    private String name;
    /**
     * 类别（0 菜单，1 按钮）
     * type：varchar(16) ==》 type：String
     */
    @ApiModelProperty(value = "类别（0 菜单，1 按钮）", example = "0", hidden = true)
    private Integer type;
    /**
     * 图标
     * icon：varchar(255) ==》 icon：String
     */
    @ApiModelProperty(value = "图标", hidden = true)
    @Size(max = 255, message = "图标最多可输入255个字符")
    private String icon;
    /**
     * 图标
     * icon_url：varchar(128) ==》 iconUrl：String
     */
    @ApiModelProperty(value = "在线图标")
    @Size(max = 128, message = "在线图标最多可输入128个字符")
    private String iconUrl;
    /**
     * 代码
     * code：varchar(16) ==》 code：String
     */
    @ApiModelProperty(value = "菜单代码", required = true)
    @NotBlank(message = "菜单代码不能为空")
    @Size(max = 16, message = "菜单代码最多可输入16个字符")
    private String code;
    /**
     * 组件
     * component：varchar(255) ==》 component：String
     */
    @ApiModelProperty(value = "菜单组件")
    @Size(max = 255, message = "菜单组件最多可输入255个字符")
    private String component;
    /**
     * 父级ID
     * pid：bigint(20) ==》 pid：Long
     */
    @ApiModelProperty(value = "父级ID", example = "0")
    private Long pid;
    /**
     * 描述
     * description：varchar(255) ==》 description：String
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 255, message = "描述最多可输入255个字符")
    private String description;
    /**
     * 是否隐藏 1是 0否
     * hidden：int(1) ==》 hidden：Integer
     */
    @ApiModelProperty(value = "是否隐藏 1是 0否", example = "0", hidden = true)
    private Integer hidden;
    /**
     * 是否启用 0未启用 1启用 -1禁用
     * enabled：int(1) ==》 enabled：Integer
     */
    @ApiModelProperty(value = "是否启用 1启用 -1禁用", example = "0")
    private Integer enabled;
    /**
     * 浏览器访问路径
     * path：varchar(1000) ==》 path：String
     */
    @ApiModelProperty(value = "浏览器访问路径", required = true)
    @Size(max = 1000, message = "浏览器访问路径最多可输入1000个字符")
    private String path;
    /**
     * 重定向页面路径
     * redirect：varchar(1000) ==》 redirect：String
     */
    @ApiModelProperty(value = "重定向页面路径")
    @Size(max = 1000, message = "重定向页面路径最多可输入1000个字符")
    private String redirect;

    /**
     * 按钮类型
     * button_type: varchar(32) ==》buttonType: String
     */
    @ApiModelProperty(value = "按钮类型", hidden = true)
    @Size(max = 32, message = "按钮类型最多可输入32个字符")
    private String buttonType;
    /**
     * 菜单所属 0:PC  1:APP
     * menu_belong: int ==》menuBelong: Integer
     */
    @ApiModelProperty(value = "菜单所属 0:PC  1:APP", required = true)
    private Integer menuBelong;
    /***
     * 逻辑删除 0 未删除 1删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @ApiModelProperty(value = "是否跳转外部网址")
    private String outUrl;

    /**
     * 菜单位置（1：页面，2：底部）
     * menu_position: int ==》menuPosition: Integer
     */
    @ApiModelProperty(value = "菜单位置（1：页面，2：底部）")
    private Integer menuPosition;


    public String getTypeCh() {
        return UtilDic.getDictName(SystemConstant.MENU_TYPE.CODE, this.type + "");
    }

    public String getButtonTypeCh() {
        return UtilDic.getDictName(SystemConstant.BUTTON_TYPE.CODE, this.buttonType);
    }

    public String getEnabledCh() {
        return UtilDic.getDictName(SystemConstant.ENABLE_STATUS.CODE, this.enabled + "");
    }

    public String getHiddenCh() {
        return UtilDic.getDictName(SystemConstant.YES_OR_NO.CODE, this.hidden + "");
    }


}