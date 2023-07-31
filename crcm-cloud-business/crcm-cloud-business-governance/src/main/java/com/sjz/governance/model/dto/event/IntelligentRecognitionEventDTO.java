package com.sjz.governance.model.dto.event;

import com.crcm.core.dto.QueryDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName IntelligentRecognitionEventDTO
 * @Description 智能识别查询DTO
 * @Author GZL
 * @Date 2023/4/9 14:47
 * @Version 1.0
 **/
@Getter
@Setter
public class IntelligentRecognitionEventDTO extends QueryDTO {

    /**
     * 事件类型
     */
    @ApiModelProperty(value = "事件类型")
    private String eventType;

    /**
     * 监控id
     */
    @ApiModelProperty(value = "监控id")
    private String cameraId;
}
