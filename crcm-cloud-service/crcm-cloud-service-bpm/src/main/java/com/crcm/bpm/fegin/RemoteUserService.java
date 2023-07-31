package com.crcm.bpm.fegin;

import com.crcm.bpm.domain.dto.response.UserInfoDTO;
import com.crcm.core.response.RestResult;
import org.springframework.stereotype.Service;

/**
 * @ClassName RemoteUserService
 * @Description：模拟微服务远程调用用户服务
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/10
 **/
@Service
public class RemoteUserService {

//    @Resource
//    private FeignEmployeeService feignEmployeeService;
    /**
     * 通过用户ID获取用户信息
     * @param userId
     * @return
     */
    public RestResult<UserInfoDTO> getUserInfoById(String userId) {
//        RestResult<EmployeeDto> result = feignEmployeeService.getEmployeeDtoById(userId, SecurityConstants.FROM_IN);
//        if (result.getSuccess()) {
//            EmployeeDto employeeDto = result.getData();
//            ArrayList<SysRoleDTO> roles = new ArrayList<>();
//            if (employeeDto.getRoleList() != null) {
//                employeeDto.getRoleList().stream().forEach(sysRole -> {
//                    SysRoleDTO roleDTO = SysRoleDTO.builder()
//                            .comId(sysRole.getComId())
//                            .authorizedSigns(sysRole.getAuthorizedSigns())
//                            .id(sysRole.getId())
//                            .roleName(sysRole.getRoleName())
//                            .roleRemark(sysRole.getRoleRemark())
//                            .type(sysRole.getType()).build();
//                    roles.add(roleDTO);
//                });
//            }
//            UserInfoDTO userInfoDTO = UserInfoDTO.builder()
//                    .id(employeeDto.getEmployeeId())
//                    .username(employeeDto.getAccount())
//                    .name(employeeDto.getEmployeeName())
//                    .companyId(employeeDto.getComId())
//                    .deptIdList(employeeDto.getDeptIdList())
//                    .roles(roles).build();
//            return RestResult.succeed(userInfoDTO);
//        }
        return RestResult.failed();
    }
}
