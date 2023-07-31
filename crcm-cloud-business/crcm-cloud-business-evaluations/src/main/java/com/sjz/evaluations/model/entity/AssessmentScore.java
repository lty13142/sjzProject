package com.sjz.evaluations.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.core.constant.DicConstant;
import com.sjz.evaluations.model.vo.AttachmentsSimpleVO;
import com.sjz.evaluations.util.FileUtil;
import com.sjz.evaluations.util.UtilDic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
/**
 * 考核人员分数确认对象 gr_assessment_score
 *
 * @author guozhilin
 * @date 2023-04-09
 */
@Setter
@Getter
@ToString
@ApiModel("考核人员分数确认")
@TableName("gr_assessment_score")
public class AssessmentScore extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    private String id;

    /** 指标表id */
        @ApiModelProperty(value = "指标表id")
    @Size(max = 64, message = "指标表id最多输入64个字符")
    private String examineId;

    @ApiModelProperty(value = "年份")
    @Size(max = 10, message = "年份最多输入10个字符")
    private String yearly;

    /** 一级指标 */
        @ApiModelProperty(value = "一级指标")
    @Size(max = 64, message = "一级指标最多输入64个字符")
    private String firstOrder;

    /** 二级指标 */
        @ApiModelProperty(value = "二级指标", required = true)
    @NotBlank(message = "二级指标不能为空")
    @Size(max = 64, message = "二级指标最多输入64个字符")
    private String secondaryOrder;

    /** 三级指标 */
        @ApiModelProperty(value = "三级指标", required = true)
    @NotBlank(message = "三级指标不能为空")
    @Size(max = 64, message = "三级指标最多输入64个字符")
    private String threeOrder;

    /** 四级指标项id */
        @ApiModelProperty(value = "四级指标项id")
    @Size(max = 64, message = "四级指标项id最多输入64个字符")
    private String fourOrder;

    /** 指标内容 */
        @ApiModelProperty(value = "指标内容")
    @Size(max = 255, message = "指标内容最多输入255个字符")
    private String content;


    /** 是否确认 0 未确认 1 确认 */
        @ApiModelProperty(value = "是否确认 0 未确认 1 确认")
    @Size(max = 10, message = "是否确认 0 未确认 1 确认最多输入10个字符")
    private String confirm;

    /**  是否完成 0 未完成  1 已完成 */
        @ApiModelProperty(value = " 是否完成 0 未完成  1 已完成")
    private String complete;

    @ApiModelProperty(value = "考核责任部门ID")
    private String assessmentDepartment;

    @ApiModelProperty(value = "考核岗位")
    private String post;

    /** 被考核村 */
   @ApiModelProperty(value = "被考核村")
    private String areaId;

   @TableField(exist = false)
   @ApiModelProperty(value = "考核村名称")
   private String areaName;

    @TableField(exist = false)
    @ApiModelProperty(value = "管区")
    private String villageName;

    @ApiModelProperty(value = "补充内容")
    private String remarks;

    /** 考核人ID */
        @ApiModelProperty(value = "考核人ID")
    @Size(max = 64, message = "考核人ID最多输入64个字符")
    private String userId;

    /** 附件 */
        @ApiModelProperty(value = "附件")
    @Size(max = 255, message = "附件最多输入255个字符")
    private String attachment;

    /** 分数 */
        @ApiModelProperty(value = "分数")
    @Size(max = 64, message = "分数最多输入64个字符")
    private String score;

    /** 删除  0 未删除 1 删除 */
        @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "删除  0 未删除 1 删除")
    private Integer deleted;

    @TableField(exist = false)
    @ApiModelProperty(value = "村庄CODE")
    private String villageCode;
    @TableField(exist = false)
    @ApiModelProperty(value = "0 考核者，1 被考核者")
    private String type;

    public String getConfirmCh(){
        return UtilDic.getDictName(DicConstant.CONFIRM_STATUS.CODE,this.getConfirm());
    }

    public String getCompleteCh(){return UtilDic.getDictName(DicConstant.COMPLETE_STATUS.CODE,this.getComplete());}


    @TableField(exist = false)
    @ApiModelProperty(value = "附件列表")
    private String attachmentList;

    @TableField(exist = false)
    private String assessmentDepartmentName;

    @TableField(exist = false)
    private String postName;

    public List<AttachmentsSimpleVO> getAttachmentList() {
        if (StringUtils.isEmpty(attachment)) {
            return null;
        }
        return FileUtil.getAllAttachments(attachment);
    }
}
