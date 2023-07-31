package com.crcm.bpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.bpm.domain.dto.ProcessDto;
import com.crcm.bpm.domain.dto.request.ProcessInfoReqDTO;
import com.crcm.bpm.domain.dto.response.GetSortListResponseDto;
import com.crcm.bpm.domain.dto.response.ProcessStartResDTO;
import com.crcm.bpm.domain.entity.ProcessDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ProcessMapper extends BaseMapper<ProcessDO> {

    /**
     * 真实删除
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);

    ProcessStartResDTO selectStartInfo(@Param("process") ProcessInfoReqDTO process);


    List<GetSortListResponseDto> getSortList(@Param("flowType") String flowType, @Param("companyId") String companyId, @Param("processName") String processName, @Param("roleIds") List<String> roleIds);

    List<String> getUsedModelIdList(@Param("companyId") String companyId);

    /**
     * 根据processId获取最后一次提交的FormJson
     *
     * @param processId
     * @param employeeId
     * @return
     */
    String getMaxOneFormJsonByProcessId(@Param("processId") Long processId, @Param("employeeId") String employeeId);

    /**
     * 获取我收藏的流程列表
     *
     * @param employeeId
     * @param companyId
     * @return
     */
    List<ProcessDto> getMyCollection(@Param("employeeId") String employeeId, @Param("companyId") String companyId);
}
