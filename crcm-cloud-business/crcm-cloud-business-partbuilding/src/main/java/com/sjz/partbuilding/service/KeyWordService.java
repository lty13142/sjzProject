package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.entity.KeyWord;


import java.util.List;


/**
 * 关键词service
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/20 10:21
 */
public interface KeyWordService extends IService<KeyWord> {

    /**
     * 新增关键词
     *
     * @param keyWord 新增字段
     * @return
     */
    String saveKeyWordByNameStr(KeyWord keyWord);

    /**
     * 修改关键词
     *
     * @param keyWord 修改字段
     * @return
     */
    int updateKeyWord(KeyWord keyWord);

    /**
     * 根据id删除
     *
     * @param id id
     * @return
     */
    int deleteById(String id);

    /**
     * 真实删除
     *
     * @param id id
     * @return
     */
    int realDelete(String id);

    /**
     * 根据id查询
     *
     * @param id 查询字段
     * @return
     */
    KeyWord findById(String id);

    /**
     * 查询列表
     *
     * @param keyWord 查询字段
     * @return
     */
    List<KeyWord> findList(KeyWord keyWord);

    /**
     * 分页查询
     *
     * @param page    分页参数
     * @param keyWord 查询字段
     * @return
     */
    IPage<KeyWord> findPage(Page page, KeyWord keyWord);


    /**
     * 根据idStr获取关键词集合
     *
     * @param idStr ids字符串(逗号分隔)
     * @return
     */
    List<KeyWord> getKeyWorkList(String idStr);

    /**
     * 清空原关键词并把新关键词重新新增
     *
     * @param oldIds      原关键词ids
     * @param newKeyWords 新关键词名
     * @return
     */
    String updateKeyWorkByOldIds(String oldIds, String newKeyWords);
}
