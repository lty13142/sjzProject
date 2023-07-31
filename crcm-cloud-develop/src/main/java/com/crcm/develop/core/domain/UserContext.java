package com.crcm.develop.core.domain;


import java.util.Objects;

/**
 * @ClassName UserContext
 * @Description 用户信息获取
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/6/12 10:30
 **/
public class UserContext {

    /**
     * 线程内部数据存储，对数据存储后，只有在线程中才可以获取到存储的数据，对于其他线程来说是无法获取到数据
     */
    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    /**
     * 获取当前操作的用户对象
     * @return 用户对象
     */
    public static UserInfo current() {
        return threadLocal.get();
    }

    /**
     * 获取当前操作的用户名
     * @return 用户名
     */
    public static String currentUserName() {
        UserInfo userInfo = threadLocal.get();
        if (!Objects.isNull(userInfo)) {
            return userInfo.getUsername();
        }
        return null;
    }

    /**
     * 设置当前的用户
     * @param userInfo
     */
    public static void setUserInfo(UserInfo userInfo) {
        threadLocal.set(userInfo);
    }
}
