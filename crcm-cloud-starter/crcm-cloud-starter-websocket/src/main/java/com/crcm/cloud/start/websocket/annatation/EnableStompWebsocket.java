package com.crcm.cloud.start.websocket.annatation;

import com.crcm.cloud.start.websocket.config.MessageProperties;
import com.crcm.cloud.start.websocket.config.ProjectRabbitConfig;
import com.crcm.cloud.start.websocket.config.WebSocketStompConfigAdapter;
import org.springframework.context.annotation.Import;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.lang.annotation.*;

/**
 * @ClassName EnableProjectWebsocket
 * @Description 启动基于stomp协议的websocket
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/18
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableWebSocketMessageBroker
@Import({WebSocketStompConfigAdapter.class,MessageProperties.class,ProjectRabbitConfig.class})
public @interface EnableStompWebsocket {
}
