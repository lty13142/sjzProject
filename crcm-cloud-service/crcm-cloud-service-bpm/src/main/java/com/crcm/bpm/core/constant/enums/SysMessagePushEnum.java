
package com.crcm.bpm.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统消息推送常量
 * @author 80971
 */
@Getter
@AllArgsConstructor
public enum SysMessagePushEnum {
	/**
	 * 待办
	 */
	add("add", "增加"),

	/**
	 * 待阅
	 */
	delete("delete", "删除");

	/**
	 * 类型
	 */
	private final String type;
	/**
	 * 描述
	 */
	private final String description;
}
