package com.zsgf.platform.dto.businessLicense;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName BusinessLicenseDTO
 * @Description 经营许可查询DTO
 * @Author GZL
 * @Date 2023/3/29 10:20
 * @Version 1.0
 **/
@Data
public class BusinessLicenseDTO {

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id", hidden = true)
    private String companyId;

    /**
     * 许可证编号
     */
    @ApiModelProperty(value = "许可证编号")
    private String licenseCode;

    /**
     * 核准经营方式
     */
    @ApiModelProperty(value = "核准经营方式")
    private String operateWay;
}
