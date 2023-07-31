package com.sjz.evaluations.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.core.constant.DicConstant;
import com.sjz.evaluations.util.UtilDic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
/**
 * 考核对象 gr_ examine
 *
 * @author guozhilin
 * @date 2023-04-06
 */
@Setter
@Getter
@ToString
@ApiModel("考核")
@TableName("gr_examine")
public class Examine extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /** 名称 */
        @ApiModelProperty(value = "名称")
    @Size(max = 64, message = "名称最多输入64个字符")
    private String name;

    /** 年份 */
        @ApiModelProperty(value = "年份")
    @Size(max = 10, message = "年份最多输入10个字符")
    private String yearly;

    /** 发布状态 */
    @ApiModelProperty(value = "发布状态 字典code(RELEASE_STATUS)")
    @Size(max = 10, message = "发布状态最多输入10个字符")
    private String releaseStatus;

    @ApiModelProperty(value = "是否确认 字典code(CONFIRM_STATUS)")
    private String confirm;

    /** 删除  0 未删除 1 删除 */
        @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "删除  0 未删除 1 删除")
    private Integer deleted;

   @TableField(exist = false)
   @ApiModelProperty(value = "区域ID")
    private String areaId;

    @TableField(exist = false)
    @ApiModelProperty(value = "村庄CODE")
   private String villageCode;

    @TableField(exist = false)
    @ApiModelProperty(value = "岗位id")
    private String positionId;

    @TableField(exist = false)
    @ApiModelProperty(value = "所属组织ID")
    private Long orgId;

    @TableField(exist = false)
    @ApiModelProperty(value = "0 考核者，1 被考核者")
    private String type;

    public String getReleaseStatusCh() {
        return UtilDic.getDictName(DicConstant.RELEASE_STATUS.CODE, this.getReleaseStatus());
    }

    public String getConfirmCh(){
        return UtilDic.getDictName(DicConstant.CONFIRM_STATUS.CODE,this.getConfirm());
    }

}
