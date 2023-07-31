package com.zsgf.platform.service.impl.standardize;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.standardize.WasteStandardizedAssessmentItemDTO;
import com.zsgf.platform.mapper.standardize.WasteStandardizedAssessmentItemDetailMapper;
import com.zsgf.platform.mapper.standardize.WasteStandardizedAssessmentItemMapper;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentItem;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentItemDetailService;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规范化考核大项Service业务层处理
 *
 * @author gzl
 * @date 2023-03-20
 */
@Service
public class WasteStandardizedAssessmentItemServiceImpl extends ServiceImpl<WasteStandardizedAssessmentItemMapper, WasteStandardizedAssessmentItem>
        implements WasteStandardizedAssessmentItemService {

    @Resource
    private WasteStandardizedAssessmentItemMapper wasteStandardizedAssessmentItemMapper;

    @Resource
    private WasteStandardizedAssessmentItemDetailService wasteStandardizedAssessmentItemDetailService;

    /**
     * 新增规范化考核大项
     *
     * @param wasteStandardizedAssessmentItem 规范化考核大项
     * @return 结果
     */
    @Override
    public boolean saveWasteStandardizedAssessmentItem(WasteStandardizedAssessmentItem wasteStandardizedAssessmentItem) {
        // 获取排序序号
        int maxOrderByItemSort = wasteStandardizedAssessmentItemMapper.getMaxOrderByItemSort(wasteStandardizedAssessmentItem.getItemSort());
        wasteStandardizedAssessmentItem.setOrderNo(maxOrderByItemSort + 1);
        return this.save(wasteStandardizedAssessmentItem);
    }

    /**
     * 修改规范化考核大项
     *
     * @param wasteStandardizedAssessmentItem 规范化考核大项
     * @return 结果
     */
    @Override
    public boolean updateWasteStandardizedAssessmentItem(WasteStandardizedAssessmentItem wasteStandardizedAssessmentItem) {
        return this.updateById(wasteStandardizedAssessmentItem);
    }

    /**
     * 删除规范化考核大项信息
     *
     * @param id 规范化考核大项ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteStandardizedAssessmentItem(String id) {
        boolean removeById = this.removeById(id);
        if(removeById){
            // 删除小项
            wasteStandardizedAssessmentItemDetailService.deletedByItemId(id);
        }
        return removeById;
    }

    /**
     * 查询规范化考核大项
     *
     * @param id 规范化考核大项ID
     * @return 规范化考核大项
     */
    @Override
    public WasteStandardizedAssessmentItem findWasteStandardizedAssessmentItemById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询规范化考核大项列表
     *
     * @param queryDTO 规范化考核大项
     * @return 规范化考核大项
     */
    @Override
    public List<WasteStandardizedAssessmentItem> findWasteStandardizedAssessmentItemList(WasteStandardizedAssessmentItemDTO queryDTO) {
        LambdaQueryWrapper<WasteStandardizedAssessmentItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(queryDTO.getItemSort()), WasteStandardizedAssessmentItem::getItemSort, queryDTO.getItemSort());
        queryWrapper.orderByAsc(WasteStandardizedAssessmentItem::getOrderNo);
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询规范化考核大项
     *
     * @param page     分页参数
     * @param queryDTO 规范化考核大项
     * @return 规范化考核大项
     */
    @Override
    public PageT<WasteStandardizedAssessmentItem> findWasteStandardizedAssessmentItemPage(PageT<WasteStandardizedAssessmentItem> page,
                                                                                          WasteStandardizedAssessmentItemDTO queryDTO) {
        LambdaQueryWrapper<WasteStandardizedAssessmentItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(queryDTO.getItemSort()), WasteStandardizedAssessmentItem::getItemSort, queryDTO.getItemSort());
        queryWrapper.like(StringUtils.isNotBlank(queryDTO.getName()), WasteStandardizedAssessmentItem::getName, queryDTO.getName());
        queryWrapper.orderByAsc(WasteStandardizedAssessmentItem::getOrderNo);
        return this.page(page, queryWrapper);
    }
}
