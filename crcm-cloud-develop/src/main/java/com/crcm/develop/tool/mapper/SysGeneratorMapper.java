
package com.crcm.develop.tool.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.develop.tool.entity.TableEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 */
public interface SysGeneratorMapper {

	/**
	 * 分页查询表格
	 *
	 * @param page
	 * @param tableName
	 * @return
	 */
	IPage<TableEntity> queryList(Page page, @Param("tableName") String tableName);

	/**
	 * 查询表信息
	 *
	 * @param tableName 表名称
	 * @return
	 */
	Map<String, String> queryTable(String tableName);

	/**
	 * 查询表列信息
	 *
	 * @param tableName 表名称
	 * @return
	 */
	List<Map<String, String>> queryColumns(String tableName);
}