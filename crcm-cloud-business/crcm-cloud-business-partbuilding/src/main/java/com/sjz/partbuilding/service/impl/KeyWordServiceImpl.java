package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjz.partbuilding.mapper.KeyWordMapper;
import com.sjz.partbuilding.model.entity.KeyWord;
import com.sjz.partbuilding.service.KeyWordService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * 关键词serviceImpl
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/20 10:21
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class KeyWordServiceImpl extends ServiceImpl<KeyWordMapper, KeyWord> implements KeyWordService {


    /**
     * 新增关键词
     *
     * @param keyWord 新增字段
     * @return
     */
    @Override
    public String saveKeyWordByNameStr(KeyWord keyWord) {
        String idStr = "";
        //新闻通知关键词不为空
        if (StringUtils.isNotBlank(keyWord.getNameStr())) {
            //创建新增集合
            List<KeyWord> saveList = new ArrayList<>();
            //返回ids集合
            List<String> idsList = new ArrayList<>();
            String[] nameArrays = keyWord.getNameStr().split(",");
            for (String name : nameArrays) {
                //组装每一次的新增对象
                KeyWord saveKeyWord = new KeyWord();
                String id = UUID.randomUUID().toString().replace("-", "");
                saveKeyWord.setId(id);
                saveKeyWord.setName(name);
                //往集合组装数据
                saveList.add(saveKeyWord);
                idsList.add(id);
            }
            //id字符串隔开
            idStr = StringUtils.join(idsList.toArray(), ",");
            this.saveBatch(saveList);
        }
        return idStr;
    }

    /**
     * 修改关键词
     *
     * @param keyWord 修改字段
     * @return
     */
    @Override
    public int updateKeyWord(KeyWord keyWord) {
        return this.baseMapper.updateById(keyWord);
    }

    /**
     * 根据id删除
     *
     * @param id id
     * @return
     */
    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * 真实删除
     *
     * @param id id
     * @return
     */
    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    /**
     * 根据id查询
     *
     * @param id 查询字段
     * @return
     */
    @Override
    public KeyWord findById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询列表
     *
     * @param keyWord 查询字段
     * @return
     */
    @Override
    public List<KeyWord> findList(KeyWord keyWord) {
        QueryWrapper<KeyWord> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param page    分页参数
     * @param keyWord 查询字段
     * @return
     */
    @Override
    public IPage<KeyWord> findPage(Page page, KeyWord keyWord) {
        //page.setSize(8);
        LambdaQueryWrapper<KeyWord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询字段
        lambdaQueryWrapper.select(KeyWord::getId, KeyWord::getName);
        //根据创建时间倒叙排列
        lambdaQueryWrapper.orderByDesc(KeyWord::getCreateTime);
        //名字不重复
        lambdaQueryWrapper.groupBy(KeyWord::getName);
        //查询30条关键字
        IPage iPage = this.baseMapper.selectPage(page, lambdaQueryWrapper);
        return iPage;
    }

    /**
     * 根据idStr获取关键词集合
     *
     * @param idStr ids字符串(逗号分隔)
     * @return
     */
    @Override
    public List<KeyWord> getKeyWorkList(String idStr) {
        //为空
        if (StringUtils.isBlank(idStr)) {
            return new ArrayList<>();
        }

        List<KeyWord> keyWordList = new ArrayList<>();
        String[] idArrays = idStr.split(",");
        for(String id : idArrays){
            KeyWord dataById = this.baseMapper.getDataById(id);
            keyWordList.add(dataById);
        }
        return keyWordList;
//        //按逗号分割
//        String[] idArrays = idStr.split(",");
//        LambdaQueryWrapper<KeyWord> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.in(KeyWord::getId, idArrays);
//        //查询列表
//        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 清空原关键词并新增新关键词
     *
     * @param oldIds      原关键词ids
     * @param newKeyWords 新关键词名
     * @return
     */
    @Override
    public String updateKeyWorkByOldIds(String oldIds, String newKeyWords) {
        //不为空
        if (oldIds != null && !"".equals(oldIds)) {
            String[] keyArrays = oldIds.split(",");
            //清空原关键词
            this.baseMapper.deleteBatchKeyWorkByIds(keyArrays);
        }
        //新增新关键词
        KeyWord keyWord = new KeyWord();
        keyWord.setNameStr(newKeyWords);
        String idsStr = this.saveKeyWordByNameStr(keyWord);
        return idsStr;
    }
}
