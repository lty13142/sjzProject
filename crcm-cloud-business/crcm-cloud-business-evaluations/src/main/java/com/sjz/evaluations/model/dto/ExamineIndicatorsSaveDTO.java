package com.sjz.evaluations.model.dto;

import com.sjz.evaluations.model.entity.ExamineIndicators;
import com.sjz.evaluations.model.entity.RegionExamine;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 指标新增实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/25 16:33
 */
@Data
@Accessors(chain = true)
public class ExamineIndicatorsSaveDTO extends ExamineIndicators {

    /**
     * 管区定性定量新增列表
     */
    @NotNull(message = "管区定性定量列表不能为空")
    private List<RegionExamine> regionExamineList;
}
