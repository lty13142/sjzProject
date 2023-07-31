package com.sjz.evaluations.model.vo;

import com.crcm.core.dto.TreeView;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 考核结果表格返回实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/27 17:18
 */
@Data
@Accessors(chain = true)
public class ExamineResultTableVO implements Serializable {

    /**
     * 表头
     */
    private List<TreeView> title;

    /**
     * 表格数据，可以把表格数据理解成二维数组形成的矩阵
     */
    private List<LinkedHashMap<String, String>> tableData;

    /**
     * 表格指标数据（行数必须和表格数据相同）
     */
    private List<ExamineResultInfoVO> indicatorsList;

    /**
     * 完成率
     */
    private List<List<String>> completionRateList;


}
