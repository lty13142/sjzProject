package com.zsgf.platform.model.entity.waste;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 废物处置方式对象 tbl_waste_disposal_way
 *
 * @author gzl
 * @date 2023-03-30
 */
@Setter
@Getter
@ToString
@ApiModel("废物处置方式")
@TableName("tbl_waste_disposal_way")
public class WasteDisposalWay extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    @Size(max = 100, message = "id最多输入100个字符")
    private String id;

    /**
     * 处置方式类型(大类,小类, 字典：WASTE_DISPOSAL_WAY_TYPE)
     */
    @ApiModelProperty(value = "处置方式类型(0：大类, 1：小类, 字典：WASTE_DISPOSAL_WAY_TYPE)")
    @NotBlank(message = "请选择处置方式类型")
    private String disposalWayType;

    /**
     * 处置方式名称
     */
    @ApiModelProperty(value = "处置方式名称")
    @Size(max = 100, message = "处置方式名称最多输入100个字符")
    private String disposalWayName;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    @Size(max = 100, message = "父级ID最多输入100个字符")
    private String parentId;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    @NotNull(message = "是否删除不能为空")
    private Integer deleted;

}
