package com.zsgf.platform.service.impl.standardize;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.standardize.WasteStandardizedAssessmentFeedbackMapper;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentFeedback;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentFeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规范化考核整改反馈Service业务层处理
 *
 * @author gzl
 * @date 2023-03-24
 */
@Service
public class WasteStandardizedAssessmentFeedbackServiceImpl extends ServiceImpl<WasteStandardizedAssessmentFeedbackMapper, WasteStandardizedAssessmentFeedback> implements WasteStandardizedAssessmentFeedbackService {


    /**
     * 新增规范化考核整改反馈
     *
     * @param wasteStandardizedAssessmentFeedback 规范化考核整改反馈
     * @return 结果
     */
    @Override
    public boolean saveWasteStandardizedAssessmentFeedback(WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback) {
        return this.save(wasteStandardizedAssessmentFeedback);
    }

    /**
     * 修改规范化考核整改反馈
     *
     * @param wasteStandardizedAssessmentFeedback 规范化考核整改反馈
     * @return 结果
     */
    @Override
    public boolean updateWasteStandardizedAssessmentFeedback(WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback) {
        return this.updateById(wasteStandardizedAssessmentFeedback);
    }

    /**
     * 删除规范化考核整改反馈信息
     *
     * @param id 规范化考核整改反馈ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteStandardizedAssessmentFeedback(String id) {
        return this.removeById(id);
    }

    /**
     * 查询规范化考核整改反馈
     *
     * @param id 规范化考核整改反馈ID
     * @return 规范化考核整改反馈
     */
    @Override
    public WasteStandardizedAssessmentFeedback findWasteStandardizedAssessmentFeedbackById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询规范化考核整改反馈列表
     *
     * @param wasteStandardizedAssessmentFeedback 规范化考核整改反馈
     * @return 规范化考核整改反馈
     */
    @Override
    public List<WasteStandardizedAssessmentFeedback> findWasteStandardizedAssessmentFeedbackList(WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback) {
        LambdaQueryWrapper<WasteStandardizedAssessmentFeedback> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询规范化考核整改反馈
     *
     * @param page                                分页参数
     * @param wasteStandardizedAssessmentFeedback 规范化考核整改反馈
     * @return 规范化考核整改反馈
     */
    @Override
    public PageT<WasteStandardizedAssessmentFeedback> findWasteStandardizedAssessmentFeedbackPage(PageT<WasteStandardizedAssessmentFeedback> page, WasteStandardizedAssessmentFeedback wasteStandardizedAssessmentFeedback) {
        LambdaQueryWrapper<WasteStandardizedAssessmentFeedback> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
