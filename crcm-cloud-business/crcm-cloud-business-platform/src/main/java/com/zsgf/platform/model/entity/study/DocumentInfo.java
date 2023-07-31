package com.zsgf.platform.model.entity.study;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 文档信息对象 tbl_document_info
 *
 * @author gzl
 * @date 2023-03-28
 */
@Setter
@Getter
@ToString
@ApiModel("文档信息")
@TableName("tbl_document_info")
public class DocumentInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    @Size(max = 36, message = "id最多输入36个字符")
    private String id;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    @Size(max = 128, message = "文件名称最多输入128个字符")
    private String fileName;

    /**
     * 文件id
     */
    @ApiModelProperty(value = "文件id")
    @Size(max = 64, message = "文件id最多输入64个字符")
    private String fileId;

    /**
     * 类型（政策法规、指导行文件、标准规范、相关函复等, 字典：STUDY_DOCUMENT_TYPE）
     */
    @ApiModelProperty(value = "类型（政策法规、指导行文件、标准规范、相关函复等, 字典：STUDY_DOCUMENT_TYPE）")
    private Integer docType;

    /**
     * 上传人
     */
    @ApiModelProperty(value = "上传人")
    @Size(max = 200, message = "上传人最多输入128个字符")
    private String createUser;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

}
