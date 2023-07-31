package com.crcm.bpm.fegin;

import com.crcm.bpm.domain.dto.request.UserRoleDetailQueryDTO;
import com.crcm.bpm.domain.dto.response.UserRoleDetailDTO;
import com.crcm.core.response.RestResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RemoteUserRoleService
 * @Description：用户角色关系远程调用服务
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/10
 **/
@Service
public class RemoteUserRoleService {
//    @Resource
//    private FeignRoleDistributionService feignRoleDistributionService;

    /**
     * 通过条件查询用户角色关系
     *
     * @param queryDTO
     * @return
     */
    public RestResult<List<UserRoleDetailDTO>> getUserRoleDetailByCondition(UserRoleDetailQueryDTO queryDTO) {
//        RoleDistributionQueryDTO roleDistributionQueryDTO = RoleDistributionQueryDTO.builder()
//                .comId(queryDTO.getCompanyId()).empId(queryDTO.getUserId())
//                .empIdList(queryDTO.getUserIdList()).roleId(queryDTO.getRoleId())
//                .roleIdList(queryDTO.getRoleIdList()).roleCode(queryDTO.getRoleCode())
//                .tenantId(queryDTO.getTenantId()).build();
//        RestResult<List<RoleDistributionResDTO>> result = feignRoleDistributionService.getRoleDistributionByCondition(queryDTO.getRoleIdList());
//        ArrayList<UserRoleDetailDTO> userRoleDetailDTOS = new ArrayList<>();
//        if (result.getSuccess()) {
//            List<RoleDistributionResDTO> data = result.getData();
//            if (data != null && data.size() >0) {
//                data.stream().forEach(dto -> {
//                    UserRoleDetailDTO userRoleDetailDTO = UserRoleDetailDTO.builder().companyId(dto.getComId())
//                            .roleId(dto.getRoleId()).userId(dto.getEmpId()).name(dto.getName()).build();
//                    userRoleDetailDTOS.add(userRoleDetailDTO);
//                });
//            }
//            return RestResult.succeed(userRoleDetailDTOS);
//        }
        return RestResult.failed();
    }


}
