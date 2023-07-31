package com.zsgf.platform.dto.waste;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName WasteDisposalWayDTO
 * @Description 废物处置方式查询DTO
 * @Author GZL
 * @Date 2023/3/30 11:26
 * @Version 1.0
 **/
@Getter
@Setter
public class WasteDisposalWayDTO {

    /**
     * 处置方式类型(大类,小类, 字典：WASTE_DISPOSAL_WAY_TYPE)
     */
    @ApiModelProperty(value = "处置方式类型(0：大类, 1：小类, 字典：WASTE_DISPOSAL_WAY_TYPE)")
    private String disposalWayType;

    /**
     * 处置方式名称
     */
    @ApiModelProperty(value = "处置方式名称")
    private String disposalWayName;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private String parentId;
}
