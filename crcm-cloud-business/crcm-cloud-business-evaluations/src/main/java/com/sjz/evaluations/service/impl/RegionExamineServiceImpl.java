package com.sjz.evaluations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.sjz.evaluations.mapper.RegionExamineMapper;
import com.sjz.evaluations.model.entity.RegionExamine;
import com.sjz.evaluations.service.RegionExamineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 区域考核实体Service业务层处理
 *
 * @author zzyt
 * @date 2023-04-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RegionExamineServiceImpl extends ServiceImpl<RegionExamineMapper, RegionExamine> implements RegionExamineService {


    /**
     * 新增区域考核实体
     *
     * @param regionExamine 区域考核实体
     * @return 结果
     */
    @Override
    public boolean saveRegionExamine(RegionExamine regionExamine) {
        return this.save(regionExamine);
    }

    /**
     * 修改区域考核实体
     *
     * @param regionExamine 区域考核实体
     * @return 结果
     */
    @Override
    public boolean updateRegionExamine(RegionExamine regionExamine) {
        return this.updateById(regionExamine);
    }

    /**
     * 删除区域考核实体信息
     *
     * @param id 区域考核实体ID
     * @return 结果
     */
    @Override
    public boolean deleteRegionExamine(String id) {
        return this.removeById(id);
    }

    /**
     * 查询区域考核实体
     *
     * @param id 区域考核实体ID
     * @return 区域考核实体
     */
    @Override
    public RegionExamine findRegionExamineById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询区域考核实体列表
     *
     * @param regionExamine 区域考核实体
     * @return 区域考核实体
     */
    @Override
    public List<RegionExamine> findRegionExamineList(RegionExamine regionExamine) {
        return this.baseMapper.selectList(
                Wrappers.lambdaQuery(RegionExamine.class)
                        .eq(!ObjectUtils.isNull(regionExamine.getIndicatorsId()), RegionExamine::getIndicatorsId, regionExamine.getIndicatorsId())
        );
    }

    /**
     * 分页查询区域考核实体
     *
     * @param page          分页参数
     * @param regionExamine 区域考核实体
     * @return 区域考核实体
     */
    @Override
    public IPage<RegionExamine> findRegionExaminePage(PageT page, RegionExamine regionExamine) {
        LambdaQueryWrapper<RegionExamine> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }

}
