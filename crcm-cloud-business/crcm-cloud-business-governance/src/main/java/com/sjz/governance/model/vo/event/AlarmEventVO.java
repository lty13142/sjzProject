package com.sjz.governance.model.vo.event;

import com.crcm.core.constant.DicConstant;
import com.crcm.core.constant.SystemConstant;
import com.sjz.governance.model.entity.event.AlarmEvent;
import com.sjz.governance.model.vo.AreaVo;
import com.sjz.governance.utils.UtilDic;
import com.sjz.governance.utils.UtilSysArea;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 综合治理_告警事件对象
 *
 * @author pengl
 * @date 2023-04-04
 */
@Data
public class AlarmEventVO extends AlarmEvent {

    /**
     * 处理状态：2退回  1通过  0未处理
     */
    @ApiModelProperty(value = "处理状态：2退回  1通过  0未处理")
    private String dealStatus;

    /**
     * 月份
     */
    @ApiModelProperty(value = "月份")
    private String month;

    public String getDealStatusCh() {
        return UtilDic.getDictName(DicConstant.NODE_DEAL_STATUS.CODE, this.getDealStatus());
    }

    public String getEventTypeCh() {
        return UtilDic.getDictName(DicConstant.CAMERA_CAPTURE_TYPE.CODE, this.getEventType());
    }

    public String getDealNodeCh() {
        return UtilDic.getDictName(DicConstant.EVENT_DEAL_NODE.CODE, this.getDealNode());
    }

    public String getIsMisinformationCh() {
        return UtilDic.getDictName(SystemConstant.YES_OR_NO.CODE, this.getIsMisinformation());
    }

    public String getVillageCh(){
        return UtilSysArea.getVillageChById(this.getVillageId());
    }
}
