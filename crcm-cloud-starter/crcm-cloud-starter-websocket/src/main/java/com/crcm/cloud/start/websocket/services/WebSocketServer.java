package com.crcm.cloud.start.websocket.services;

import com.alibaba.fastjson.JSON;
import com.crcm.cloud.start.websocket.components.ChatMessage;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName WebSocketServer
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/26
 **/
@ServerEndpoint("/ws/{sid}")
public class WebSocketServer {

    /**
     * 记录当前在线连接数。AtomicInteger可实现线程安全的自增。
     */
    private static AtomicInteger onlineNum = new AtomicInteger();

    /**
     * JUC包的线程安全ConcurrentHashMap，用来存放所有WebSocketServer对象Session。
     */
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();


    //建立连接
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName) {
        sessionPools.put(userName, session);
        addOnlineCount();
        System.out.println(userName + "加入webSocket！当前人数为" + onlineNum);
        for (String key : sessionPools.keySet()) {
            Session sessionVar = sessionPools.get(key);
            try {
                if (key.equals(userName)) {
                    sendMessage(sessionVar, "欢迎： 用户" + userName);
                } else {
                    sendMessage(sessionVar, "用户" + userName + " 加入了服务器");
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 关闭连接时调用
     * @param userName
     */
    @OnClose
    public void onClose(@PathParam(value = "sid") String userName) {
        sessionPools.remove(userName);
        subOnlineCount();
        System.out.println("用户" + userName + "断开webSocket连接！当前人数为" + onlineNum);
        for (String key : sessionPools.keySet()) {
            Session sessionVar = sessionPools.get(key);
            try {
                sendMessage(sessionVar, "用户" + userName + " 退出了服务器");
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 收到客户端信息
     * @param messageStr
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String messageStr) throws IOException {
        //json转对象
        System.out.println(messageStr);
        ChatMessage message = JSON.parseObject(messageStr, ChatMessage.class);
        System.out.println(message);



        if (message.getTo() != null) {
            //单发
            Session session = sessionPools.get( message.getTo());
            Session session2 = sessionPools.get(message.getFrom());
            try {
                sendMessage(session, "用户" + message.getFrom()+ "  向你发送了消息：" + message.getContent());
                sendMessage(session2, "我向用户" +  message.getTo() + "  发送了一条消息：" + message.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //群发广播
            for (String key : sessionPools.keySet()) {
                Session session = sessionPools.get(key);
                try {
                    if (key.equals(message.getFrom())) {
                        sendMessage(session, "我群发了一条消息：" + message.getContent());
                    } else {
                        sendMessage(session, "用户" + message.getFrom() + "  群发消息：" + message.getContent());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }

    }

    /**
     * 发生错误时调用
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("发生错误");
        throwable.printStackTrace();
    }


    /**
     * 实际发送消息
     * @param session
     * @param message
     * @throws IOException
     */
    public void sendMessage(Session session, String message) throws IOException {
        if (session != null) {
            synchronized (session) {
                session.getBasicRemote().sendText(message);
            }
        }
    }

    /**
     * 在线人数+1
     */
    public static void addOnlineCount() {
        onlineNum.incrementAndGet();
    }

    /**
     * 在线人数-1
     */
    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }

}