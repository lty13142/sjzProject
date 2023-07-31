package com.zsgf.platform.model.entity.collect;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 企业收集人员信息对象 tbl_collector
 *
 * @author gzl
 * @date 2023-03-23
 */
@Setter
@Getter
@ToString
@ApiModel("企业收集人员信息")
@TableName("tbl_collector")
public class Collector extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    @Size(max = 32, message = "最多输入32个字符")
    private String id;

    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")
    @Size(max = 64, message = "公司id最多输入64个字符")
    private String companyId;

    /**
     * 收集人员名称
     */
    @ApiModelProperty(value = "收集人员名称", required = true)
    @NotBlank(message = "收集人员名称不能为空")
    @Size(max = 100, message = "收集人员名称最多输入100个字符")
    private String collectorName;

    /**
     * 收集人员电话
     */
    @ApiModelProperty(value = "收集人员电话", required = true)
    @NotBlank(message = "收集人员电话不能为空")
    @Size(max = 64, message = "收集人员电话最多输入64个字符")
    private String collectorPhone;

    /**
     * 类型：1 产废车间，2 医院科室
     */
    @ApiModelProperty(value = "类型：1 产废车间，2 医院科室", required = true)
    @NotBlank(message = "类型：1 产废车间，2 医院科室不能为空")
    @Size(max = 10, message = "类型：1 产废车间，2 医院科室最多输入10个字符")
    private String type;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
