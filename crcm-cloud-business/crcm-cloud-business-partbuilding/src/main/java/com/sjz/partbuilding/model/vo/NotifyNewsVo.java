package com.sjz.partbuilding.model.vo;


import com.sjz.partbuilding.model.entity.Attachments;
import com.sjz.partbuilding.model.entity.NotifyNews;

import com.sjz.partbuilding.model.entity.KeyWord;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 新增通知通知扩展类
 * @author zzyt
 * @version 1.0
 * @date 2020/7/8 17:55
 */
@Data
public class NotifyNewsVo extends NotifyNews implements Serializable {
    /**
     * 封面路径
     */
    private String CoverCh;

    /**
     * 附件返回集
     */
    private List<Map<String,String>> attachmentsList;

    /**
     * 关键词返回集
     */
    private List<KeyWord> keyWordList;

}
