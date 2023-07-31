package com.crcm.admin.service;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.crcm.admin.model.entity.Agreement;

/**
 * 协议管理Service接口
 * 
 * @author cb
 * @date 2023-06-28
 */
public interface AgreementService extends IService<Agreement>{

    /**
     * 新增协议管理
     * 
     * @param agreement 协议管理
     * @return 结果
     */
    boolean saveAgreement(Agreement agreement);

    /**
     * 修改协议管理
     * 
     * @param agreement 协议管理
     * @return 结果
     */
    boolean updateAgreement(Agreement agreement);

    /**
     * 删除协议管理信息
     * 
     * @param id 协议管理ID
     * @return 结果
     */
    boolean deleteAgreement(String id);

    /**
     * 查询协议管理
     *
     * @param id 协议管理ID
     * @return 协议管理
     */
    Agreement findAgreementById(String id);

    /**
     * 查询协议管理列表
     *
     * @param agreement 协议管理
     * @return 协议管理集合
     */
    List<Agreement> findAgreementList(Agreement agreement);

    /**
     * 分页查询协议管理列表
     * @param page 分页参数
     * @param agreement 协议管理
     * @return 协议管理集合
     */
    PageT<Agreement> findAgreementPage(PageT<Agreement> page, Agreement agreement);

    /**
     * 通过类型获取最后更新协议管理
     * @param agreement
     * @return
     */
    Agreement getLastByType(Agreement agreement);

    /**
     * 通过类型获取协议管理列表
     * @param agreement
     * @return
     */
    List<Agreement> getListByType(Agreement agreement);
}
