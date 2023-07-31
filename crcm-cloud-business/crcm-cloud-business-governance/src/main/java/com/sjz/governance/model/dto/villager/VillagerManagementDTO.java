package com.sjz.governance.model.dto.villager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName VillagerManagementDTO
 * @Description 村民信息查询DTO
 * @Author GZL
 * @Date 2023/4/3 17:06
 * @Version 1.0
 **/
@Data
public class VillagerManagementDTO {

    /**
     * 村民姓名
     */
    @ApiModelProperty(value = "村民姓名")
    private String villagerName;

    /**
     * 类别 字典：VILLAGER_TYPE
     */
    @ApiModelProperty(value = "类别 字典：VILLAGER_TYPE")
    private String type;

    /**
     * 性别 字典：SEX
     */
    @ApiModelProperty(value = "性别 字典：SEX")
    private String sex;

    /**
     * 所属管区id
     */
    @ApiModelProperty(value = "所属管区id")
    private String district;

    /**
     * 所属村id
     */
    @ApiModelProperty(value = "所属村id")
    private String village;

    /**
     * 所属村
     */
    @ApiModelProperty(value = "所属村名称")
    private String villageName;
}
