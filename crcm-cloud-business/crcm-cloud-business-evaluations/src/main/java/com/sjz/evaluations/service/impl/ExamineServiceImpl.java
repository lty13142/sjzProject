package com.sjz.evaluations.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.api.dto.res.AreaDTO;
import com.crcm.admin.api.dto.res.UserBaseInfoVO;
import com.crcm.admin.api.feign.RemoteAreaService;
import com.crcm.admin.api.feign.RemoteUserService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.response.RestResult;
import com.sjz.evaluations.mapper.ExamineMapper;
import com.sjz.evaluations.model.entity.Examine;
import com.sjz.evaluations.model.vo.ExamineVo;
import com.sjz.evaluations.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.List;


/**
 * 考核Service业务层处理
 * 
 * @author guozhilin
 * @date 2023-04-06
 */
@Service
public class ExamineServiceImpl extends ServiceImpl<ExamineMapper, Examine> implements ExamineService {

    @Autowired
    private RemoteAreaService remoteAreaService;

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 新增考核
     * 
     * @param examine 考核
     * @return 结果
     */
    @Override
    public boolean saveExamine(Examine examine) {
        examine.setReleaseStatus("0");
        return this.save(examine);
    }

    /**
     * 修改考核
     * 
     * @param examine 考核
     * @return 结果
     */
    @Override
    public boolean updateExamine(Examine examine) {
        return this.updateById(examine);
    }

    /**
     * 删除考核信息
     * 
     * @param id 考核ID
     * @return 结果
     */
    @Override
    public boolean deleteExamine(String id) {
        return this.removeById(id);
    }

    /**
     * 查询考核
     *
     * @param id 考核ID
     * @return 考核
     */
    @Override
    public Examine findExamineById(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询考核列表
     *
     * @param examine 考核
     * @return 考核
     */
    @Override
    public List<Examine> findExamineList(Examine examine) {
        LambdaQueryWrapper<Examine> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询考核
     *
     * @param page 分页参数
     * @param examine 考核
     * @return 考核
     */
    @Override
    public PageT<Examine> findExaminePage(PageT<Examine> page, Examine examine) {
        LambdaQueryWrapper<Examine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(examine.getYearly()),Examine::getYearly,examine.getYearly())
                .eq(StrUtil.isNotBlank(examine.getReleaseStatus()),Examine::getReleaseStatus,examine.getReleaseStatus())
                .like(StrUtil.isNotBlank(examine.getName()),Examine::getName,examine.getName());
        return this.page(page, queryWrapper);
    }

    @Override
    public PageT<ExamineVo> getAssessedPage(PageT<ExamineVo> page, Examine examine) {
        if (StrUtil.isNotBlank(examine.getVillageCode())){
            //查询村庄id
            AreaDTO areaDTO = remoteAreaService.getInfoByCode(examine.getVillageCode(), AuthConstants.FROM_IN).getData();
            examine.setAreaId(areaDTO.getId());
        }
        //考核者
        if ("0".equals(examine.getType())){
                UserBaseInfoVO data = remoteUserService.getUserBaseInfo(SecurityUtil.getCurrentUserId(), AuthConstants.FROM_IN).getData();
                examine.setOrgId(data.getOrgId());
                examine.setPositionId(data.getPositionId());
                examine.setAreaId(null);
        }else{
            UserBaseInfoVO data = remoteUserService.getUserBaseInfo(SecurityUtil.getCurrentUserId(), AuthConstants.FROM_IN).getData();
            AreaDTO areaDTO = remoteAreaService.getInfoByCode(data.getVillageCode(), AuthConstants.FROM_IN).getData();
            examine.setAreaId(areaDTO.getId());
        }
        PageT<ExamineVo>  pageT=this.baseMapper.getAssessedPage(page,examine);
        return pageT;
    }
}
