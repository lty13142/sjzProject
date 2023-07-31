package com.crcm.bpm.domain.dto;

import lombok.Data;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/11/15 16:29 <br>
 * @Author: <a>bot</a>
 */
@Data
public class CorrespondenceDto {

    /**
     * 数据回填，当前table对应属性
     */
    private String currentVModel;

    /**
     * 数据回填，当前table对应属性
     */
    private String currentLabel;

    /**
     * 数据回填，回填table对应属性
     */
    private String backFillVModel;

    /**
     * 数据回填，回填table对应属性
     */
    private String backFillLabel;
}
