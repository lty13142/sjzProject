package com.crcm.auth.controller;

import com.crcm.auth.model.ClientsDTO;
import com.crcm.auth.service.impl.CustomClientDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName ClientsController
 * @Description Client管理Controller
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/17
 **/
@RequiredArgsConstructor
@RestController
public class ClientsController {
    /**
     * 客户端Service
     */
    private final CustomClientDetailsServiceImpl customClientDetailsServiceImpl;

    /**
     * 新增客户端
     * @param clients 新增客户端内容
     * @return java.lang.Object
     */
    @PostMapping("oauth/client/insert")
    public Object insert(@Valid @RequestBody ClientsDTO clients) {
        return customClientDetailsServiceImpl.insert(clients);
    }

}
