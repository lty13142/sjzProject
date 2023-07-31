package com.crcm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.crcm.admin.mapper.AgreementMapper;
import com.crcm.admin.model.entity.Agreement;
import com.crcm.admin.service.AgreementService;

/**
 * 协议管理Service业务层处理
 * 
 * @author cb
 * @date 2023-06-28
 */
@Service
public class AgreementServiceImpl extends ServiceImpl<AgreementMapper, Agreement> implements AgreementService {

    

    /**
     * 新增协议管理
     * 
     * @param agreement 协议管理
     * @return 结果
     */
    @Override
    public boolean saveAgreement(Agreement agreement) {
        return this.save(agreement);
    }

    /**
     * 修改协议管理
     * 
     * @param agreement 协议管理
     * @return 结果
     */
    @Override
    public boolean updateAgreement(Agreement agreement) {
        return this.updateById(agreement);
    }

    /**
     * 删除协议管理信息
     * 
     * @param id 协议管理ID
     * @return 结果
     */
    @Override
    public boolean deleteAgreement(String id) {
        return this.removeById(id);
    }

    /**
     * 查询协议管理
     *
     * @param id 协议管理ID
     * @return 协议管理
     */
    @Override
    public Agreement findAgreementById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询协议管理列表
     *
     * @param agreement 协议管理
     * @return 协议管理
     */
    @Override
    public List<Agreement> findAgreementList(Agreement agreement) {
        LambdaQueryWrapper<Agreement> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询协议管理
     *
     * @param page 分页参数
     * @param agreement 协议管理
     * @return 协议管理
     */
    @Override
    public PageT<Agreement> findAgreementPage(PageT<Agreement> page, Agreement agreement) {
        LambdaQueryWrapper<Agreement> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }

	@Override
	public Agreement getLastByType(Agreement agreement) {
        return this.baseMapper.getLastByType(agreement);
	}

    @Override
    public List<Agreement> getListByType(Agreement agreement) {
        return this.baseMapper.getListByType(agreement);
    }
}
