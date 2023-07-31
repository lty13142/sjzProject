package com.crcm.cloud.start.websocket.constants;

/**
 * @ClassName MessageType
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/18
 **/
public enum MessageType {
    CHAT(1,"聊天"),
    JOIN(2,"加入"),
    LEAVE(3,"离开"),
    NOTICE(4,"公告"),
    NOTIFY(5,"通知");


    private int value;
    private String name;

    MessageType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
