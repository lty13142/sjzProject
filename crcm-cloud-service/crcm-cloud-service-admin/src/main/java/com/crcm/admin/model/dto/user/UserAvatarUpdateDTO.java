package com.crcm.admin.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserAvatarUpdateDTO
 * @Description 用户头像修改 DTO
 * @Author GZL
 * @Date 2023/2/8 14:36
 * @Version 1.0
 **/
@Data
public class UserAvatarUpdateDTO {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "用户头像")
    private String avatar;
}
