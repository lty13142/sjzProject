package com.crcm.cloud.start.job.annation;

import com.crcm.cloud.start.job.XxlJobAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableProjectJob
 * @Description 激活xxl-job
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/22
 **/
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ XxlJobAutoConfiguration.class })
public @interface EnableXxlJob {

}

