package com.sjz.governance.model.vo.event;

import com.crcm.core.constant.DicConstant;
import com.crcm.core.constant.SystemConstant;
import com.sjz.governance.utils.UtilDic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 综合治理_告警事件统计分析对象
 *
 * @author pengl
 * @date 2023-04-06
 */
@Data
public class AlarmEventStatisticAnalysisVO {

    /**
     * 事件流转节点
     */
    private List<AlarmEventStatisticAnalysisByDealNodeVO> alarmEventStatisticAnalysisByDealNodeVOList;

    /**
     * 事件处理时间
     */
    private AlarmEventStatisticAnalysisByTimeVO alarmEventStatisticAnalysisByTimeVO;

    /**
     * 事件类型
     */
    private List<AlarmEventStatisticAnalysisByTypeVO> alarmEventStatisticAnalysisByTypeVOList;

    /**
     * 事件月度统计
     */
    private List<AlarmEventStatisticAnalysisByTypeMonthVO> alarmEventStatisticAnalysisByTypeMonthVOList;

}
