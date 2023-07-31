package com.crcm.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.auth.model.ClientsDTO;
import com.crcm.auth.model.entity.SysOauthClientDetails;

import java.util.List;

/**
 * @ClassName OauthClientDetailsMapper
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
public interface OauthClientDetailsMapper extends BaseMapper<SysOauthClientDetails>{

    /**
     * 查询客户端根据客户端ID
     * @param clientId 客户端ID
     * @return org.surge.authorization.model.Clients
     * @author qipp
     * @date 2020/1/21 16:15
     */
    ClientsDTO selectClientById(String clientId);

    /**
     * 新增客户端
     * @param clientsDto 内容
     * @return int
     * @author qipp
     * @date 2020/1/21 16:15
     */
    int insertClient(ClientsDTO clientsDto);

    /**
     * 查询所有客户端
     * @return
     */
    List<ClientsDTO> selectListClient();
}
