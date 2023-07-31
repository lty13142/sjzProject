package com.sjz.evaluations.model.dto;

import com.sjz.evaluations.model.entity.VillageExamine;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 村级考核新增接收实体
 *
 * @author rmc
 * @version 1.0
 * @date 2023/4/26 15:28
 */
@Data
@Accessors(chain = true)
public class VillageExamineSaveDTO extends VillageExamine {

    /**
     * 村级列表
     */
    private List<VillageExamine> villageExamineList;
}
