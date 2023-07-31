package com.sjz.partbuilding.model.vo;

import com.sjz.partbuilding.model.entity.Honor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * This is description
 *
 * @authoryzw
 * @date 2020/09/30
 */
@Data
public class HonorVo extends Honor {

    //附件的详情
    private List<Map<String,String>> attachments;
    //公司名称
    private String comName;

    private String empName;

    private String userId;

}
