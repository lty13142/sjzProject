package com.crcm.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName CommonTreeView
 * @Description 通用树视图
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
@Data
@Accessors(chain = true)
public class TreeView implements Serializable {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "文本")
    private String label;

    @ApiModelProperty(value = "父级ID")
    private String pid;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "子集列表")
    private List<TreeView> children;

    @ApiModelProperty(value = "是否展开")
    private Boolean open;

    @ApiModelProperty(value = "是否选中")
    private Boolean checked;
}
