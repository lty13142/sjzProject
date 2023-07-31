package com.crcm.cloud.start.websocket.components;

import com.alibaba.fastjson.JSONObject;
import com.crcm.cloud.start.websocket.constants.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * @ClassName ChatService
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/18
 **/
@Service
public class ChatService {
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;
    public static final String SEND_ALL = "SEND_ALL";


    public Boolean sendMsg(String msg) {
        try {
            JSONObject msgJson = JSONObject.parseObject(msg);
            // 群聊
            if (SEND_ALL.equals(msgJson.getString("to"))
                    && msgJson.getString("type").equals(MessageType.CHAT.getValue())) {
                simpMessageSendingOperations.convertAndSend("/topic/public", msgJson);

            } else if (msgJson.getString("to").equals(SEND_ALL)
                    && msgJson.getString("type").equals(MessageType.JOIN.getValue())) { // 加入聊天
                simpMessageSendingOperations.convertAndSend("/topic/public", msgJson);

            } else if (msgJson.getString("to").equals(SEND_ALL)
                    && msgJson.getString("type").equals(MessageType.LEAVE.getValue())) { // 离开聊天
                simpMessageSendingOperations.convertAndSend("/topic/public", msgJson);

            } else if (!msgJson.getString("to").equals(SEND_ALL)
                    && msgJson.getString("type").equals(MessageType.CHAT.getValue())) { // 单聊
                simpMessageSendingOperations.convertAndSendToUser(msgJson.getString("to"), "/topic/msg", msgJson);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
