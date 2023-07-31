package com.sjz.partbuilding.model.vo;

import com.sjz.partbuilding.model.entity.Attachments;
import com.sjz.partbuilding.model.entity.OrgEvent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Setter
@Getter
@ApiModel("组织活动表VO")
public class OrgEventVo extends OrgEvent {

    @ApiModelProperty(value = "党课材料附件")
    private List<Map<String,String>> materialsList;

    @ApiModelProperty(value = "活动记录附件")
    private List<Map<String,String>> notesList;

    @ApiModelProperty(value = "时间范围查询结束时间")
    private String endTime;

    @ApiModelProperty(value = "出席人员名单，逗号分隔")
    private String presentNames;

    @ApiModelProperty(value = "列席人员名单，逗号分隔")
    private String attendNames;

    @ApiModelProperty(value = "缺席人员名单，逗号分隔")
    private String absenceNames;
}
