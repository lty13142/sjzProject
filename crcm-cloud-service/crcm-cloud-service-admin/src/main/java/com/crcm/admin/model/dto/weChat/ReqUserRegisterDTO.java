package com.crcm.admin.model.dto.weChat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @PackageName: com.crcm.admin.model.dto.weChat
 * @ClassName: ResUserAccountDTO
 * @Author: cb
 * @Date: 2023-04-06 20:10
 * @Description: 微信授权登录用户实体类
 */
@Data
public class ReqUserRegisterDTO {
	@NotNull(message = "用户账户id为空")
	@ApiModelProperty(value = "账户id")
	private String id;
	@Length(max = 64)
	@ApiModelProperty("昵称")
	private String nickName;
	@ApiModelProperty(value = "密码")
	private String password;
	@ApiModelProperty(value = "电话号码")
	private String phoneNum;
	@ApiModelProperty(value = "共用code")
	private String shareCode;
	@ApiModelProperty(value = "角色")
	private Long userRole;
	@ApiModelProperty(value = "性别")
	private String sex;
	@ApiModelProperty(value = "头像")
	private String headPortrait;
	@ApiModelProperty(value = "个性签名")
	private String personalizedSignature;
	@ApiModelProperty(value = "生日")
	private LocalDate birthday;
	@ApiModelProperty(value = "微信用户openId")
	private String openId;
}
