package com.crcm.bpm.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.domain.entity.ProcessCollectionDO;
import com.crcm.bpm.mapper.ProcessCollectionMapper;
import com.crcm.bpm.service.ProcessCollectionService;
import com.crcm.core.exception.CustomException;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.utils.SecurityUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程收藏表 服务实现类
 * </p>
 *
 * @author wxl
 * @since 2021-03-05
 */
@Service
public class ProcessCollectionServiceImpl extends ServiceImpl<ProcessCollectionMapper, ProcessCollectionDO> implements ProcessCollectionService {

    @Override
    public Boolean saveEntity(ProcessCollectionDO processCollectionDO) {
        UserAccount securityUserDetails = SecurityUtil.getCurrentUser();
        ProcessCollectionDO old = baseMapper.selectOne(Wrappers.lambdaQuery(ProcessCollectionDO.class)
                .eq(ProcessCollectionDO::getEmployeeId, securityUserDetails.getUserId())
                .eq(ProcessCollectionDO::getProcessId, processCollectionDO.getProcessId()));
        if(old != null){
            return true;
        }
        processCollectionDO.setEmployeeId(securityUserDetails.getUserId());
        processCollectionDO.setCompanyId(securityUserDetails.getComId());
        processCollectionDO.setCreateBy(securityUserDetails.getUserId());
        int num = baseMapper.insert(processCollectionDO);
        if(num > 0){
            return true;
        }
        throw new CustomException(HttpStatus.HTTP_PRECON_FAILED, "保存失败，请检查参数！");
    }
}
