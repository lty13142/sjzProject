package com.sjz.evaluations.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.api.dto.res.AreaDTO;
import com.crcm.admin.api.feign.RemoteAreaService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.constant.AuthConstants;
import com.sjz.evaluations.mapper.GrRewardPunishmentMapper;
import com.sjz.evaluations.model.entity.GrRewardPunishment;
import com.sjz.evaluations.model.vo.RewardPunishmentVo;
import com.sjz.evaluations.service.GrRewardPunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 奖惩事项Service业务层处理
 * 
 * @author guozhilin
 * @date 2023-04-04
 */
@Service
public class GrRewardPunishmentServiceImpl extends ServiceImpl<GrRewardPunishmentMapper, GrRewardPunishment> implements GrRewardPunishmentService {

    @Autowired
    private RemoteAreaService remoteAreaService;

    /**
     * 新增奖惩事项
     * 
     * @param grRewardPunishment 奖惩事项
     * @return 结果
     */
    @Override
    public boolean saveGrRewardPunishment(GrRewardPunishment grRewardPunishment) {
        return this.save(grRewardPunishment);
    }

    /**
     * 修改奖惩事项
     * 
     * @param grRewardPunishment 奖惩事项
     * @return 结果
     */
    @Override
    public boolean updateGrRewardPunishment(GrRewardPunishment grRewardPunishment) {
        return this.updateById(grRewardPunishment);
    }

    /**
     * 删除奖惩事项信息
     * 
     * @param name 奖惩事项ID
     * @return 结果
     */
    @Override
    public boolean deleteGrRewardPunishment(String name) {
        return this.removeById(name);
    }

    /**
     * 查询奖惩事项
     *
     * @param name 奖惩事项ID
     * @return 奖惩事项
     */
    @Override
    public GrRewardPunishment findGrRewardPunishmentById(String name) {
        return this.baseMapper.selectById(name);
    }

    /**
     * 查询奖惩事项列表
     *
     * @param grRewardPunishment 奖惩事项
     * @return 奖惩事项
     */
    @Override
    public List<GrRewardPunishment> findGrRewardPunishmentList(GrRewardPunishment grRewardPunishment) {
        LambdaQueryWrapper<GrRewardPunishment> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查询奖惩事项
     *
     * @param page 分页参数
     * @param grRewardPunishment 奖惩事项
     * @return 奖惩事项
     */
    @Override
    public PageT<GrRewardPunishment> findGrRewardPunishmentPage(PageT<GrRewardPunishment> page, GrRewardPunishment grRewardPunishment) {
        List<String> collect=new ArrayList<>();
        //查询管区的村ID
        if (StrUtil.isNotBlank(grRewardPunishment.getOrganizationId())) {
            List<AreaDTO> data = remoteAreaService.getAreaListById(grRewardPunishment.getOrganizationId(), AuthConstants.FROM_IN).getData();
            if (data!=null&&data.size()>0) {
                collect = data.stream().map(e -> e.getId()).collect(Collectors.toList());
            }
        }
        LambdaQueryWrapper<GrRewardPunishment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(grRewardPunishment.getName()),GrRewardPunishment::getName,grRewardPunishment.getName())
                .in(collect.size()>0,GrRewardPunishment::getOrganizationId,collect)
                .orderByDesc(GrRewardPunishment::getCreateTime);
        PageT<GrRewardPunishment> page1 = this.page(page, queryWrapper);
        List<GrRewardPunishment> records = page1.getRecords();
        if (records.size()>0){
            for (GrRewardPunishment e : records) {
                AreaDTO area = remoteAreaService.getInfo(e.getOrganizationId(), AuthConstants.FROM_IN).getData();
                if (ObjectUtil.isNotEmpty(area)){
                    e.setGomName(area.getName());
                    e.setDName(area.getPname());
                }
            }
        }
        return page1;
    }

    @Override
    public List< Map<String,Object>> penaltyStatistics() {
       List< Map<String,Object>> mapList= new ArrayList<>();
        List<RewardPunishmentVo> rewardPunishmentVos= this.baseMapper.penaltyStatistics();
        if (rewardPunishmentVos.size()>0){
        int i=rewardPunishmentVos.size()>5?5: rewardPunishmentVos.size();
            rewardPunishmentVos = rewardPunishmentVos.subList(0, i);
            //总共数量
            int a = rewardPunishmentVos.stream().mapToInt(RewardPunishmentVo::getNum).sum();
            rewardPunishmentVos.forEach(e->{
                Map<String,Object> map=MapUtil.newHashMap();
                AreaDTO data = remoteAreaService.getInfo(e.getName(), AuthConstants.FROM_IN).getData();
                map.put("name",data.getName());
                String decimalFormat = NumberUtil.decimalFormat("#.##%", NumberUtil.div(Convert.toNumber(e.getNum()), Convert.toNumber(a)));
                map.put("scale",decimalFormat);
                mapList.add(map);
            });

        }
        return mapList;
    }
}
