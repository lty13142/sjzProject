package com.sjz.governance.model.vo.event;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GovernanceHomeEventTotalVo
 * @Description 综合治理首页顶部-告警事件总数统计
 * @Author GZL
 * @Date 2023/4/9 9:55
 * @Version 1.0
 **/
@Data
public class GovernanceHomeEventTotalVo {

    @ApiModelProperty(value = "总数量")
    private Integer total;

    @ApiModelProperty(value = "已处理数量")
    private Integer handelCount;

    @ApiModelProperty(value = "未处理数量")
    private Integer noHandelCount;
}
