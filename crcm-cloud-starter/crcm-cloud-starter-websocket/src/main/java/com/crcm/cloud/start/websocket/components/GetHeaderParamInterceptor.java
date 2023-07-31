package com.crcm.cloud.start.websocket.components;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

/**
 * @ClassName GetHeaderParamInterceptor
 * @Description socket消息请求头消信息截器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/18
 **/
@Component
public class GetHeaderParamInterceptor  extends ChannelInterceptorAdapter {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
            if (raw instanceof Map) {
                //取出客户端携带的参数
                Object name = ((Map) raw).get("username");
                System.out.println(name);
                if (name instanceof LinkedList) {
                    // 设置当前访问的认证用户
                    accessor.setUser(new UserPrincipal(((LinkedList) name).get(0).toString()));
                }
            }
        }
        return message;
    }

}
