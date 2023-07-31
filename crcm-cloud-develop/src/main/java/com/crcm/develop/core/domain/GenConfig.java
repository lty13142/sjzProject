
package com.crcm.develop.core.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 生成配置
 */
@Data
public class GenConfig {
	/**
	 * 数据源ID
	 */
	private String dataSourceId;
	/**
	 * 包名
	 */
	private String packageName;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 模块名称
	 */
	private String moduleName;
	/**
	 * 路由名称
	 */
	private String routeName;
	/**
	 * 表前缀
	 */
	private String tablePrefix;

	/**
	 * 表名称
	 */
	@NotNull(message = "表名不能为空")
	private String tableName;

	/**
	 * 表备注
	 */
	private String comments;
}
