package com.sjz.partbuilding.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author rmc
 * @version 1.0
 * @date 2020/10/10 23:07
 */
@Data
public class PostTreeVo {

    private String id;

    private String name;

    private String postName;

    private String pid;

    private String facePic;

    private List<PostTreeVo> children;
}
