package com.sjz.governance.model.vo.event;

import lombok.Data;

import java.util.List;

/**
 * @author Tianyu
 * @date 2023/4/11 14:48
 */
@Data
public class AlarmEventPageVo {

    //所有报警事件总数
    private int allNum;

    //按照类型区分
    private List<AlarmEventStatisticAnalysisByTypeVO> dataList;

}
