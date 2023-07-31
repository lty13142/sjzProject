package com.crcm.cloud.start.websocket.annatation;

import com.crcm.cloud.start.websocket.config.WebSocketConfig;
import com.crcm.cloud.start.websocket.services.WebSocketServer;
import org.springframework.context.annotation.Import;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.lang.annotation.*;

/**
 * @ClassName EnableWebSocket
 * @Description 启用websocket
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/26
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableWebSocketMessageBroker
@Import({WebSocketConfig.class, WebSocketServer.class})
public @interface EnableWebSocket {
}
