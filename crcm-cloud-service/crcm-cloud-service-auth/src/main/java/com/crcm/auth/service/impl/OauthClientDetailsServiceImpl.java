package com.crcm.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.auth.mapper.OauthClientDetailsMapper;
import com.crcm.auth.model.entity.SysOauthClientDetails;
import com.crcm.auth.service.OauthClientDetailsService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import com.crcm.security.service.RedisClientDetailsService;

/**
 * @ClassName OauthClientDetailsServiceImpl
 * @Description oauth2 Client 注册实现
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, SysOauthClientDetails> implements OauthClientDetailsService {

    private final RedisClientDetailsService redisClientDetailsService;

    @Override
    public PageT<SysOauthClientDetails> findOauthClientDetails(PageT page, SysOauthClientDetails oauthClientDetails) {
        LambdaQueryWrapper<SysOauthClientDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(oauthClientDetails.getClientId()), SysOauthClientDetails::getClientId, oauthClientDetails.getClientId());
        return this.page(page, queryWrapper);
    }

    @Override
    public SysOauthClientDetails findById(String clientId) {
        return this.getById(clientId);
    }

    @Override
    public void createOauthClientDetails(SysOauthClientDetails oauthClientDetails) throws CustomException {
        SysOauthClientDetails byId = this.getById(oauthClientDetails.getClientId());
        if (byId != null) {
            throw new CustomException("该Client已存在");
        }
        oauthClientDetails.setClientSecret(oauthClientDetails.getClientSecret());
        boolean saved = this.save(oauthClientDetails);
        if (saved) {
            log.info("缓存Client -> {}", oauthClientDetails);
            this.redisClientDetailsService.loadClientByClientId(oauthClientDetails.getClientId());
        }
    }

    @Override
    public void updateOauthClientDetails(SysOauthClientDetails oauthClientDetails) {
        boolean updated = this.updateById(oauthClientDetails);
        if (updated) {
            log.info("更新Client -> {}", oauthClientDetails);
            this.redisClientDetailsService.reloadClientByClientId(oauthClientDetails.getClientId());
        }
    }

    @Override
    public void deleteOauthClientDetails(String clientId) {
        boolean removed = this.removeById(clientId);
        if (removed) {
            log.info("删除ClientId为({})的Client", clientId);
            this.redisClientDetailsService.removeRedisCache(clientId);
        }
    }
}
