package com.sjz.governance.task;

import com.sjz.governance.utils.recognition.RecognitionUtils;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName identifyResultsJob
 * @Description 智能识别定时任务
 * @Author GZL
 * @Date 2023/4/10 15:59
 * @Version 1.0
 **/
@Slf4j
@Component
public class IdentifyResultsJob {

    @XxlJob("identifyResultsHandle")
    public void identifyResultsHandle() {
        RecognitionUtils.intelligentRecognition(Boolean.FALSE);
        XxlJobHelper.log("执行智能识别结束！！！");
    }
}
