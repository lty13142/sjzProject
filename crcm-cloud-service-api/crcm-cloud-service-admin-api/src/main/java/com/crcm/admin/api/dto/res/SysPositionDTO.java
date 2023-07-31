package com.crcm.admin.api.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yzw
 * @data 2023/4/11
 * @apiNote
 */
@Data
public class SysPositionDTO {

    private String id;

    /** 岗位名称 */
    private String positionName;
}
