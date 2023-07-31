package com.sjz.partbuilding.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class PersonTreeVo {
    private String label;
    private List children;
}
