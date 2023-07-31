package com.crcm.bpm.controller.api;


import com.crcm.core.response.RestResult;
import com.crcm.bpm.core.handler.TaskHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


/**
 * <p>
 * 任务处理人相关
 * </p>
 *
 * @author liuxz
 * @since 2019-08-20
 */
@ApiIgnore
@RestController
@RequestMapping("api/flow/user")
@Api(value = "FlowUser", tags = {"任务处理人"})
@Slf4j
public class FlowUserController {

    @Autowired
    private TaskHandler taskHandler;

    /**
     * 执行流程任务
     */
    @RequestMapping(value = "/transferTask", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "任务移交", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID（接受移交的用户）", required = true, dataType = "String")
    })
    public RestResult<Task> transferTask(String taskId, String userId) throws Exception {
        taskHandler.setAssignee(taskId,userId);
        return RestResult.succeed();
    }
}

