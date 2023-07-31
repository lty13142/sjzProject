package com.zsgf.platform.dto.sheets;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @ClassName EquipmentSheetUploadDTO
 * @Description 确认书上传DTO
 * @Author GZL
 * @Date 2023/3/30 10:08
 * @Version 1.0
 **/
@Data
public class EquipmentSheetUploadDTO {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 附件id
     */
    @ApiModelProperty(value = "附件id")
    @NotBlank(message = "附件id不能为空")
    @Size(max = 64, message = "附件id最多输入64个字符")
    private String fileId;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    @NotBlank(message = "附件名称不能为空")
    @Size(max = 1000, message = "附件名称最多输入1000个字符")
    private String fileName;
}
