package com.sjz.education.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.education.model.entity.EduProducts;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author SSSCCCC
 * @description 针对表【edu_products(积分可兑换商品)】的数据库操作Service
 * @createDate 2023-04-03 14:35:16
 */
public interface EduProductsService extends IService<EduProducts> {
    /**
     * 新增商品
     *
     * @param t
     * @return
     */
    boolean add(EduProducts t);

    /**
     * 修改商品
     *
     * @param t
     * @return
     */
    boolean edit(EduProducts t);

    /**
     * 根据id进行删除
     *
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     * 根据ids批量删除
     *
     * @param ids
     * @return
     */
    boolean batchDelete(List<String> ids);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    EduProducts findById(String id);

    /**
     * 根据ids查找
     *
     * @param ids
     * @return
     */
    List<EduProducts> findByIds(List<String> ids);

    /**
     * 获取商品列表
     *
     * @param t
     * @return
     */
    List<EduProducts> getList(EduProducts t);

    /**
     * 获取获取商品分页
     *
     * @param t,page分页参数
     * @return
     */
    IPage<EduProducts> getPage(PageT page, EduProducts t);

    /**
     * 审核通过并上架
     *
     * @param id
     * @return
     */
    int grounding(String id);

    /**
     * 批量审核通过并上架
     *
     * @param ids id集合
     * @return
     */
    boolean batchGrounding(List<String> ids);

    /**
     * 下架
     *
     * @param id
     * @return
     */
    int undercarriage(String id);

    /**
     * 批量下架
     *
     * @param ids id集合
     * @return
     */
    boolean batchUndercarriage(List<String> ids);
}
