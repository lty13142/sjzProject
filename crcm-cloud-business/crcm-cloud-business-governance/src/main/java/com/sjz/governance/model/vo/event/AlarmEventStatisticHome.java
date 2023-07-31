package com.sjz.governance.model.vo.event;

import com.sjz.governance.utils.UtilSysArea;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Tianyu
 * @date 2023/4/7 17:40
 */
@Data
public class AlarmEventStatisticHome {

    /**
     * 排名
     */
    @ApiModelProperty(value = "排名")
    private String rank;

    /**
     * 村庄代码
     */
    @ApiModelProperty(value = "村庄代码")
    private String villageCode;

    /**
     * 村名
     */
    @ApiModelProperty(value = "村名")
    private String villageName;

    /**
     * 事件数量
     */
    @ApiModelProperty(value = "事件数量")
    private int eventNum;


}
