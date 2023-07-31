package com.sjz.evaluations.model.vo;

import com.sjz.evaluations.model.entity.ExamineIndicators;
import lombok.Data;

/**
 * 管区指标返回实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/26 9:28
 */
@Data
public class RegionIndicatorsVO extends ExamineIndicators {

    /**
     * 管区id
     */
    private String regionId;
    /**
     * 管区代码
     */
    private String regionAreaCode;
    /**
     * 管区名称
     */
    private String regionAreaName;
    /**
     * 定性/定量目标
     */
    private String target;
    /**
     * 单位
     */
    private String unit;
    /**
     * 考核状态
     */
    private String status;


}
