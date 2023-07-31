package com.sjz.education.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @PackageName: com.sjz.education.model.dto
 * @ClassName: EduPointExchange
 * @Author: cb
 * @Date: 2023-04-09 11:52
 * @Description:
 */
@Data
public class EduPointExchangeDTO {
	@ApiModelProperty(value = "开始时间")
	private String startTime;
	@ApiModelProperty(value = "结束时间")
	private String endTime;
	@ApiModelProperty(value = "查询类型(1:今日  2：本周  3：本月  4：全年)")
	private String queryType;
}
