package com.crcm.bpm.fegin;

import com.crcm.bpm.domain.dto.response.DeptInfoDTO;
import com.crcm.core.response.RestResult;
import org.springframework.stereotype.Service;

/**
 * @ClassName RemoteDeptService
 * @Description：部门信息远程调用服务
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/18
 **/
@Service
public class RemoteDeptService {


//    @Resource
//    private FeignDepartmentService feignDepartmentService;


    /**
     * 获取部门信息
     * @param deptId 部门ID
     * @param tenantId 租户ID
     * @return
     */
    public RestResult<DeptInfoDTO> getDeptInfo(String deptId, String tenantId) {

//        RestResult<DepartmentVo> result = feignDepartmentService.findDeptById(deptId);
//        if (result.getSuccess()) {
//            DepartmentVo vo = result.getData();
//            DeptInfoDTO deptInfoDTO  = DeptInfoDTO.builder()
//                    .companyId(vo.getComId())
//                    .deptHeader(vo.getPostId())
//                    .deptManager(vo.getManager())
//                    .id(vo.getDepartmentId())
//                    .deptName(vo.getDepartmentName())
//                    .build();
//            return RestResult.succeed(deptInfoDTO);
//        }
        return RestResult.failed();
    }


    /**
     * 获取部门领导信息
     * @param deptId 部门ID
     * @param tenantId 租户ID
     * @return
     */
//    public Employee getDeptLeaderInfo(String deptId, String tenantId) {
//        RestResult<Employee> result = feignDepartmentService.getLeaderByDepartmentId(deptId);
//        if (!result.getSuccess() || result.getData() == null) {
////            throw new BpmException("部门领导查询失败！", BpmError.DATA_NOT_FOUND_ERROR);
//            return null;
//        }
//        return result.getData();
//    }
}
