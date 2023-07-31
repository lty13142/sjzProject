package com.sjz.governance.model.vo.villager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Tianyu
 * @date 2023/4/7 18:54
 */
@Data
public class VillagerStaticsAgeVo {

    /**
     * 年龄范围
     */
    @ApiModelProperty(value = "年龄范围")
    private String ageRange;

    /**
     * 男数量
     */
    @ApiModelProperty(value = "男数量")
    private int mNum;

    /**
     * 女数量
     */
    @ApiModelProperty(value = "女数量")
    private int wmNum;

    /**
     * 男百分比
     */
    @ApiModelProperty(value = "男百分比")
    private String mNumPercentage;

    /**
     * 女百分比
     */
    @ApiModelProperty(value = "女百分比")
    private String wmNumPercentage;

}
