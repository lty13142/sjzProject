package com.crcm.cloud.start.log.aspect;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.crcm.admin.api.dto.req.ReqLogSaveDTO;
import com.crcm.cloud.start.log.event.SysLogEvent;
import com.crcm.cloud.start.log.utils.SysLogUtils;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.utils.SpringContextHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName SysLogAspect
 * @Description 系统日志切面处理
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/22
 **/
@Aspect
@Slf4j
public class SysLogAspect {

    @Around("@annotation(sysLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, com.crcm.cloud.start.log.annation.SysLog sysLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);
        ReqLogSaveDTO logDTO = SysLogUtils.getSysLog();
        logDTO.setTitle(sysLog.title());
        logDTO.setServiceId(sysLog.serviceId());
        logDTO.setBusinessType(sysLog.businessType().ordinal());
        // 设置操作人
        this.setOperator(logDTO);
        // 发送异步日志事件
        Object obj = null;
        try {
            obj = point.proceed();
        } catch (Exception e) {
            logDTO.setType(SystemConstant.LOG_TYPE.ERROR);
            logDTO.setErrorMsg(StringUtils.substring(SysLogUtils.getStackTrace(e), 0, 2000));
            throw e;
        } finally {
            logDTO.setResponseData(JSON.toJSONString(obj));
            SpringContextHolder.publishEvent(new SysLogEvent(logDTO));
        }

        return obj;
    }

    /**
     * 设置操作人
     *
     * @param logDTO
     */
    private void setOperator(ReqLogSaveDTO logDTO) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal != null) {
                    String str = JSONUtil.toJsonStr(principal);
                    if (JSONUtil.isJson(str)) {
                        JSONObject jsonObject = JSONUtil.parseObj(str);
                        logDTO.setOperateId(jsonObject.getStr("userId"));
                        logDTO.setOperateName(jsonObject.getStr("username"));
                    } else {
                        logDTO.setOperateName(str);
                    }

                }
            }
        }
    }

}