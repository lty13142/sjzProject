package com.sjz.partbuilding.model.vo;

import com.sjz.partbuilding.model.entity.Attachments;
import com.sjz.partbuilding.model.entity.WorkDeploy;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author rmc
 * @version 1.0
 * @date 2020/10/21 11:08
 */
@Data
public class WorkDeployVo extends WorkDeploy implements Serializable {

    /**
     * 下发组织名
     */
    private String orgName;

    /**
     * 超时情况
     */
    private String timeOutCh;

    /**
     * 完成情况
     */
    private Date completeTime;

    /**
     * 完成情况Ch
     */
    private String completeCh;

    /**
     * 附件返回集
     */
    private List<Attachments> attachmentsList;

//    /**
//     * 组织集
//     */
//    private List<Org> orgList;
}
