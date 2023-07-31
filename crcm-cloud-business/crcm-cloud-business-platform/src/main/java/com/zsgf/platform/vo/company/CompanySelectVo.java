package com.zsgf.platform.vo.company;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CompanySelectVo
 * @Description 企业下拉列表Vo
 * @Author GZL
 * @Date 2023/3/29 14:50
 * @Version 1.0
 **/
@Data
public class CompanySelectVo {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;

}
