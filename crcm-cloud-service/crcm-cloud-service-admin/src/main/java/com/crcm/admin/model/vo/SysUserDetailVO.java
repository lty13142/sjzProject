package com.crcm.admin.model.vo;

import com.crcm.admin.model.entity.SysUserDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserDetailVo
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/4/1
 **/
@Data
public class SysUserDetailVO extends SysUserDetail {

    @ApiModelProperty(value = "人脸识别图片地址")
    private String facePicPath;
}
