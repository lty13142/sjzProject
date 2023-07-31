package com.zsgf.platform.mapper.standardize;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentItem;

/**
 * 规范化考核大项Mapper接口
 *
 * @author gzl
 * @date 2023-03-20
 */
public interface WasteStandardizedAssessmentItemMapper extends BaseMapper<WasteStandardizedAssessmentItem> {


    /**
     * 获取排序序号
     *
     * @return 排序序号
     * @Author GZL
     * @Date 2023/3/21 9:26
     * @Param itemSort 类别
     **/
    int getMaxOrderByItemSort(String itemSort);
}
