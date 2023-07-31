package com.sjz.evaluations.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import javax.validation.constraints.Size;
/**
 * 管区对象 gr_district
 *
 * @author guozhilin
 * @date 2023-04-04
 */
@Setter
@Getter
@ToString
@ApiModel("管区")
@TableName("gr_district")
public class GrDistrict extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    @Size(max = 64, message = "主键最多输入64个字符")
    private String id;

    /** 名称 */
        @ApiModelProperty(value = "名称")
    @Size(max = 64, message = "名称最多输入64个字符")
    private String name;

    /** 管区联系人 */
        @ApiModelProperty(value = "管区联系人")
    @Size(max = 64, message = "管区联系人最多输入64个字符")
    private String districtContacts;

    /** 管区联系方式 */
        @ApiModelProperty(value = "管区联系方式")
    @Size(max = 64, message = "管区联系方式最多输入64个字符")
    private String districtContact;

    /** 删除  0 未删除 1 删除 */
        @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "删除  0 未删除 1 删除")
    private Integer deleted;

}
