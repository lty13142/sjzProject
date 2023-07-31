package com.crcm.admin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @PackageName: com.crcm.admin.model.dto
 * @ClassName: AreaDTO
 * @Author: cb
 * @Date: 2023-04-04 15:07
 * @Description: 区域查询数据封装类
 */
@Data
public class AreaDTO {
	@ApiModelProperty(value = "区域ID")
	private String id;
	@ApiModelProperty(value = "父ID")
	private String pid;
	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "名称")
	private String name;
}
