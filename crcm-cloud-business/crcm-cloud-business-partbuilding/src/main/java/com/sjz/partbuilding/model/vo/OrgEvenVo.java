package com.sjz.partbuilding.model.vo;

import com.sjz.partbuilding.model.entity.Attachments;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 * @date 2020/11/4 21:54:31
 */
@Setter
@Getter
@ApiModel("支委会表")
public class OrgEvenVo implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "主题")
    private String theme;

    @ApiModelProperty(value = "活动记录id")
    private String notesIds;

    @ApiModelProperty(value = "活动记录附件")
    private List<Attachments> notesList;

    @ApiModelProperty(value = "第一张图片路径")
    private String filePath;

    @ApiModelProperty(value = "第一张图片名字")
    private String fileName;
}
