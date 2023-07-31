package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.sjz.partbuilding.model.entity.KeyWord;
import org.apache.ibatis.annotations.Param;


/**
 * 关键词mapper
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/10/20 10:21
 */
public interface KeyWordMapper extends BaseMapper<KeyWord> {

    /**
     * 真实删除
     *
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);


    /**
     * 根据ids数组批量删除关键字
     *
     * @param keyArrays ids
     */
    int deleteBatchKeyWorkByIds(@Param("keyArrays") String[] keyArrays);

    KeyWord getDataById(String id);
}
