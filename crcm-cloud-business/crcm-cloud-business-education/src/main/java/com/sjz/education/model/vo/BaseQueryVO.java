package com.sjz.education.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: sjz-project
 * @description: 基础查询VO类
 * @author: sssccc
 * @create: 2023-04-03 16:00
 **/
@Data
public class BaseQueryVO {
    /**
     * id 集合
     */
    @ApiModelProperty(value = "id 集合",required = true)
    private List<String> ids;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
