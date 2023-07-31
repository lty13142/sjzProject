package com.zsgf.platform.service.impl.standardize;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.standardize.WasteStandardizedAssessmentJudgeMapper;
import com.zsgf.platform.model.entity.standardize.WasteStandardizedAssessmentJudge;
import com.zsgf.platform.service.standardize.WasteStandardizedAssessmentJudgeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规范化考核结果Service业务层处理
 *
 * @author gzl
 * @date 2023-03-24
 */
@Service
public class WasteStandardizedAssessmentJudgeServiceImpl extends ServiceImpl<WasteStandardizedAssessmentJudgeMapper, WasteStandardizedAssessmentJudge> implements WasteStandardizedAssessmentJudgeService {


    /**
     * 新增规范化考核结果
     *
     * @param wasteStandardizedAssessmentJudge 规范化考核结果
     * @return 结果
     */
    @Override
    public boolean saveWasteStandardizedAssessmentJudge(WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge) {
        return this.save(wasteStandardizedAssessmentJudge);
    }

    /**
     * 修改规范化考核结果
     *
     * @param wasteStandardizedAssessmentJudge 规范化考核结果
     * @return 结果
     */
    @Override
    public boolean updateWasteStandardizedAssessmentJudge(WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge) {
        return this.updateById(wasteStandardizedAssessmentJudge);
    }

    /**
     * 删除规范化考核结果信息
     *
     * @param id 规范化考核结果ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteStandardizedAssessmentJudge(String id) {
        return this.removeById(id);
    }

    /**
     * 查询规范化考核结果
     *
     * @param id 规范化考核结果ID
     * @return 规范化考核结果
     */
    @Override
    public WasteStandardizedAssessmentJudge findWasteStandardizedAssessmentJudgeById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询规范化考核结果列表
     *
     * @param wasteStandardizedAssessmentJudge 规范化考核结果
     * @return 规范化考核结果
     */
    @Override
    public List<WasteStandardizedAssessmentJudge> findWasteStandardizedAssessmentJudgeList(WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge) {
        LambdaQueryWrapper<WasteStandardizedAssessmentJudge> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询规范化考核结果
     *
     * @param page                             分页参数
     * @param wasteStandardizedAssessmentJudge 规范化考核结果
     * @return 规范化考核结果
     */
    @Override
    public PageT<WasteStandardizedAssessmentJudge> findWasteStandardizedAssessmentJudgePage(PageT<WasteStandardizedAssessmentJudge> page, WasteStandardizedAssessmentJudge wasteStandardizedAssessmentJudge) {
        LambdaQueryWrapper<WasteStandardizedAssessmentJudge> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
