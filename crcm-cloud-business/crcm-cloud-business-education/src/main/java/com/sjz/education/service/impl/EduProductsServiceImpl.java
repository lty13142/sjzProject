package com.sjz.education.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.exception.CustomException;
import com.sjz.education.mapper.EduExchangeRecordMapper;
import com.sjz.education.mapper.EduPersonnelManagementMapper;
import com.sjz.education.mapper.EduProductsMapper;
import com.sjz.education.model.entity.EduPersonnelManagement;
import com.sjz.education.service.EduPersonnelManagementService;
import com.sjz.education.service.EduProductsService;
import com.sjz.education.model.entity.EduProducts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SSSCCCC
 * @description 针对表【edu_products(积分可兑换商品)】的数据库操作Service实现
 * @createDate 2023-04-03 14:35:16
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EduProductsServiceImpl extends ServiceImpl<EduProductsMapper, EduProducts>
        implements EduProductsService {

    private static final String ERR_MSG_NAME = "商品名称已存在";
    private static final String ERR_MSG_STATUS1 = "商品已下架";
    private static final String ERR_MSG_STATUS2 = "商品已上架";

    @Autowired
    private EduPersonnelManagementMapper managementMapper;

    /**
     * 新增商品
     *
     * @param t
     * @return
     */
    @Override
    public boolean add(EduProducts t) {
        //获取当前登录用户的村
        AuthUser user = SecurityUtil.getCurrentUser();
        EduPersonnelManagement currentUser = managementMapper.selectById(user.getUserId());

        LambdaQueryWrapper<EduProducts> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduProducts::getProductName, t.getProductName());
        wrapper.eq(EduProducts::getProductType, t.getProductType());
        Integer count = this.baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new CustomException(ERR_MSG_NAME);
        }
        t.setStatus(0);
        t.setVillageName(currentUser.getVillageName());
        //兑换次数及兑换数量初始化
        t.setExchangeNumber(0);
        t.setExchangeCount(0);
        return this.save(t);
    }

    /**
     * 修改商品
     *
     * @param t
     * @return
     */
    @Override
    public boolean edit(EduProducts t) {
        LambdaQueryWrapper<EduProducts> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduProducts::getProductName, t.getProductName());
        wrapper.eq(EduProducts::getProductType, t.getProductType());
        EduProducts products = this.baseMapper.selectOne(wrapper);
        if (ObjectUtil.isNotEmpty(products) && !products.getId().equals(t.getId())) {
            throw new CustomException(ERR_MSG_NAME);
        }
        return this.updateById(t);
    }

    /**
     * 根据id进行删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(String id) {
        return this.removeById(id);
    }

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean batchDelete(List<String> ids) {
        LambdaQueryWrapper<EduProducts> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(EduProducts::getId, ids);
        List<EduProducts> eduProducts = this.baseMapper.selectList(wrapper);
        for (EduProducts eduProduct : eduProducts) {
            if (!eduProduct.getStatus().equals(0)) {
                throw new CustomException(ERR_MSG_STATUS2);
            }
        }
        return this.removeByIds(ids);
    }


    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public EduProducts findById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 根据ids查找
     *
     * @param ids
     * @return
     */
    @Override
    public List<EduProducts> findByIds(List<String> ids) {
        LambdaQueryWrapper<EduProducts> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(EduProducts::getId, ids);
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 获取商品列表
     *
     * @param t
     * @return
     */
    @Override
    public List<EduProducts> getList(EduProducts t) {
        LambdaQueryWrapper<EduProducts> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(t.getStatus()), EduProducts::getStatus, t.getStatus());
        wrapper.like(ObjectUtil.isNotEmpty(t.getProductName()), EduProducts::getProductName, t.getProductName());
        wrapper.eq(ObjectUtil.isNotEmpty(t.getProductType()), EduProducts::getProductType, t.getProductType());
        wrapper.ge(ObjectUtil.isNotEmpty(t.getStartPoints()), EduProducts::getNeedPoints, t.getStartPoints());
        wrapper.le(ObjectUtil.isNotEmpty(t.getEndPoints()), EduProducts::getNeedPoints, t.getEndPoints());
        wrapper.orderByDesc(EduProducts::getCreateTime);
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 获取商品分页
     *
     * @param t,page分页参数
     * @return
     */
    @Override
    public IPage<EduProducts> getPage(PageT page, EduProducts t) {
        //获取当前登录用户的村
        AuthUser user = SecurityUtil.getCurrentUser();
        EduPersonnelManagement currentUser = managementMapper.selectById(user.getUserId());

        LambdaQueryWrapper<EduProducts> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduProducts::getVillageName,currentUser.getVillageName());
        wrapper.eq(ObjectUtil.isNotEmpty(t.getStatus()), EduProducts::getStatus, t.getStatus());
        wrapper.like(ObjectUtil.isNotEmpty(t.getProductName()), EduProducts::getProductName, t.getProductName());
        wrapper.eq(ObjectUtil.isNotEmpty(t.getProductType()), EduProducts::getProductType, t.getProductType());
        wrapper.ge(ObjectUtil.isNotEmpty(t.getStartPoints()), EduProducts::getNeedPoints, t.getStartPoints());
        wrapper.le(ObjectUtil.isNotEmpty(t.getEndPoints()), EduProducts::getNeedPoints, t.getEndPoints());
        wrapper.orderByDesc(EduProducts::getCreateTime);
        return this.page(page, wrapper);
    }

    /**
     * 审核通过并上架
     *
     * @param id
     * @return
     */
    @Override
    public int grounding(String id) {
        EduProducts eduProducts = this.baseMapper.selectById(id);
        if (!eduProducts.getStatus().equals(0)) {
            throw new RuntimeException(ERR_MSG_STATUS2);
        }
        eduProducts.setStatus(1);
        return this.baseMapper.updateById(eduProducts);
    }

    /**
     * 批量审核通过并上架
     *
     * @param ids id集合
     * @return
     */
    @Override
    public boolean batchGrounding(List<String> ids) {
        LambdaQueryWrapper<EduProducts> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(EduProducts::getId, ids);
        List<EduProducts> eduProducts = this.baseMapper.selectList(wrapper);
        for (EduProducts products : eduProducts) {
            if (!products.getStatus().equals(0)) {
                throw new RuntimeException(ERR_MSG_STATUS2);
            }
            products.setStatus(1);
        }
        return this.updateBatchById(eduProducts);
    }

    /**
     * 下架
     *
     * @param id
     * @return
     */
    @Override
    public int undercarriage(String id) {
        EduProducts eduProducts = this.baseMapper.selectById(id);
        if (!eduProducts.getStatus().equals(1)) {
            throw new RuntimeException(ERR_MSG_STATUS1);
        }
        eduProducts.setStatus(0);
        return this.baseMapper.updateById(eduProducts);
    }

    /**
     * 批量下架
     *
     * @param ids id集合
     * @return
     */
    @Override
    public boolean batchUndercarriage(List<String> ids) {
        LambdaQueryWrapper<EduProducts> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(EduProducts::getId, ids);
        List<EduProducts> eduProducts = this.baseMapper.selectList(wrapper);
        for (EduProducts products : eduProducts) {
            if (!products.getStatus().equals(1)) {
                throw new RuntimeException(ERR_MSG_STATUS1);
            }
            products.setStatus(0);
        }
        return this.updateBatchById(eduProducts);
    }
}




