package com.sjz.partbuilding.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rmc
 * @version 1.0
 * @date 2020/10/23 16:53
 */
@Data
public class LoginKeyWordVo implements Serializable {

    private String id;

    /**
     * 新闻通知or公告
     */
    private String type;
}
