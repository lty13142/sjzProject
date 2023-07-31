package com.crcm.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.crcm.auth.mapper.OauthClientDetailsMapper;
import com.crcm.auth.model.Clients;
import com.crcm.auth.model.ClientsDTO;
import com.crcm.core.response.RestResult;
import com.crcm.security.constants.Oauth2ExceptionEnum;
import com.crcm.security.utils.BpwdEncoderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName CustomClientDetailsServiceImpl
 * @Description 自定义客户端Service，采用数据库和redis混合方式，提升性能
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/3/16
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomClientDetailsServiceImpl implements ClientDetailsService {
    /**
     * clientMapper
     */
    private final RedisClientDetailsService redisClientDetailsService;
    private final OauthClientDetailsMapper oauthClientDetailsMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if (clientId == null || clientId.trim().length() <= 0) {
            log.error(Oauth2ExceptionEnum.CLIENT_ID_EMPTY.getMsg());
            throw new ClientRegistrationException(Oauth2ExceptionEnum.CLIENT_ID_EMPTY.getMsg());
        }
        // 先从缓存中加载
        Clients client = redisClientDetailsService.loadClientByClientId(clientId);
        if (Objects.isNull(client)) {
            // 缓存中没有再从数据库加载查询客户端根据客户端ID
            ClientsDTO clientsDTO = oauthClientDetailsMapper.selectClientById(clientId);
            if (Objects.isNull(clientsDTO)) {
                log.error(Oauth2ExceptionEnum.CLIENT_NOT_FOUND.getMsg());
                throw new ClientRegistrationException(Oauth2ExceptionEnum.CLIENT_NOT_FOUND.getMsg());
            }
            client = BeanUtil.copyProperties(clientsDTO,Clients.class);
            client.setClientSecret(BpwdEncoderUtil.bCryptPassword(clientsDTO.getClientSecret()));
            client.setAutoApproveScopes(clientsDTO.getAutoApproveScopes());
            clientsDTO.setClientSecret(BpwdEncoderUtil.bCryptPassword(clientsDTO.getClientSecret()));
            // 重新加载client信息到缓存中
            redisClientDetailsService.reloadClientByClientId(clientId);
        }
        return client;

    }

    /**
     * 新增客户端
     *
     * @param clientsDto 客户端信息
     * @return java.lang.Object
     */
    public Object insert(ClientsDTO clientsDto) {
        String secret = clientsDto.getClientSecret();
        String secretEncode = new BCryptPasswordEncoder().encode(secret);
        clientsDto.setClientSecret(secretEncode);
        if (StringUtils.isBlank(clientsDto.getScope())) {
            clientsDto.setScope("all");
        }
        if (StringUtils.isBlank(clientsDto.getAuthorizedGrantTypes())) {
            clientsDto.setAuthorizedGrantTypes("authorization_code,password,refresh_token");
        }
        oauthClientDetailsMapper.insertClient(clientsDto);
        return RestResult.succeed("新增客户端成功");
    }
}
