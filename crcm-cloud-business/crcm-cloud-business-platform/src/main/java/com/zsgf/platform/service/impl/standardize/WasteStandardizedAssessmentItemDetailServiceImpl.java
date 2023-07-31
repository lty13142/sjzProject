package com.zsgf.platform.service.impl.standardize;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.standardize.WasteStandardizedAssessmentItemDetailDTO;
import com.zsgf.platform.mapper.standardize.WasteStandardizedAssessmentItemDetailMapper;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentItemDetail;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentItemDetailService;
import com.zsgf.platform.vo.standardize.WasteStandardizedAssessmentItemDetailVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 规范化考核小项Service业务层处理
 *
 * @author gzl
 * @date 2023-03-20
 */
@Service
public class WasteStandardizedAssessmentItemDetailServiceImpl extends ServiceImpl<WasteStandardizedAssessmentItemDetailMapper,
        WasteStandardizedAssessmentItemDetail> implements WasteStandardizedAssessmentItemDetailService {

    @Resource
    private WasteStandardizedAssessmentItemDetailMapper wasteStandardizedAssessmentItemDetailMapper;

    /**
     * 新增规范化考核小项
     *
     * @param itemDetail 规范化考核小项
     * @return 结果
     */
    @Override
    public boolean saveWasteStandardizedAssessmentItemDetail(WasteStandardizedAssessmentItemDetail itemDetail) {
        // 获取排序序号
        int maxOrderByItemSort = wasteStandardizedAssessmentItemDetailMapper.getMaxOrderByItemSort(itemDetail.getItemSort());
        itemDetail.setOrderNo(maxOrderByItemSort);
        return this.save(itemDetail);
    }

    /**
     * 修改规范化考核小项
     *
     * @param itemDetail 规范化考核小项
     * @return 结果
     */
    @Override
    public boolean updateWasteStandardizedAssessmentItemDetail(WasteStandardizedAssessmentItemDetail itemDetail) {
        return this.updateById(itemDetail);
    }

    /**
     * 删除规范化考核小项信息
     *
     * @param id 规范化考核小项ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteStandardizedAssessmentItemDetail(String id) {
        return this.removeById(new WasteStandardizedAssessmentItemDetail());
    }

    /**
     * 查询规范化考核小项
     *
     * @param id 规范化考核小项ID
     * @return 规范化考核小项
     */
    @Override
    public WasteStandardizedAssessmentItemDetail findWasteStandardizedAssessmentItemDetailById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询规范化考核小项列表
     *
     * @param queryDTO 规范化考核小项
     * @return 规范化考核小项
     */
    @Override
    public List<WasteStandardizedAssessmentItemDetailVo> findWasteStandardizedAssessmentItemDetailList(WasteStandardizedAssessmentItemDetailDTO queryDTO) {
        return wasteStandardizedAssessmentItemDetailMapper.getItemDetailList(queryDTO);
    }

    /**
     * 分页查询规范化考核小项
     *
     * @param page     分页参数
     * @param queryDTO 规范化考核小项
     * @return 规范化考核小项
     */
    @Override
    public PageT<WasteStandardizedAssessmentItemDetailVo> findWasteStandardizedAssessmentItemDetailPage(PageT<WasteStandardizedAssessmentItemDetailVo> page,
                                                                                                        WasteStandardizedAssessmentItemDetailDTO queryDTO) {
        return wasteStandardizedAssessmentItemDetailMapper.getItemDetailPage(page, queryDTO);
    }

    /**
     * 根据大项id删除小项
     *
     * @Author GZL
     * @Date 2023/3/22 16:05
     * @Param itemId 大项id
     **/
    @Override
    public void deletedByItemId(String itemId) {
        WasteStandardizedAssessmentItemDetail detail = new WasteStandardizedAssessmentItemDetail();
        detail.setUpdateTime(LocalDateTime.now());
        detail.setItemId(itemId);
        wasteStandardizedAssessmentItemDetailMapper.deletedByItemId(detail);
    }
}
