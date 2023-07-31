package com.sjz.education.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.crcm.admin.api.dto.res.AreaDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: sjz-project
 * @description: 积分上报
 * @author: sssccc
 * @create: 2023-04-11 16:40
 **/
@Data
public class AreaReportVO extends AreaDTO {
    /**
     * 今年总获得积分
     */
    @ApiModelProperty(value = "获得积分")
    private Integer earnPoints;

    /**
     * 今年总兑换积分
     */
    @TableField(exist = false)
    private Integer exchangePoints;

    /**
     * 当前积分
     */
    @TableField(exist = false)
    private Integer nowPoints;
}
