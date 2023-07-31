package com.zsgf.platform.service.impl.company;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.core.constant.SystemConstant;
import com.zsgf.platform.dto.company.WasteInfoQueryDTO;
import com.zsgf.platform.mapper.company.WasteInfoMapper;
import com.zsgf.platform.model.entity.company.WasteInfo;
import com.zsgf.platform.service.company.WasteInfoService;
import com.zsgf.platform.utils.SecurityBusinessUtil;
import com.zsgf.platform.vo.company.WasteInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 企业废物信息Service业务层处理
 *
 * @author zzyt
 * @date 2023-02-22
 */
@Service
public class WasteInfoServiceImpl extends ServiceImpl<WasteInfoMapper, WasteInfo> implements WasteInfoService {


    /**
     * 新增企业废物信息
     *
     * @param wasteInfo 企业废物信息
     * @return 结果
     */
    @Override
    public boolean saveWasteInfo(WasteInfo wasteInfo) {
        AuthUser currentUser = SecurityBusinessUtil.getCurrentUserInfo(true);
//        wasteInfo.setCompanyId(currentUser.getCompanyId());
        return this.save(wasteInfo);
    }

    /**
     * 修改企业废物信息
     *
     * @param wasteInfo 企业废物信息
     * @return 结果
     */
    @Override
    public boolean updateWasteInfo(WasteInfo wasteInfo) {
        return this.updateById(wasteInfo);
    }

    /**
     * 删除企业废物信息信息
     *
     * @param id 企业废物信息ID
     * @return 结果
     */
    @Override
    public boolean deleteWasteInfo(String id) {
        return this.removeById(id);
    }

    /**
     * 查询企业废物信息
     *
     * @param id 企业废物信息ID
     * @return 企业废物信息
     */
    @Override
    public WasteInfo findWasteInfoById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询企业废物信息列表
     *
     * @param wasteInfo 企业废物信息
     * @return 企业废物信息
     */
    @Override
    public List<WasteInfo> findWasteInfoList(WasteInfoQueryDTO wasteInfo) {
        return this.baseMapper.selectList(getLambdaQueryWrapper(wasteInfo));
    }

    /**
     * 分页查询企业废物信息
     *
     * @param page      分页参数
     * @param wasteInfo 企业废物信息
     * @return 企业废物信息
     */
    @Override
    public PageT<WasteInfoVo> findWasteInfoPage(PageT<WasteInfoVo> page, WasteInfoQueryDTO wasteInfo) {
        AuthUser currentUser = SecurityBusinessUtil.getCurrentUserInfo();
        if (Objects.equals(SystemConstant.USER_TYPE.ENTERPRISE_ACCOUNT, currentUser.getUserType())) {
//            wasteInfo.setCompanyId(currentUser.getCompanyId());
        }
        return this.baseMapper.findWasteInfoPage(page, wasteInfo);
    }

    /**
     * 获取查询Wrapper
     *
     * @return 查询Wrapper
     * @Author GZL
     * @Date 2023/2/24 14:26
     * @Param wasteInfo 查询参数
     **/
    private LambdaQueryWrapper<WasteInfo> getLambdaQueryWrapper(WasteInfoQueryDTO wasteInfo) {
        AuthUser currentUser = SecurityBusinessUtil.getCurrentUserInfo();
        if (Objects.equals(SystemConstant.USER_TYPE.ENTERPRISE_ACCOUNT, currentUser.getUserType())) {
//            wasteInfo.setCompanyId(currentUser.getCompanyId());
        }
        LambdaQueryWrapper<WasteInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(wasteInfo.getWasteName()), WasteInfo::getWasteName, wasteInfo.getWasteName());
        queryWrapper.eq(StringUtils.isNotBlank(wasteInfo.getCompanyId()), WasteInfo::getCompanyId, wasteInfo.getCompanyId());
        queryWrapper.eq(StringUtils.isNotBlank(wasteInfo.getDangerousType()), WasteInfo::getDangerousType, wasteInfo.getDangerousType());
        queryWrapper.eq(StringUtils.isNotBlank(wasteInfo.getWasteCode()), WasteInfo::getWasteCode, wasteInfo.getWasteCode());
        queryWrapper.eq(StringUtils.isNotBlank(wasteInfo.getWasteType()), WasteInfo::getWasteType, wasteInfo.getWasteType());
        queryWrapper.eq(StringUtils.isNotBlank(wasteInfo.getDirectoryVersion()), WasteInfo::getDirectoryVersion, wasteInfo.getDirectoryVersion());
        queryWrapper.eq(Objects.nonNull(wasteInfo.getWasteCategory()), WasteInfo::getWasteCategory, wasteInfo.getWasteCategory());
        return queryWrapper;
    }
}
