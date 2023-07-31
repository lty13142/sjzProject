package com.crcm.cloud.service.chat.service;

import com.alibaba.fastjson.JSONObject;
import com.crcm.cloud.service.chat.dto.UserLoginDTO;
import com.crcm.cloud.service.chat.dto.UserRegisterDTO;

/**
 * @ClassName UserService
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/29
 **/
public interface UserService {

    /**
     * 用户登录
     * @param dto
     * @return
     */
    JSONObject login(UserLoginDTO dto);

    /**
     * 用户注册
     * @param dto
     * @return
     */
    JSONObject register(UserRegisterDTO dto);


}
