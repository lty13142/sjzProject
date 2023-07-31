package com.sjz.partbuilding.model.vo;

import com.sjz.partbuilding.model.entity.Leader;
import lombok.Data;

@Data
public class LeaderVo extends Leader {
    private String avatar;//头像

    private String name;

    private String label;

    private String facePic;
}
