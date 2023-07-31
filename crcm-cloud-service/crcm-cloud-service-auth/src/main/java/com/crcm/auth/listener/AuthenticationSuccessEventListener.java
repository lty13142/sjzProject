package com.crcm.auth.listener;

import cn.hutool.core.bean.BeanUtil;
import com.crcm.admin.api.dto.req.ReqLogSaveDTO;
import com.crcm.cloud.start.log.event.SysLogEvent;
import com.crcm.cloud.start.log.utils.SysLogUtils;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.utils.IPUtil;
import com.crcm.security.bean.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @ClassName AuthenticationSuccessEventListener
 * @Description 鉴权结束事件监听器，监听登陆成功和登陆失败事件
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/8/4
 **/
@Slf4j
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private DefaultTokenServices tokenService;
    private final SecurityContextRepository repo = new HttpSessionSecurityContextRepository();
    @Autowired
    private HttpServletRequest request;


    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        //这里的事件源除了登录事件（UsernamePasswordAuthenticationToken）还有可能是token验证事件源（OAuth2Authentication）
        if (!"org.springframework.security.authentication.UsernamePasswordAuthenticationToken".equals(event.getSource().getClass().getName())) {
            return;
        }

        //这里还有oAuth2的客户端认证的事件，需要做一个判断
        if (event.getAuthentication().getDetails() != null) {
            // 登录日志逻辑。。。
            try {
                //
                Authentication authentication = event.getAuthentication();
                // 只处理用户登录成功时间，避免client认证 成功事件进入处理
                if (authentication.getPrincipal() instanceof UserAccount) {
                    log.info("用户{}登录成功", authentication.getName());
                    ReqLogSaveDTO sysLog = SysLogUtils.getSysLog();
                    sysLog.setType(SystemConstant.LOG_TYPE.LOGIN);
                    UserAccount authUser = BeanUtil.copyProperties(authentication.getPrincipal(), UserAccount.class);
                    sysLog.setOperateId(authUser.getUserId());
                    sysLog.setOperateName(authUser.getUsername());
                    sysLog.setCreateTime(LocalDateTime.now());
                    // 发布事件
                    applicationContext.publishEvent(new SysLogEvent(sysLog));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 客户端认证成功
            Object principal = event.getAuthentication().getPrincipal();
            Map<String, Object> map = BeanUtil.beanToMap(principal);
            // 获取客户端名称
            String clientId = String.valueOf(map.get("username"));
        }
    }


    /**
     * 登陆鉴权错误事件处理
     *
     * @param event
     */
    @EventListener
    public void failureBadCredentialsEvent(AuthenticationFailureBadCredentialsEvent event) {
        log.info("-----------------插入用户登陆失败日志-----------------");
        if (event.getAuthentication().getDetails() != null) {
            String errorMessage = event.getException().getMessage();
            //插入用户登陆失败日志
            try {
                Authentication authentication = event.getAuthentication();
                Object details = authentication.getDetails();
                Map<String, Object> map = BeanUtil.beanToMap(details);
                // 获取登陆IP
                // 请求IP
                String ip = IPUtil.getRequestIp(request);
                // 请求地址
//                Ip2regionSearcher regionSearcher = SpringContextHolder.getBean(Ip2regionSearcher.class);
//                IpInfo ipInfo = regionSearcher.memorySearch(ip);
                log.warn("用户：{} 登录失败,描述信息：{},登录ip：{},登陆方式：{}", authentication.getPrincipal(), errorMessage, ip, map.get("grant_type"));
//                报错登陆失败日志
//                SysLog sysLog = SystemLogUtil.createSysLog(request, "登录失败");
//                sysLog.setType(SystemConstant.LOG_TYPE.LOGIN);
//                sysLog.setUserName(String.valueOf(authentication.getPrincipal()));
//                sysLog.setExDesc(errorMessage);
//                sysLog.setCreateTime(LocalDateTime.now());
//                sysLog.setCreateBy(String.valueOf(authentication.getPrincipal()));
//                // 发布事件
//                applicationContext.publishEvent(new SysLogEvent(sysLog));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
