package com.crcm.bpm.domain.dto;

import com.crcm.bpm.domain.dto.response.UserTaskDTO;
import lombok.Builder;
import lombok.Data;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;

import java.io.Serializable;
import java.util.Map;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/11/23 14:18 <br>
 * @Author: <a>bot</a>
 */
@Data
@Builder
public class DoActionDto implements Serializable {

    ProcessCirculationDto processCirculationDto;

    UserTaskDTO userTaskDTO;

    TaskService taskService;

    RuntimeService runtimeService;

    Map<String, Object> actionMap;
}
