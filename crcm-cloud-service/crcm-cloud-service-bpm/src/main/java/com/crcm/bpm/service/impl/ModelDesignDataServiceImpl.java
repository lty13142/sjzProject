package com.crcm.bpm.service.impl;

import com.crcm.bpm.domain.dto.response.CompanyTreeDTO;
import com.crcm.bpm.domain.dto.response.DeptInfoDTO;
import com.crcm.bpm.domain.dto.response.PostInfoDTO;
import com.crcm.bpm.domain.dto.response.PostUserDTO;
import com.crcm.bpm.domain.vo.ModelDataQueryVO;
import com.crcm.bpm.service.ModelDesignDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ModelDesignDataServiceImpl
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/26
 **/
@Service
public class ModelDesignDataServiceImpl implements ModelDesignDataService {


//    @Resource
//    private FeignEmployeeService feignEmployeeService;
//    @Resource
//    private FeignCompanyService feignCompanyService;
//    @Resource
//    private FeignDepartmentService feignDepartmentService;
//    @Resource
//    private FeignPostService feignPostService;

    @Override
    public List<PostUserDTO> getUserList(ModelDataQueryVO queryVO) {
        List<PostUserDTO> users = new ArrayList<>();
//        RestResult<List<GetUsersByComIdResponseDto>> result = feignEmployeeService.getUsersByComId(queryVO.getCompanyId(), SecurityConstants.FROM_IN);
//        if (result.getSuccess()) {
//            for (GetUsersByComIdResponseDto datum : result.getData()) {
//                users.add(PostUserDTO.builder()
//                        .userId(datum.getEmployeeId())
//                        .postName(datum.getDepartmentName())
//                        .userName(datum.getEmployeeName())
//                        .postId(datum.getDepartmentId())
//                        .build());
//            }
//        }
        return users;
    }


    @Override
    public CompanyTreeDTO getCompanyTree(ModelDataQueryVO queryVO) {
//        Company company = new Company();
//        company.setCompanyId(queryVO.getCompanyId());
//        RestResult<CompanyTreeVo> result = feignCompanyService.getCompanyTree(company);
//        if (result.getSuccess()) {
//            CompanyTreeVo companyTreeVo = result.getData();
//            CompanyTreeDTO companyTree = CompanyTreeDTO.builder()
//                    .companyId(companyTreeVo.getCompanyId())
//                    .companyName(companyTreeVo.getCompanyName())
//                    .build();
//            return getCompanyTree(companyTree, companyTreeVo);
//        }
        return null;
    }

    @Override
    public List<DeptInfoDTO> getDeptList(String companyId) {
//        RestResult<List<DepartmentVo>> result = feignDepartmentService.findDepartmentListByOwnCom(companyId, SecurityConstants.FROM_IN);
        ArrayList<DeptInfoDTO> deptInfoDTOS = new ArrayList<>();
//        if (result.getSuccess()) {
//            List<DepartmentVo> list = result.getData();
//            for (DepartmentVo departmentVo : list) {
//                deptInfoDTOS.add(BeanUtil.copyProperties(departmentVo,DeptInfoDTO.class));
//            }
//        }
        return deptInfoDTOS;
    }

    @Override
    public List<PostInfoDTO> getPostList(String deptId) {
//        RestResult<List<Post>> result = feignPostService.getPstListByDepartmentId(deptId, SecurityConstants.FROM_IN);
        ArrayList<PostInfoDTO> list = new ArrayList<>();
//        if (result.getSuccess()) {
//            List<Post> posts = result.getData();
//            for (Post post : posts) {
//                list.add(PostInfoDTO.builder()
//                        .postId(post.getPostId())
//                        .postName(post.getPostName())
//                        .build());
//            }
//        }
        return list;
    }

//    private CompanyTreeDTO getCompanyTree(CompanyTreeDTO companyTreeDTO, CompanyTreeVo companyTreeVo) {
//        if (CollectionUtil.isNotEmpty(companyTreeVo.getChild())) {
//            ArrayList<CompanyTreeDTO> children = new ArrayList<>();
//            for (CompanyTreeVo treeVo : companyTreeVo.getChild()) {
//                CompanyTreeDTO child = CompanyTreeDTO.builder()
//                        .companyId(companyTreeVo.getCompanyId())
//                        .companyName(companyTreeVo.getCompanyName())
//                        .build();
//                children.add(getCompanyTree(child,treeVo));
//            }
//            companyTreeDTO.setChildren(children);
//        }
//        return companyTreeDTO;
//    }
}
