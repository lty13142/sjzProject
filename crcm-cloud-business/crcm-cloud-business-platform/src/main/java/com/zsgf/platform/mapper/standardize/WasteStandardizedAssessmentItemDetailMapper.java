package com.zsgf.platform.mapper.standardize;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.standardize.WasteStandardizedAssessmentItemDetailDTO;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentItemDetail;
import com.zsgf.platform.vo.standardize.WasteStandardizedAssessmentItemDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规范化考核小项Mapper接口
 *
 * @author gzl
 * @date 2023-03-20
 */
public interface WasteStandardizedAssessmentItemDetailMapper extends BaseMapper<WasteStandardizedAssessmentItemDetail> {


    /**
     * 获取排序序号
     *
     * @return 排序序号
     * @Author GZL
     * @Date 2023/3/21 10:22
     * @Param itemSort 类别
     **/
    int getMaxOrderByItemSort(String itemSort);

    List<WasteStandardizedAssessmentItemDetailVo> getItemDetailList(@Param("queryDTO") WasteStandardizedAssessmentItemDetailDTO queryDTO);

    PageT<WasteStandardizedAssessmentItemDetailVo> getItemDetailPage(@Param("page") PageT<WasteStandardizedAssessmentItemDetailVo> page
            , @Param("queryDTO") WasteStandardizedAssessmentItemDetailDTO queryDTO);

    /**
     * 根据大项id删除小项
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/3/22 16:05
     * @Param detail 小项信息
     **/
    int deletedByItemId(WasteStandardizedAssessmentItemDetail detail);
}
