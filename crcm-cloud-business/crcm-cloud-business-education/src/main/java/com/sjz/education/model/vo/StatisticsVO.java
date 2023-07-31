package com.sjz.education.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @PackageName: com.sjz.education.model.entity.vo
 * @ClassName: StatisticsVO
 * @Author: cb
 * @Date: 2023-04-10 10:20
 * @Description: 统计图接收数据类
 */
@Data
public class StatisticsVO {

	private String name;

	private BigDecimal value;

}
