package com.zsgf.platform.service.provincial;

import com.zsgf.platform.dto.ProvincialDTO;

/**
 * @ClassName ProvincialPlatformService
 * @Description 省平台接口回流服务类
 * @Author GZL
 * @Date 2023/3/17 14:16
 * @Version 1.0
 **/
public interface ProvincialPlatformService {

    /**
     * 省平台企业信息回流
     *
     * @Author GZL
     * @Date 2023/3/17 16:16
     * @Param dto 条件
     **/
    void syncCompanyInfo(ProvincialDTO dto);

    /**
     * 省平台企业角色信息回流
     *
     * @Author GZL
     * @Date 2023/3/17 16:16
     * @Param dto 条件
     **/
    void syncCompanyRole(ProvincialDTO dto);

    /**
     * 省平台经营许可信息回流
     *
     * @Author GZL
     * @Date 2023/3/17 16:16
     * @Param dto 条件
     **/
    void syncBusinessLicense(ProvincialDTO dto);

    /**
     * 省平台废物名录信息回流
     *
     * @Author GZL
     * @Date 2023/3/20 9:22
     * @Param dto 条件
     **/
    void syncWasteList(ProvincialDTO dto);
}
