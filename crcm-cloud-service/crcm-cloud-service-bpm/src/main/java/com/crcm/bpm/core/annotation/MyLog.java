
package com.crcm.bpm.core.annotation;

import com.crcm.bpm.core.constant.MyLogType;

import java.lang.annotation.*;

/**
 * @author bot20
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

	/**
	 * 方法名
	 *
	 * @return
	 */
	String value();

	/**
	 * 日志类型
	 *
	 * @return
	 */
	MyLogType type();
}
