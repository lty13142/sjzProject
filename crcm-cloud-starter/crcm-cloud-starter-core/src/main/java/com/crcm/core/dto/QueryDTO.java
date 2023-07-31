package com.crcm.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName QueryDTO
 * @Description 通用查询类
 * @Author GZL
 * @Date 2023/2/10 10:11
 * @Version 1.0
 **/
@Data
public class QueryDTO {
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String beginTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endTime;

}
