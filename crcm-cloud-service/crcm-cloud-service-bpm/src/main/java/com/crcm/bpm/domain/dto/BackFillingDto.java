package com.crcm.bpm.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/11/15 16:25 <br>
 * @Author: <a>bot</a>
 */
@Data
public class BackFillingDto {

    /**
     * 数据回填tableId
     */
    private String tableVModel;

    /**
     * 数据回填tableId
     */
    private String componentName;

    /**
     * 数据回填table对应属性
     */
    private List<CorrespondenceDto> correspondence;

}
