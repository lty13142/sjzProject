package com.crcm.cloud.service.chat.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crcm.cloud.service.chat.config.OpenImConfig;
import com.crcm.cloud.service.chat.dto.UserLoginDTO;
import com.crcm.cloud.service.chat.dto.UserRegisterDTO;
import com.crcm.cloud.service.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/29
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final OpenImConfig openImConfig;

    @Override
    public JSONObject login(UserLoginDTO dto) {
        String url = openImConfig.getServerHost() + "/auth/user_token";
        HttpRequest request = HttpUtil.createPost(url);
        request.body(JSON.toJSONString(dto));
        request.header("content-type",MediaType.APPLICATION_JSON.getType());
        HttpResponse response = request.execute();
        return JSON.parseObject(response.body());
    }

    @Override
    public JSONObject register(UserRegisterDTO dto) {
        String url = openImConfig.getServerHost() + "/auth/user_register";
        HttpRequest request = HttpUtil.createPost(url);
        request.body(JSON.toJSONString(dto));
        request.header("content-type",MediaType.APPLICATION_JSON.getType());
        HttpResponse response = request.execute();
        return JSON.parseObject(response.body());
    }



//    private JSONObject postForData(String url, Map<String, String> params) {
//        RestTemplate client = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        //执行HTTP请求
//        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
//        ResponseEntity<JSONObject> response = client.exchange(url, HttpMethod.POST, , JSONObject.class);
//        return response.getBody();
//    }
}
