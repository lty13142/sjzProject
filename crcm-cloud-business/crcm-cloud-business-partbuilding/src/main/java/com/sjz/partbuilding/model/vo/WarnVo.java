package com.sjz.partbuilding.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WarnVo implements Serializable {
    private static final long serialVersionUID = 910318428120202723L;

    /**
     * 警告标志 0不是警告 1是警告
     */
    private String warn;

    /**
     * 警告消息
     */
    private String msg;
}
