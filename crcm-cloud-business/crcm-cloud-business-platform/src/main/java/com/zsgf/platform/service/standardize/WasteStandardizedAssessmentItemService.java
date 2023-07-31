package com.zsgf.platform.service.standardize;

import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import com.zsgf.platform.dto.standardize.WasteStandardizedAssessmentItemDTO;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentItem;

/**
 * 规范化考核大项Service接口
 * 
 * @author gzl
 * @date 2023-03-20
 */
public interface WasteStandardizedAssessmentItemService extends IService<WasteStandardizedAssessmentItem>{

    /**
     * 新增规范化考核大项
     * 
     * @param wasteStandardizedAssessmentItem 规范化考核大项
     * @return 结果
     */
    boolean saveWasteStandardizedAssessmentItem(WasteStandardizedAssessmentItem wasteStandardizedAssessmentItem);

    /**
     * 修改规范化考核大项
     * 
     * @param wasteStandardizedAssessmentItem 规范化考核大项
     * @return 结果
     */
    boolean updateWasteStandardizedAssessmentItem(WasteStandardizedAssessmentItem wasteStandardizedAssessmentItem);

    /**
     * 删除规范化考核大项信息
     * 
     * @param id 规范化考核大项ID
     * @return 结果
     */
    boolean deleteWasteStandardizedAssessmentItem(String id);

    /**
     * 查询规范化考核大项
     *
     * @param id 规范化考核大项ID
     * @return 规范化考核大项
     */
    WasteStandardizedAssessmentItem findWasteStandardizedAssessmentItemById(String id);

    /**
     * 查询规范化考核大项列表
     *
     * @param wasteStandardizedAssessmentItem 规范化考核大项
     * @return 规范化考核大项集合
     */
    List<WasteStandardizedAssessmentItem> findWasteStandardizedAssessmentItemList(WasteStandardizedAssessmentItemDTO wasteStandardizedAssessmentItem);

    /**
     * 分页查询规范化考核大项列表
     * @param page 分页参数
     * @param wasteStandardizedAssessmentItem 规范化考核大项
     * @return 规范化考核大项集合
     */
    PageT<WasteStandardizedAssessmentItem> findWasteStandardizedAssessmentItemPage(PageT<WasteStandardizedAssessmentItem> page, WasteStandardizedAssessmentItemDTO wasteStandardizedAssessmentItem);
}
