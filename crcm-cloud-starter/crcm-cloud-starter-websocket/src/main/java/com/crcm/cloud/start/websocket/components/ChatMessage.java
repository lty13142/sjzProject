package com.crcm.cloud.start.websocket.components;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ChatMessage
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/18
 **/
@Data
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 5557544874651118056L;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息类型
     */
    private Integer type;
    /**
     * 消息来源ID
     */
    private String from;
    /**
     * 发送消息给ID
     */
    private String to;
    /**
     * 地址重定向
     */
    private String redirect;
    /**
     * 发送时间毫秒数
     */
    private long timestamp = System.currentTimeMillis();
}
