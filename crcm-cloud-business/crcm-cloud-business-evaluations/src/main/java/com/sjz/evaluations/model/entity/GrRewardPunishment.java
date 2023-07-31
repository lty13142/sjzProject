package com.sjz.evaluations.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.core.constant.DicConstant;
import com.sjz.evaluations.model.vo.AttachmentsSimpleVO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sjz.evaluations.util.FileUtil;
import com.sjz.evaluations.util.UtilDic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
/**
 * 奖惩事项对象 gr_reward_punishment
 *
 * @author guozhilin
 * @date 2023-04-04
 */
@Setter
@Getter
@ToString
@ApiModel("奖惩事项")
@TableName("gr_reward_punishment")
public class GrRewardPunishment extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    @Size(max = 64, message = "主键最多输入64个字符")
    private String id;
    /** 奖惩名称 */
    @ApiModelProperty(value = "奖惩名称")
    @Size(max = 64, message = "奖惩名称最多输入64个字符")
    private String name;

    /** 奖惩时间 */
        @ApiModelProperty(value = "奖惩时间")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rewardTime;

    /** 奖惩类型 */
    @ApiModelProperty(value = "奖惩类型")
    private String organizationalMatters;

    /** 备注 */
        @ApiModelProperty(value = "备注")
    private String remarks;

    /** 附件 */
        @ApiModelProperty(value = "附件")
    @Size(max = 255, message = "附件最多输入255个字符")
    private String attachment;

    /** 删除  0 未删除 1 删除 */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "删除  0 未删除 1 删除")
    private Integer deleted;

    /** 获取组织ID */
    @ApiModelProperty(value = "获取组织ID")
    @Size(max = 64, message = "获取组织ID最多输入64个字符")
    private String organizationId;

    @TableField(exist = false)
    @ApiModelProperty(value = "管区名称")
    private String dName;

    @TableField(exist = false)
    @ApiModelProperty(value = "基层组织名称")
    private String gomName;

    @TableField(exist = false)
    @ApiModelProperty(value = "类型（1：镇  2：区域  3：村）")
    private String type;


    public String getOrganizationalMattersCh() {
        return UtilDic.getDictName(DicConstant.ORGANIZATIONAL_MATTERS.CODE, this.getOrganizationalMatters());
    }

    @TableField(exist = false)
    @ApiModelProperty(value = "附件列表")
    private String attachmentList;

    public List<AttachmentsSimpleVO> getAttachmentList() {
        if (StringUtils.isEmpty(attachment)) {
            return null;
        }
        return FileUtil.getAllAttachments(attachment);
    }


}
