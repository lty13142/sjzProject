package com.crcm.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import com.crcm.auth.mapper.OauthClientDetailsMapper;
import com.crcm.auth.model.Clients;
import com.crcm.auth.model.ClientsDTO;
import com.crcm.cloud.start.redis.service.RedisService;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.security.utils.BpwdEncoderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName RedisClientDetailsServiceImpl
 * @Description redis客户端管理器
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/29
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisClientDetailsService {

    private final OauthClientDetailsMapper oauthClientDetailsMapper;
    private final RedisService redisService;

    public void loadClientData() {
        long startTime = System.currentTimeMillis();

        List<ClientsDTO> list = oauthClientDetailsMapper.selectListClient();
        if (CollectionUtil.isNotEmpty(list)) {
            log.info("加载系统客户端,共{}个", list.size());
            redisService.del(SystemBaseConstants.OAUTH_CLIENT_DATA);
            list.forEach(client -> {
                client.setClientSecret(BpwdEncoderUtil.bCryptPassword(client.getClientSecret()));
                redisService.hset(SystemBaseConstants.OAUTH_CLIENT_DATA,
                        client.getClientId(), client);
                log.info("加载客户端:{}", client.getClientId());
            });
        }
        log.info("客户端加载完成，用时{}毫秒", System.currentTimeMillis() - startTime);
    }

    public Clients loadClientByClientId(String clientId) {
        Map map = (Map)redisService.hget(SystemBaseConstants.OAUTH_CLIENT_DATA, clientId);
        if (Objects.isNull(map)) {
            return null;
        }
        ClientsDTO clientsDTO = BeanUtil.mapToBean((Map) map, ClientsDTO.class, true, CopyOptions.create());
        Clients clients = BeanUtil.copyProperties(clientsDTO, Clients.class);
        clients.setRegisteredRedirectUri(clientsDTO.getRegisteredRedirectUris());
        clients.setAutoApproveScopes(clientsDTO.getAutoApproveScopes());
        return clients;
    }


    public void reloadClientByClientId(String clientId) {
        ClientsDTO clientDetails = oauthClientDetailsMapper.selectClientById(clientId);
        redisService.hset(SystemBaseConstants.OAUTH_CLIENT_DATA, clientDetails.getClientId(),
                BeanUtil.copyProperties(clientDetails, Clients.class));
    }

    public void removeRedisCache(String clientId) {
        redisService.hdel(SystemBaseConstants.OAUTH_CLIENT_DATA, clientId);
    }
}
