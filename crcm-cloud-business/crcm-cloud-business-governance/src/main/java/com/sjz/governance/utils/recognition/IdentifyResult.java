package com.sjz.governance.utils.recognition;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @ClassName IdentifyResult
 * @Description 识别结果
 * @Author GZL
 * @Date 2023/4/6 11:00
 * @Version 1.0
 **/

@Data
@EqualsAndHashCode()
public class IdentifyResult {


    /**
     * 摄像头
     */
    private String cameraName;
    /**
     * 摄像头标识
     */
    private String cameraCode;
    /**
     * 事发时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date eventTime;
    /**
     * 事发地点
     */
    private String eventAddr;
    /**
     * 识别模型标识
     */
    private String modelCode;
    /**
     * 识别模型名称
     */
    private String modelName;
    /**
     * 事件快照
     */
    private String picUrl;
    /**
     * 最大识别率
     */
    private float recognitionRate;
    /**
     * AI识别返回结果
     */
    private String result;

    /**
     * AI识别返回结果标记
     */
    private boolean resultFlag;

}

