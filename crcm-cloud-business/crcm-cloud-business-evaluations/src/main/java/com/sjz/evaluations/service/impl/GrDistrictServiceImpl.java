package com.sjz.evaluations.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import com.sjz.evaluations.mapper.GrDistrictMapper;
import com.sjz.evaluations.model.entity.GrDistrict;
import com.sjz.evaluations.service.GrDistrictService;
import org.springframework.stereotype.Service;

/**
 * 管区Service业务层处理
 * 
 * @author guozhilin
 * @date 2023-04-04
 */
@Service
public class GrDistrictServiceImpl extends ServiceImpl<GrDistrictMapper, GrDistrict> implements GrDistrictService {

    

    /**
     * 新增管区
     * 
     * @param grDistrict 管区
     * @return 结果
     */
    @Override
    public boolean saveGrDistrict(GrDistrict grDistrict) {
        return this.save(grDistrict);
    }

    /**
     * 修改管区
     * 
     * @param grDistrict 管区
     * @return 结果
     */
    @Override
    public boolean updateGrDistrict(GrDistrict grDistrict) {
        return this.updateById(grDistrict);
    }

    /**
     * 删除管区信息
     * 
     * @param id 管区ID
     * @return 结果
     */
    @Override
    public boolean deleteGrDistrict(String id) {
        return this.removeById(id);
    }

    /**
     * 查询管区
     *
     * @param id 管区ID
     * @return 管区
     */
    @Override
    public GrDistrict findGrDistrictById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询管区列表
     *
     * @param grDistrict 管区
     * @return 管区
     */
    @Override
    public List<GrDistrict> findGrDistrictList(GrDistrict grDistrict) {
        LambdaQueryWrapper<GrDistrict> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询管区
     *
     * @param page 分页参数
     * @param grDistrict 管区
     * @return 管区
     */
    @Override
    public PageT<GrDistrict> findGrDistrictPage(PageT<GrDistrict> page, GrDistrict grDistrict) {
        LambdaQueryWrapper<GrDistrict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(grDistrict.getName()),GrDistrict::getName,grDistrict.getName());
        return this.page(page, queryWrapper);
    }
}
