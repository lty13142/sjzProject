package com.zsgf.platform.service.impl.company;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.company.CompanyDeviceMapper;
import com.zsgf.platform.model.entity.company.CompanyDevice;
import com.zsgf.platform.service.company.CompanyDeviceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司设备信息Service业务层处理
 *
 * @author gzl
 * @date 2023-03-24
 */
@Service
public class CompanyDeviceServiceImpl extends ServiceImpl<CompanyDeviceMapper, CompanyDevice> implements CompanyDeviceService {


    /**
     * 新增公司设备信息
     *
     * @param companyDevice 公司设备信息
     * @return 结果
     */
    @Override
    public boolean saveCompanyDevice(CompanyDevice companyDevice) {
        return this.save(companyDevice);
    }

    /**
     * 修改公司设备信息
     *
     * @param companyDevice 公司设备信息
     * @return 结果
     */
    @Override
    public boolean updateCompanyDevice(CompanyDevice companyDevice) {
        return this.updateById(companyDevice);
    }

    /**
     * 删除公司设备信息信息
     *
     * @param id 公司设备信息ID
     * @return 结果
     */
    @Override
    public boolean deleteCompanyDevice(String id) {
        return this.removeById(id);
    }

    /**
     * 查询公司设备信息
     *
     * @param id 公司设备信息ID
     * @return 公司设备信息
     */
    @Override
    public CompanyDevice findCompanyDeviceById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询公司设备信息列表
     *
     * @param companyDevice 公司设备信息
     * @return 公司设备信息
     */
    @Override
    public List<CompanyDevice> findCompanyDeviceList(CompanyDevice companyDevice) {
        LambdaQueryWrapper<CompanyDevice> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询公司设备信息
     *
     * @param page          分页参数
     * @param companyDevice 公司设备信息
     * @return 公司设备信息
     */
    @Override
    public PageT<CompanyDevice> findCompanyDevicePage(PageT<CompanyDevice> page, CompanyDevice companyDevice) {
        LambdaQueryWrapper<CompanyDevice> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
