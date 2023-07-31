package com.zsgf.platform.service.impl.company;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.mapper.company.HazardousChemicalsMapper;
import com.zsgf.platform.model.entity.company.HazardousChemicals;
import com.zsgf.platform.service.company.HazardousChemicalsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 危险化学品备案信息Service业务层处理
 *
 * @author gzl
 * @date 2023-03-24
 */
@Service
public class HazardousChemicalsServiceImpl extends ServiceImpl<HazardousChemicalsMapper, HazardousChemicals> implements HazardousChemicalsService {


    /**
     * 新增危险化学品备案信息
     *
     * @param hazardousChemicals 危险化学品备案信息
     * @return 结果
     */
    @Override
    public boolean saveHazardousChemicals(HazardousChemicals hazardousChemicals) {
        return this.save(hazardousChemicals);
    }

    /**
     * 修改危险化学品备案信息
     *
     * @param hazardousChemicals 危险化学品备案信息
     * @return 结果
     */
    @Override
    public boolean updateHazardousChemicals(HazardousChemicals hazardousChemicals) {
        return this.updateById(hazardousChemicals);
    }

    /**
     * 删除危险化学品备案信息信息
     *
     * @param id 危险化学品备案信息ID
     * @return 结果
     */
    @Override
    public boolean deleteHazardousChemicals(String id) {
        return this.removeById(id);
    }

    /**
     * 查询危险化学品备案信息
     *
     * @param id 危险化学品备案信息ID
     * @return 危险化学品备案信息
     */
    @Override
    public HazardousChemicals findHazardousChemicalsById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询危险化学品备案信息列表
     *
     * @param hazardousChemicals 危险化学品备案信息
     * @return 危险化学品备案信息
     */
    @Override
    public List<HazardousChemicals> findHazardousChemicalsList(HazardousChemicals hazardousChemicals) {
        LambdaQueryWrapper<HazardousChemicals> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询危险化学品备案信息
     *
     * @param page               分页参数
     * @param hazardousChemicals 危险化学品备案信息
     * @return 危险化学品备案信息
     */
    @Override
    public PageT<HazardousChemicals> findHazardousChemicalsPage(PageT<HazardousChemicals> page, HazardousChemicals hazardousChemicals) {
        LambdaQueryWrapper<HazardousChemicals> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }
}
