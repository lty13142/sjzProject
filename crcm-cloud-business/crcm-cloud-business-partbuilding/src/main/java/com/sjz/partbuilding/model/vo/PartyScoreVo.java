package com.sjz.partbuilding.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author xiangmx
 * @createTime 2023/4/7 16:45
 **/
@Data
public class PartyScoreVo {
    /**
     * 排名
     */
    private int ranking;
    /**
     * 党员姓名
     */
    private String name;
    /**
     * 分值
     */
    private BigDecimal score;
    /**
     * 党员ID
     */
    private String userId;
}
