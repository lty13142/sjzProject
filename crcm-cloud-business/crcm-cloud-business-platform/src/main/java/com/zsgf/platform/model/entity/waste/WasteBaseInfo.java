package com.zsgf.platform.model.entity.waste;

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
 * 危废名录对象 sys_waste_base_info
 *
 * @author gzl
 * @date 2023-02-22
 */
@Setter
@Getter
@ToString
@ApiModel("危废名录")
@TableName("tbl_waste_base_info")
public class WasteBaseInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @Size(max = 32, message = "id最多输入32个字符")
    private String id;

    /**
     * 名录版本
     */
    @ApiModelProperty(value = "名录版本")
    @Size(max = 10, message = "名录版本最多输入10个字符")
    private String directoryVersion;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    private Integer level;

    /**
     * 废物种类 0:危废 1：固废 2 医废
     */
    @ApiModelProperty(value = "废物种类 0:危废 1：固废 2 医废")
    private Integer wasteCategory;

    /**
     * 废物类别
     */
    @ApiModelProperty(value = "废物类别", required = true)
    @NotBlank(message = "废物类别不能为空")
    @Size(max = 255, message = "废物类别最多输入255个字符")
    private String wasteType;

    /**
     * 废物类别名称
     */
    @ApiModelProperty(value = "废物类别名称", required = true)
    @NotBlank(message = "废物类别名称不能为空")
    @Size(max = 255, message = "废物类别名称最多输入255个字符")
    private String wasteTypeName;

    /**
     * 废物代码
     */
    @ApiModelProperty(value = "废物代码", required = true)
    @NotBlank(message = "废物代码不能为空")
    @Size(max = 100, message = "废物代码最多输入100个字符")
    private String wasteCode;

    /**
     * 废物代码名称
     */
    @ApiModelProperty(value = "废物代码名称")
    @Size(max = 500, message = "废物代码名称最多输入500个字符")
    private String wasteCodeName;

    /**
     * 危险特性 腐蚀性C 毒性T 易燃性I 反应性R 感染性 In
     */
    @ApiModelProperty(value = "危险特性 腐蚀性C 毒性T 易燃性I 反应性R 感染性 In")
    @Size(max = 100, message = "危险特性 腐蚀性C 毒性T 易燃性I 反应性R 感染性 In最多输入100个字符")
    private String hazards;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
