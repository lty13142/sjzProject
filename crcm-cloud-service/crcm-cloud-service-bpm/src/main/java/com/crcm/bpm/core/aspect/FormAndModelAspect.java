package com.crcm.bpm.core.aspect;

import com.alibaba.fastjson.JSONObject;
import com.crcm.bpm.core.annotation.MyLog;
import com.crcm.bpm.core.constant.MyLogType;
import com.crcm.bpm.domain.entity.FormDO;
import com.crcm.bpm.domain.entity.FormUseLogDO;
import com.crcm.bpm.domain.entity.ModelDO;
import com.crcm.bpm.domain.entity.ModelUseLogDO;
import com.crcm.bpm.service.FormService;
import com.crcm.bpm.service.FormUseLogService;
import com.crcm.bpm.service.ModelService;
import com.crcm.bpm.service.ModelUseLogService;
import com.crcm.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * TODO
 *
 * @Description: <br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020/11/11 15:05 <br>
 * @Author: <a>bot</a>
 */
@Slf4j
@Aspect
@Order(value = 99)
@Component
public class FormAndModelAspect {

    private static final ThreadLocal threadLocal = new ThreadLocal();

    @Autowired
    private FormUseLogService formUseLogService;

    @Autowired
    private ModelUseLogService modelUseLogService;

    @Autowired
    private FormService formService;

    @Autowired
    private ModelService modelService;

    @Pointcut(value = "@annotation(myLog)", argNames = "myLog")
    public void pointCut(MyLog myLog) {
    }

    @Before(value = "pointCut(myLog)", argNames = "joinPoint, myLog")
    public void beforeAdvice(JoinPoint joinPoint, MyLog myLog) {
        try {
            Object[] args = joinPoint.getArgs();
            Object object = args[0];
            String businessId = getBusinessId(object);
            HttpServletRequest request = (HttpServletRequest) args[1];
            String uri = request.getRequestURI();
            String ip = getLocalIp(request);
            String jsonData = null;
            if (MyLogType.FORM.equals(myLog.type())) {
                FormDO formDO = formService.getById(businessId);
                if (formDO != null) {
                    jsonData = JSONObject.toJSONString(formDO);
                }
                FormUseLogDO formUseLogDO = new FormUseLogDO(null, uri, myLog.value(), SecurityUtil.getCurrentUserId(),
                        SecurityUtil.getCurrentUsername(), jsonData, null, ip, null);
                threadLocal.set(formUseLogDO);
            } else {
                ModelDO modelDO = modelService.getById(businessId);
                if (modelDO != null) {
                    jsonData = JSONObject.toJSONString(modelDO);
                }
                ModelUseLogDO modelUseLogDO = new ModelUseLogDO(null, uri, myLog.value(), SecurityUtil.getCurrentUserId(),
                        SecurityUtil.getCurrentUsername(), jsonData, null, ip, null);
                threadLocal.set(modelUseLogDO);
            }
        } catch (Exception e) {
            log.error("FormAndModelAspect----beforeAdvice:执行失败，原因{}", e);
        }

    }

    private String getBusinessId(Object object) {
        String businessId = null;
        try {
            String json = JSONObject.toJSONString(object);
            Map<String, Object> map = JSONObject.parseObject(json, Map.class);
            if (map.get("id") != null) {
                businessId = map.get("id").toString();
            } else if (map.get("modelId") != null) {
                businessId = map.get("modelId").toString();
            } else if (map.get("processId") != null) {
                businessId = map.get("processId").toString();
            }
        } catch (Exception e) {
            businessId = object.toString();
        }
        return businessId;
    }

    @AfterThrowing(value = "pointCut(myLog)", argNames = "joinPoint, myLog")
    public void afterThrowing(JoinPoint joinPoint, MyLog myLog) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[1];
        log.debug(request.getRequestURI() + "执行出错");
    }

    @AfterReturning(value = "pointCut(myLog)", argNames = "joinPoint, myLog")
    public void afterAdvice(JoinPoint joinPoint, MyLog myLog) {
        try {
            Object[] args = joinPoint.getArgs();
            Object object = args[0];
            String businessId = getBusinessId(object);
            String newJsonData = null;
            if (MyLogType.FORM.equals(myLog.type())) {
                FormDO formDO = formService.getById(businessId);
                if (formDO != null) {
                    newJsonData = JSONObject.toJSONString(formDO);
                }
                FormUseLogDO formUseLogDO = (FormUseLogDO) threadLocal.get();
                formUseLogDO.setNewData(newJsonData);
                formUseLogDO.setBusinessId(businessId);
                formUseLogService.save(formUseLogDO);
            } else {
                ModelDO modelDO = modelService.getById(businessId);
                if (modelDO != null) {
                    newJsonData = JSONObject.toJSONString(modelDO);
                }
                ModelUseLogDO modelUseLogDO = (ModelUseLogDO) threadLocal.get();
                modelUseLogDO.setNewData(newJsonData);
                modelUseLogDO.setBusinessId(businessId);
                modelUseLogService.save(modelUseLogDO);
            }
        } catch (Exception e) {
            log.error("FormAndModelAspect----afterAdvice:执行失败，原因{}", e);
        }

    }


    /**
     * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
     *
     * @param request
     * @return ip
     */
    public static String getLocalIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if (forwarded != null) {
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }
}
