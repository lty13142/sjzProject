package com.crcm.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.auth.model.entity.SysOauthClientDetails;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.exception.CustomException;

/**
 * @ClassName OauthClientDetailsService
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
public interface OauthClientDetailsService extends IService<SysOauthClientDetails> {
    /**
     * 查询（分页）
     *
     * @param page            QueryRequest
     * @param oauthClientDetails oauthClientDetails
     * @return IPage<OauthClientDetails>
     */
    PageT<SysOauthClientDetails> findOauthClientDetails(PageT page, SysOauthClientDetails oauthClientDetails);

    /**
     * 根据主键查询
     *
     * @param clientId clientId
     * @return OauthClientDetails
     */
    SysOauthClientDetails findById(String clientId);

    /**
     * 新增
     *
     * @param oauthClientDetails oauthClientDetails
     * @throws CustomException
     */
    void createOauthClientDetails(SysOauthClientDetails oauthClientDetails) throws CustomException;

    /**
     * 修改
     *
     * @param oauthClientDetails oauthClientDetails
     */
    void updateOauthClientDetails(SysOauthClientDetails oauthClientDetails);

    /**
     * 删除
     *
     * @param clientIds clientIds
     */
    void deleteOauthClientDetails(String clientIds);
}
