package com.crcm.cloud.start.websocket.config;

import com.crcm.cloud.start.websocket.components.GetHeaderParamInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @ClassName WebSocketStompConfig
 * @Description 基于stomp协议的websocket适配器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/18
 **/
@Slf4j
@RequiredArgsConstructor
public class WebSocketStompConfigAdapter implements WebSocketMessageBrokerConfigurer {
    private final GetHeaderParamInterceptor getHeaderParamInterceptor;
    private final MessageProperties messageProperties;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*")
                //允许跨域
                .setAllowedOrigins("*")
                .withSockJS() //使用sockJS
                // 使用sockJS 避免 Incompatibile SockJS! Main site uses: "1.5.0", the iframe: "1.0.0"
                // TODO 存在有些地区由于CDN加速的问题获取不到这个文件的bug
                .setClientLibraryUrl("https://cdn.jsdelivr.net/npm/sockjs-client@1.5/dist/sockjs.min.js");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //如果不使用rabbitmq作为消息代理,那么只需要暴露简单节点即可
        if (!messageProperties.isEnableMq()) {
            //在暴露的节点上，消息代理即将会处理 前缀为 /topic 和 /queue 的请求消息。（服务端给客户端推送消息使用)
            registry.enableSimpleBroker("/topic", "/queue");
            // 意思是客户端程序访问服务器，需带有/app 前缀，那么这些带有/app的消息就会匹配到@MessageMapping注解的方法上。（可客户端向服务端推送消息使用）
            registry.setApplicationDestinationPrefixes("/app");
            // 服务端指定给用户一对一推送消息，使用 sendToUser 方法时，会帮我们默认拼接上 /user，所以客户端也需要订阅相关的/user前缀的主题才能正常接收
            registry.setUserDestinationPrefix("/user");
        } else {
            // 使用RabbitMQ做为消息代理，替换默认的Simple Broker
            //定义了服务端接收地址的前缀，也即客户端给服务端发消息的地址前缀,@SendTo(XXX) 也可以重定向
            registry.setUserDestinationPrefix("/user"); //这是给sendToUser使用,前端订阅需要加上/user
            registry.setApplicationDestinationPrefixes("/app"); //这是给客户端推送消息到服务器使用 ，推送的接口加上/app
            // "STOMP broker relay"处理所有消息将消息发送到外部的消息代理
            registry.enableStompBrokerRelay("/exchange", "/topic", "/queue", "/amq/queue")
                    .setVirtualHost("JCChost") //对应自己rabbitmq里的虚拟host
                    .setRelayHost("localhost")
                    .setClientLogin("root")
                    .setClientPasscode("root")
                    .setSystemLogin("root")
                    .setSystemPasscode("root")
                    .setSystemHeartbeatSendInterval(5000)
                    .setSystemHeartbeatReceiveInterval(4000);
        }
    }

    /**
     * 采用自定义拦截器，获取connect时候传递的参数
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(getHeaderParamInterceptor);
    }

}
