package com.sjz.governance.model.vo.camera;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CameraStatusStatisticsVo
 * @Description 监控状态统计VO
 * @Author GZL
 * @Date 2023/4/9 16:22
 * @Version 1.0
 **/
@Data
public class CameraStatusStatisticsVo {

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private Integer total;

    /**
     * 总数
     */
    @ApiModelProperty(value = "在线数量")
    private Integer onLine;

    /**
     * 总数
     */
    @ApiModelProperty(value = "离线数量")
    private Integer offLine;
}
