package com.zsgf.platform.service.company;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.model.entity.company.CompanyDevice;

import java.util.List;

/**
 * 公司设备信息Service接口
 *
 * @author gzl
 * @date 2023-03-24
 */
public interface CompanyDeviceService extends IService<CompanyDevice> {

    /**
     * 新增公司设备信息
     *
     * @param companyDevice 公司设备信息
     * @return 结果
     */
    boolean saveCompanyDevice(CompanyDevice companyDevice);

    /**
     * 修改公司设备信息
     *
     * @param companyDevice 公司设备信息
     * @return 结果
     */
    boolean updateCompanyDevice(CompanyDevice companyDevice);

    /**
     * 删除公司设备信息信息
     *
     * @param id 公司设备信息ID
     * @return 结果
     */
    boolean deleteCompanyDevice(String id);

    /**
     * 查询公司设备信息
     *
     * @param id 公司设备信息ID
     * @return 公司设备信息
     */
    CompanyDevice findCompanyDeviceById(String id);

    /**
     * 查询公司设备信息列表
     *
     * @param companyDevice 公司设备信息
     * @return 公司设备信息集合
     */
    List<CompanyDevice> findCompanyDeviceList(CompanyDevice companyDevice);

    /**
     * 分页查询公司设备信息列表
     *
     * @param page          分页参数
     * @param companyDevice 公司设备信息
     * @return 公司设备信息集合
     */
    PageT<CompanyDevice> findCompanyDevicePage(PageT<CompanyDevice> page, CompanyDevice companyDevice);
}
