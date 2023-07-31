package com.crcm.admin.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.admin.utils.UtilDic;
import com.crcm.core.constant.SystemConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

import lombok.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 岗位对象 sys_position
 *
 * @author cb
 * @date 2023-04-06
 */
@Setter
@Getter
@ToString
@ApiModel("岗位")
@TableName("sys_position")
public class SysPosition extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /** 主键id */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键id")
    private String id;

    /** 岗位名称 */
    @ApiModelProperty(value = "岗位名称")
    private String positionName;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    private String departmentId;

    /** 职级 */
    @ApiModelProperty(value = "职级")
    private String positionLevel;

    /** 删除标志 */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "删除标志")
    private Integer deleted;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getPositionLevelCh() {
        if (Objects.isNull(this.getPositionLevel())){
            return null;
        }
        return UtilDic.getDictName(SystemConstant.POSITION_LEVEL.CODE, this.getPositionLevel() + "");
    }

}
