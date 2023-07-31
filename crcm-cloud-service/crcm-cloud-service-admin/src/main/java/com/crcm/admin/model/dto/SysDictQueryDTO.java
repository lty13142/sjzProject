package com.crcm.admin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SysDictQueryDTO
 * @Description 字典查询DTO
 * @Author GZL
 * @Date 2023/3/2 10:30
 * @Version 1.0
 **/
@Data
public class SysDictQueryDTO {

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "父级id")
    private Long pid;

    @ApiModelProperty(value = "字典类型（0：字典目录，1：字典常量）")
    private Integer type;

    @ApiModelProperty(value = "字典代码")
    private String code;
}
