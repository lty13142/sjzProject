package com.zsgf.platform.service.standardize;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.standardize.WasteStandardizedAssessmentItemDetailDTO;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentItemDetail;
import com.zsgf.platform.vo.standardize.WasteStandardizedAssessmentItemDetailVo;

import java.util.List;

/**
 * 规范化考核小项Service接口
 *
 * @author gzl
 * @date 2023-03-20
 */
public interface WasteStandardizedAssessmentItemDetailService extends IService<WasteStandardizedAssessmentItemDetail> {

    /**
     * 新增规范化考核小项
     *
     * @param wasteStandardizedAssessmentItemDetail 规范化考核小项
     * @return 结果
     */
    boolean saveWasteStandardizedAssessmentItemDetail(WasteStandardizedAssessmentItemDetail wasteStandardizedAssessmentItemDetail);

    /**
     * 修改规范化考核小项
     *
     * @param wasteStandardizedAssessmentItemDetail 规范化考核小项
     * @return 结果
     */
    boolean updateWasteStandardizedAssessmentItemDetail(WasteStandardizedAssessmentItemDetail wasteStandardizedAssessmentItemDetail);

    /**
     * 删除规范化考核小项信息
     *
     * @param id 规范化考核小项ID
     * @return 结果
     */
    boolean deleteWasteStandardizedAssessmentItemDetail(String id);

    /**
     * 查询规范化考核小项
     *
     * @param id 规范化考核小项ID
     * @return 规范化考核小项
     */
    WasteStandardizedAssessmentItemDetail findWasteStandardizedAssessmentItemDetailById(String id);

    /**
     * 查询规范化考核小项列表
     *
     * @param queryDTO 规范化考核小项
     * @return 规范化考核小项集合
     */
    List<WasteStandardizedAssessmentItemDetailVo> findWasteStandardizedAssessmentItemDetailList(WasteStandardizedAssessmentItemDetailDTO queryDTO);

    /**
     * 分页查询规范化考核小项列表
     *
     * @param page     分页参数
     * @param queryDTO 规范化考核小项
     * @return 规范化考核小项集合
     */
    PageT<WasteStandardizedAssessmentItemDetailVo> findWasteStandardizedAssessmentItemDetailPage(PageT<WasteStandardizedAssessmentItemDetailVo> page,
                                                                                                 WasteStandardizedAssessmentItemDetailDTO queryDTO);

    /**
     * 根据大项id删除小项
     *
     * @Author GZL
     * @Date 2023/3/22 16:05
     * @Param itemId 大项id
     **/
    void deletedByItemId(String itemId);
}
