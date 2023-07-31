package com.zsgf.platform.service.impl.waste;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.zsgf.platform.dto.waste.WasteDisposalWayDTO;
import com.zsgf.platform.mapper.waste.WasteDisposalWayMapper;
import com.zsgf.platform.model.entity.waste.WasteDisposalWay;
import com.zsgf.platform.service.waste.WasteDisposalWayService;
import com.zsgf.platform.vo.waste.WasteDisposalWayVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 废物处置方式Service业务层处理
 *
 * @author gzl
 * @date 2023-03-30
 */
@Service
public class WasteDisposalWayServiceImpl extends ServiceImpl<WasteDisposalWayMapper, WasteDisposalWay> implements WasteDisposalWayService {


    /**
     * 新增废物处置方式
     *
     * @param wasteDisposalWay 废物处置方式
     * @return 结果
     */
    @Override
    public boolean saveWasteDisposalWay(WasteDisposalWay wasteDisposalWay) {
        return this.save(wasteDisposalWay);
    }

    /**
     * 修改废物处置方式
     *
     * @param wasteDisposalWay 废物处置方式
     * @return 结果
     */
    @Override
    public boolean updateWasteDisposalWay(WasteDisposalWay wasteDisposalWay) {
        return this.updateById(wasteDisposalWay);
    }

    /**
     * 删除废物处置方式信息
     *
     * @param id 废物处置方式ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteDisposalWay(String id) {
        return this.removeById(id);
    }

    /**
     * 查询废物处置方式
     *
     * @param id 废物处置方式ID
     * @return 废物处置方式
     */
    @Override
    public WasteDisposalWay findWasteDisposalWayById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询废物处置方式列表
     *
     * @param wasteDisposalWay 废物处置方式
     * @return 废物处置方式
     */
    @Override
    public List<WasteDisposalWay> findWasteDisposalWayList(WasteDisposalWayDTO wasteDisposalWay) {
        LambdaQueryWrapper<WasteDisposalWay> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(wasteDisposalWay.getDisposalWayType()), WasteDisposalWay::getDisposalWayType,
                wasteDisposalWay.getDisposalWayType());
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询废物处置方式
     *
     * @param page             分页参数
     * @param wasteDisposalWay 废物处置方式
     * @return 废物处置方式
     */
    @Override
    public PageT<WasteDisposalWayVo> findWasteDisposalWayPage(PageT<WasteDisposalWayVo> page, WasteDisposalWayDTO wasteDisposalWay) {
        return this.baseMapper.findWasteDisposalWayPage(page, wasteDisposalWay);
    }
}
