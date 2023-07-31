package com.crcm.bpm.fegin;

import com.crcm.bpm.domain.dto.response.PostUserDTO;
import com.crcm.core.response.RestResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RemotePostService
 * @Description：岗位信息远程调用服务
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/23
 **/
@Service
public class RemotePostService {

//    @Resource
//    private FeignPostService feignPostService;

    /**
     * 获取岗位用户信息
     * @param postId
     * @return
     */
    public RestResult<List<PostUserDTO>> getPostUsers(String postId){
//        RestResult<List<PostEmployeeResDTO>> result = feignPostService.getPostEmployees(postId);
//        if (result.getSuccess()) {
//            ArrayList<PostUserDTO> list = new ArrayList<>();
//            List<PostEmployeeResDTO> postEmployeeResDTOS = result.getData();
//            for (PostEmployeeResDTO postEmployeeResDTO : postEmployeeResDTOS) {
//                list.add(PostUserDTO.builder()
//                        .userId(postEmployeeResDTO.getEmployeeId())
//                        .userName(postEmployeeResDTO.getEmployeeName())
//                        .postId(postEmployeeResDTO.getPostId())
//                        .postName(postEmployeeResDTO.getPostName())
//                        .build());
//            }
//            return RestResult.succeed(list);
//        }
        return RestResult.failed();
    }

    /**
     * 获取岗位用户信息By postIds
     * @param postIds
     * @return
     */
    public List<PostUserDTO> getPostUsersByPostIds(String[] postIds) {
//        RestResult<List<PostEmployeeResDTO>> result = feignPostService.getPostEmployeesByPostIds(postIds);
//        if (!result.getSuccess() || result.getData() == null) {
//            throw new BusinessException(HttpStatus.HTTP_BAD_REQUEST, "获取岗位人员失败");
//        }
        ArrayList<PostUserDTO> list = new ArrayList<>();
//        List<PostEmployeeResDTO> postEmployeeResDTOS = result.getData();
//        for (PostEmployeeResDTO postEmployeeResDTO : postEmployeeResDTOS) {
//                    list.add(PostUserDTO.builder()
//                    .userId(postEmployeeResDTO.getEmployeeId())
//                    .userName(postEmployeeResDTO.getEmployeeName())
//                    .postId(postEmployeeResDTO.getPostId())
//                    .postName(postEmployeeResDTO.getPostName())
//                    .build());
//        }
        return list;
    }

}
