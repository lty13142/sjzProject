package com.zsgf.platform.model.entity.sheets;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 设备确认单对象 tbl_equipment_sheet
 *
 * @author gzl
 * @date 2023-03-30
 */
@Setter
@Getter
@ToString
@ApiModel("设备确认单")
@TableName("tbl_equipment_sheet")
public class EquipmentSheet extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    @Size(max = 100, message = "企业id最多输入100个字符")
    private String companyId;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    @Size(max = 100, message = "企业名称最多输入100个字符")
    private String companyName;

    /**
     * 附件id
     */
    @ApiModelProperty(value = "附件id")
    @Size(max = 64, message = "附件id最多输入64个字符")
    private String fileId;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    @Size(max = 200, message = "附件名称最多输入200个字符")
    private String fileName;

    /**
     * 所属区域代码
     */
    @ApiModelProperty(value = "所属区域代码")
    @Size(max = 100, message = "所属区域代码最多输入100个字符")
    private String belongAreaCode;

    /**
     * 所属区域
     */
    @ApiModelProperty(value = "所属区域")
    @Size(max = 100, message = "所属区域最多输入100个字符")
    private String belongArea;

    /**
     * 是否上传（0：否，1：是）
     */
    @ApiModelProperty(value = "是否上传（0：否，1：是）")
    @Size(max = 2, message = "是否上传（0：否，1：是）最多输入2个字符")
    private String uploadFlag;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除", required = true)
    @NotNull(message = "是否删除不能为空")
    private Integer deleted;

}
