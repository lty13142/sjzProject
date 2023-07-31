package com.sjz.governance.controller.event;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.sjz.governance.model.dto.event.AlarmEventFlowNodeDTO;
import com.sjz.governance.model.entity.event.AlarmEventFlowNode;
import com.sjz.governance.model.vo.event.AlarmEventFlowNodeVO;
import com.sjz.governance.service.event.AlarmEventFlowNodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 综合治理_告警事件流转节点Controller
 *
 * @author pengl
 * @date 2023-04-04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/alarmEventFlowNode")
@Api(tags = "综合治理_告警事件流转节点")
public class AlarmEventFlowNodeController extends BaseController {

    private final AlarmEventFlowNodeService alarmEventFlowNodeService;

    /**
     * 查询综合治理_告警事件流转节点列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询综合治理_告警事件流转节点列表")
    public RestResult<List<AlarmEventFlowNodeVO>> getList(AlarmEventFlowNodeDTO dto) {
        return RestResult.succeed(alarmEventFlowNodeService.findAlarmEventFlowNodeList(dto));
    }

}
