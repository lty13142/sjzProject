package com.sjz.evaluations.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * 考核对象 kh_examine_task
 *
 * @author zzyt
 * @date 2023-04-25
 */
@Data
@Accessors(chain = true)
@TableName("kh_examine_task")
public class ExamineTask extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 考核名称
     */
    @NotBlank(message = "考核名称不能为空")
    @Length(max = 128, message = "考核名称长度不能超过128位")
    @ApiModelProperty(value = "考核名称")
    private String examineName;

    /**
     * 年份
     */
    @NotNull(message = "考核年份不能为空")
    @ApiModelProperty(value = "考核年份")
    private Integer examineYear;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private LocalDate publishDate;

    /**
     * 发布类型(0:未发布 1:已发布)
     */
    @ApiModelProperty(value = "发布类型(0:未发布 1:已发布)")
    private Integer publishType;


}
