package com.crcm.admin.model.vo;

import com.crcm.admin.model.entity.SysPosition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Tianyu
 * @date 2023/4/6 16:16
 */
@Data
public class SysPositionVo extends SysPosition {

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "组织名称")
    private String orgName;

}
