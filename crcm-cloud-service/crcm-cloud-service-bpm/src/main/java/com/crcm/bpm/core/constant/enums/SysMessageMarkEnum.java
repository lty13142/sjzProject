
package com.crcm.bpm.core.constant.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.crcm.bpm.utils.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统消息标识常量
 * @author 80971
 */
@Getter
@AllArgsConstructor
public enum SysMessageMarkEnum  implements IEnum, EnumUtil {

	PROCESS(1, "流程"),
	NOTICE(2, "通知"),
	ANNOUNCEMENT(3, "公告"),
	SIGN_QUOTE(4, "签报"),
	MEETING(5, "会议"),
	// 这里不知道他们以前为什么会有两个一样的
//	SIGN_QUOTE_SIX(6, "签报"),
	SIGN(7, "会签"),
	CARBON_COPY(8, "抄送"),
	SUMMARY(9, "纪要"),
	OVERSEE(10, "督办"), // 督办任务下发
	PASS_READ(11, "传阅"),
	ISSUE(12, "议题"),
	VEHICLE_MANAGEMENT(13, "车辆管理"),
	SUPERVISED_CONFIRMED(15, "督办反馈"),
	SUPERVISORY_TASK(16, "督办确认"),
	OFFICE_SUPPLIES(17, "办公用品"),
	FIXED_ASSETS(18, "固定资产"),
	RECEIVE_FILE(19, "内部收文"),
	UP_FILE(20, "上行文"),
	EXTERNAL_COME_FILE(21, "外部来文"),
	SEND_FILE(22, "发文处理"),
	SEND_MEETING(23, "上会"),
	FILE_CIRCULATION(24, "文件传阅"),
	TECHNICAL_SUPPORT(25, "技术支持"),
	SALARY_SEND(26, "工资条发送"),
	SALARY_RECALL(27, "工资条撤回"),
	PROJECT_APPROVAL(28, "项目立项"),
	DOCUMENT_NOTIFICATION(29, "文档通知"),
	PROCESS_AUDIT(30, "流程审核"),
	ARCHIVES_APPOROVE(31, "档案审批"),
	VIDEO_MEETING(32, "视频会议"),
	EMAIL(33, "邮件"),
	COPY_SEND(34, "抄送"),
	WORK_COMMUNICATE(35, "工作请示"),
	WORK_COMMUNICATE_FEED_BACK(36, "工作请示反馈"),
	FLOW_FINISH(37, "流程结束"),
	SUPERVISED_CONFIRMED_NEW(38, "督办反馈"), // 新督办反馈
	PROGRESS_REJECTION(39, "督办进度驳回"), // 新督办进度驳回
	FLOW_REVOKE(40, "流程撤销"),
	;

	private Integer value;

	private String desc;
}
