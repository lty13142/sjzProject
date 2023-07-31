package com.crcm.bpm.domain.dto.response;

import com.crcm.bpm.domain.dto.ProcessDto;
import com.crcm.bpm.domain.entity.ProcessDO;
import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/10/26 19:01 <br>
 * @Author: <a>bot</a>
 */
@Data
public class GetSortListResponseDto {

    /**
     * 流程类型
     */
    private String processType;

    /**
     * 流程类型value
     */
    private String processTypeValue;

    /**
     * 流程数据列表
     */
    private List<ProcessDto> processDOList;

}
