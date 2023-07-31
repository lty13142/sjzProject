package com.sjz.partbuilding.model.vo;

import com.crcm.core.dto.TreeView;
import lombok.Data;

import java.io.Serializable;

@Data
public class TreeViewVo extends TreeView implements Serializable {
    private String userId;
}
