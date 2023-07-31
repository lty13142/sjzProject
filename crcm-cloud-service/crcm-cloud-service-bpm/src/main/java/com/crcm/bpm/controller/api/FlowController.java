package com.crcm.bpm.controller.api;

import com.crcm.core.response.RestResult;
import com.crcm.bpm.core.domain.FlowRequestBean;
import com.crcm.bpm.service.api.FlowService;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import org.flowable.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName FlowController
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/8/13/15:49
 **/
@RestController
@RequestMapping("/api/flow")
public class FlowController {

    @Resource
    private RepositoryService repositoryService;

    @Autowired
    private FlowService flowService;

    /**
     * 启动流程
     * @param flowRequestBean
     * @return
     */
    @PostMapping(value = "/start")
    @ApiOperation(value = "启动工作流")
    public RestResult start(@RequestBody FlowRequestBean flowRequestBean){
        // 流程定义key
        String procDefKey= flowRequestBean.getProcDefKey();
        // 业务ID
        String bizKey= flowRequestBean.getBizKey();
        // 意见
        String comment= flowRequestBean.getComment();
        // 标题
        String title= flowRequestBean.getTitle();
        // 参数
        Map map= flowRequestBean.getMap();
        //流程启动人
        UserAccount currentUser = SecurityUtil.getCurrentUser();
        String applyUser= currentUser.getUsername();
        String tenantId = currentUser.getTenantId();
        String processId = flowService.startProcess(procDefKey, bizKey, title, comment, applyUser, tenantId, map);
        return RestResult.succeed(processId);
    }


}
