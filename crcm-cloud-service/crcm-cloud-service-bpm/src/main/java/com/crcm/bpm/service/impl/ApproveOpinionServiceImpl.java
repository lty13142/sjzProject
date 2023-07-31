package com.crcm.bpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.domain.entity.ApproveOpinionDO;
import com.crcm.bpm.mapper.ApproveOpinionMapper;
import com.crcm.bpm.service.ApproveOpinionService;
import com.crcm.bpm.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审批意见表 服务实现类
 * </p>
 *
 * @author
 * @since 2020-11-23
 */
@Service
public class ApproveOpinionServiceImpl extends ServiceImpl<ApproveOpinionMapper, ApproveOpinionDO> implements ApproveOpinionService {

    @Override
    public List<ApproveOpinionDO> getApproveOpinion(Long applyId) {
        LambdaQueryWrapper<ApproveOpinionDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApproveOpinionDO::getApplyId, applyId);
        // 未删除
        queryWrapper.eq(ApproveOpinionDO::getDeleted, 0);
        queryWrapper.orderByDesc(ApproveOpinionDO::getCreateTime);
        queryWrapper.orderByDesc(ApproveOpinionDO::getId);
        List<ApproveOpinionDO> approveOpinionDOList = baseMapper.selectList(queryWrapper);
        if (approveOpinionDOList.size() > 0) {
            List<String> ids = new ArrayList<>();
            approveOpinionDOList.forEach(getApproveOpinionListVO -> {
                ids.add(getApproveOpinionListVO.getCreateUserId());
            });
            Map<String, String> avatarMap = FileUtil.getAvatarMap(ids);
            approveOpinionDOList.forEach(approveOpinionDO -> {
                approveOpinionDO.setAvatar(avatarMap.get(approveOpinionDO.getCreateUserId()));
            });
        }
        return approveOpinionDOList;
    }
}
