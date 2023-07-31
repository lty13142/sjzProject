
package com.crcm.develop.tool.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("gen_table")
public class TableEntity {
	/**
	 * 名称
	 */
	private String tableName;
	/**
	 * 备注
	 */
	private String comments;
	/**
	 * 主键
	 */
	private ColumnEntity pk;
	/**
	 * 列名
	 */
	private List<ColumnEntity> columns;
	/**
	 * 驼峰类型
	 */
	private String caseClassName;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private LocalDateTime createTime;
	/**
	 * 编码集
	 */
	private String tableCollation;
	/**
	 * 普通类型
	 */
	private String lowerClassName;
	private String caseEntityName;
	private String lowerEntityName;
	/** 数据源ID */
	private String datasourceId;

}
