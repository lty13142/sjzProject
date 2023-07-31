package com.crcm.admin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: QueryVo
 * @Description 通用查询类
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author Administrator
 * @Date 2019/12/12
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
