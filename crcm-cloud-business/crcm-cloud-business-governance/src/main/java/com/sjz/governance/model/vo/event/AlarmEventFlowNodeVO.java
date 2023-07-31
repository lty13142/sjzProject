package com.sjz.governance.model.vo.event;

import com.baomidou.mybatisplus.annotation.*;
import com.crcm.cloud.start.data.mybatis.bean.BaseEntity;
import com.crcm.core.constant.DicConstant;
import com.sjz.governance.model.entity.event.AlarmEventFlowNode;
import com.sjz.governance.utils.UtilDic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 综合治理_告警事件流转节点对象VO
 *
 * @author pengl
 * @date 2023-04-04
 */
@Data
public class AlarmEventFlowNodeVO extends AlarmEventFlowNode {

    public String getDealNodeCh() {
        return UtilDic.getDictName(DicConstant.EVENT_DEAL_NODE.CODE, this.getDealNode());
    }

    public String getDealStatusCh() {
        return UtilDic.getDictName(DicConstant.NODE_DEAL_STATUS.CODE, this.getDealStatus());
    }
}
