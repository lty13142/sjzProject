package com.sjz.governance.model.vo.villager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Tianyu
 * @date 2023/4/7 18:51
 */
@Data
public class VillagerStaticsVo {

    /**
     * 男总人数
     */
    @ApiModelProperty(value = "男总人数")
    private int manNum;

    /**
     * 女总人数
     */
    @ApiModelProperty(value = "女总人数")
    private int woManNum;

    /**
     * 各年龄段统计信息
     */
    @ApiModelProperty(value = "各年龄段统计信息")
    private List<VillagerStaticsAgeVo> voList;

}
