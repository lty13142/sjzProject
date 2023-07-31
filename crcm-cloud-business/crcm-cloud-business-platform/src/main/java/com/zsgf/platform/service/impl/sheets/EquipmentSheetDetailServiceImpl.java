package com.zsgf.platform.service.impl.sheets;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.constant.SystemConstant;
import com.zsgf.platform.mapper.sheets.EquipmentSheetDetailMapper;
import com.zsgf.platform.model.entity.sheets.EquipmentSheetDetail;
import com.zsgf.platform.service.sheets.EquipmentSheetDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备确认单详情Service业务层处理
 *
 * @author gzl
 * @date 2023-03-30
 */
@Service
public class EquipmentSheetDetailServiceImpl extends ServiceImpl<EquipmentSheetDetailMapper, EquipmentSheetDetail> implements EquipmentSheetDetailService {


    /**
     * 新增设备确认单详情
     *
     * @param equipmentSheetDetail 设备确认单详情
     * @return 结果
     */
    @Override
    public boolean saveEquipmentSheetDetail(EquipmentSheetDetail equipmentSheetDetail) {
        return this.save(equipmentSheetDetail);
    }

    /**
     * 修改设备确认单详情
     *
     * @param equipmentSheetDetail 设备确认单详情
     * @return 结果
     */
    @Override
    public boolean updateEquipmentSheetDetail(EquipmentSheetDetail equipmentSheetDetail) {
        return this.updateById(equipmentSheetDetail);
    }

    /**
     * 删除设备确认单详情信息
     *
     * @param id 设备确认单详情ID
     * @return 结果
     */
    @Override
    public boolean deleteEquipmentSheetDetail(Long id) {
        return this.removeById(id);
    }

    /**
     * 查询设备确认单详情
     *
     * @param id 设备确认单详情ID
     * @return 设备确认单详情
     */
    @Override
    public EquipmentSheetDetail findEquipmentSheetDetailById(Long id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询设备确认单详情列表
     *
     * @param equipmentSheetDetail 设备确认单详情
     * @return 设备确认单详情
     */
    @Override
    public List<EquipmentSheetDetail> findEquipmentSheetDetailList(EquipmentSheetDetail equipmentSheetDetail) {
        LambdaQueryWrapper<EquipmentSheetDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(equipmentSheetDetail.getCompanyId()), EquipmentSheetDetail::getCompanyId,
                equipmentSheetDetail.getCompanyId());
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询设备确认单详情
     *
     * @param page                 分页参数
     * @param equipmentSheetDetail 设备确认单详情
     * @return 设备确认单详情
     */
    @Override
    public PageT<EquipmentSheetDetail> findEquipmentSheetDetailPage(PageT<EquipmentSheetDetail> page, EquipmentSheetDetail equipmentSheetDetail) {
        LambdaQueryWrapper<EquipmentSheetDetail> queryWrapper = new LambdaQueryWrapper<>();
        return this.page(page, queryWrapper);
    }

    /**
     * 删除设备确认单详情信息
     *
     * @param companyId 企业ID
     */
    @Override
    public void deleteByCompanyId(String companyId) {
        LambdaUpdateWrapper<EquipmentSheetDetail> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(EquipmentSheetDetail::getDeleted, SystemConstant.YES_OR_NO.YES);
        updateWrapper.eq(EquipmentSheetDetail::getCompanyId, companyId);
        this.update(updateWrapper);
    }
}
