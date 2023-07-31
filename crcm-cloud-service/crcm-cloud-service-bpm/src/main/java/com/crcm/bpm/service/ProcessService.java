package com.crcm.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.bpm.domain.dto.ProcessDto;
import com.crcm.bpm.domain.dto.request.ProcessInfoReqDTO;
import com.crcm.bpm.domain.dto.response.GetSortListResponseDto;
import com.crcm.bpm.domain.dto.response.ProcessInfoDTO;
import com.crcm.bpm.domain.dto.response.ProcessStartResDTO;
import com.crcm.bpm.domain.entity.ProcessDO;

import java.util.List;

public interface ProcessService extends IService<ProcessDO> {

    int saveProcess(ProcessDO processDO);

    int updateProcess(ProcessDO processDO);

    int deleteById(Long id);

    int realDelete(String id);

    ProcessDO findById(String id);

    List<ProcessDO> findList(ProcessDO processDO);

    IPage<ProcessDO> findPage(Page page, ProcessDO processDO);

    ProcessStartResDTO getStartInfo(ProcessInfoReqDTO process);

    ProcessInfoDTO getProcessInfoById(Long processId);

    /**
     * 分类查询流程列表
     *
     * @param flowType
     * @param processName
     * @return
     */
    List<GetSortListResponseDto> getSortList(String flowType, String processName);

    /**
     * 获取已使用的modelId
     *
     * @param companyId
     * @return
     */
    List<String> getUsedModelIdList(String companyId);

    /**
     * 根据流程processKey删除业务流程
     * @param processKey
     * @return
     */
    int delete(String processKey);

    List<ProcessDO> selectByProcesskey(String processKey);

    /**
     * 获取我收藏的流程列表
     *
     * @return
     */
    List<ProcessDto> getMyCollection();
}
