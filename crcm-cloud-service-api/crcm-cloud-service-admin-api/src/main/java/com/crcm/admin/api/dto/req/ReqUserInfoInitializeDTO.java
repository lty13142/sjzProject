package com.crcm.admin.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

/**
 * @ClassName ReqUserInfoInitializeDTO
 * @Description 用户信息初始化 DTO
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/30
 **/
@Data
@ApiModel(value = "APP-新增用户账户 - DTO")
public class ReqUserInfoInitializeDTO {
    @NotNull(message = "用户账户id为空")
    @ApiModelProperty(value = "账户id")
    private Long accountId;

    @Length(max = 64)
    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("头像")
    private String headPortrait;

    @ApiModelProperty(value = "个性签名")
    private String personalizedSignature;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;
}
