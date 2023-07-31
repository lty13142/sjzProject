package com.crcm.cloud.start.file.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("附件信息返回实体")
public class FileVo implements Serializable {
    private static final long serialVersionUID = 910318428120202723L;

    @ApiModelProperty(value = "附件ID")
    private String id;
    @ApiModelProperty(value = "附件路径")
    private String path;
    @ApiModelProperty(value = "附件名称")
    private String fileName;
}
