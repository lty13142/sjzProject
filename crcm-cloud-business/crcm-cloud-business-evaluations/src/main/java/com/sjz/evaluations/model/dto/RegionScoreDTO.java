package com.sjz.evaluations.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 管区打分查询接受实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/26 21:53
 */
@Data
@Accessors(chain = true)
public class RegionScoreDTO implements Serializable {

    /**
     * 考核任务id
     */
    private String examineId;
    /**
     * 管区名称
     */
    private String regionAreaName;
}
