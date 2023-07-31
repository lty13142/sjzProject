package com.sjz.evaluations.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yzw
 * @data 2023/4/6
 * @apiNote
 */
@Data
public class AttachmentVo implements Serializable {

    private String url;
    private String name;
}
