package com.zsgf.platform.dto;

import com.crcm.core.dto.QueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName ProvincialDTO
 * @Description 省平台接口回流请求DTO
 * @Author GZL
 * @Date 2023/2/9 17:04
 * @Version 1.0
 **/
@Setter
@Getter
@ToString
@ApiModel("省平台接口回流请求DTO")
public class ProvincialDTO extends QueryDTO {


    @ApiModelProperty(value = "Token")
    private String token;

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "分页数")
    private String pageSize;
}
