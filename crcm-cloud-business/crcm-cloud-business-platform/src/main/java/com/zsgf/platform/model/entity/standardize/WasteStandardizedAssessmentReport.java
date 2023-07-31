package com.zsgf.platform.model.entity.standardize;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 标准化考核整改报告对象 tbl_waste_standardized_assessment_report
 *
 * @author gzl
 * @date 2023-03-24
 */
@Setter
@Getter
@ToString
@ApiModel("标准化考核整改报告")
@TableName("tbl_waste_standardized_assessment_report")
public class WasteStandardizedAssessmentReport extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    @Size(max = 100, message = "id最多输入100个字符")
    private String id;

    /**
     * 规范化考核id
     */
    @ApiModelProperty(value = "规范化考核id")
    @Size(max = 32, message = "规范化考核id最多输入32个字符")
    private String judgeId;

    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")
    @Size(max = 100, message = "公司id最多输入100个字符")
    private String companyId;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    @Size(max = 255, message = "公司名称最多输入255个字符")
    private String companyName;

    /**
     * 镇区代码
     */
    @ApiModelProperty(value = "镇区代码")
    @Size(max = 100, message = "镇区代码最多输入100个字符")
    private String areaCode;

    /**
     * 期限日期
     */
    @ApiModelProperty(value = "期限日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadlineDate;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    @Size(max = 20, message = "联系人最多输入20个字符")
    private String contacts;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    @Size(max = 20, message = "联系电话最多输入20个字符")
    private String telephone;

    /**
     * 复评人
     */
    @ApiModelProperty(value = "复评人")
    @Size(max = 255, message = "复评人最多输入255个字符")
    private String reviewer;

    /**
     * 复评日期
     */
    @ApiModelProperty(value = "复评日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assessmentDate;

    /**
     * 执法科室
     */
    @ApiModelProperty(value = "执法科室")
    @Size(max = 1000, message = "执法科室最多输入1000个字符")
    private String lawEnforceOrg;

    /**
     * 完成整改时间
     */
    @ApiModelProperty(value = "完成整改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rectificationTime;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
