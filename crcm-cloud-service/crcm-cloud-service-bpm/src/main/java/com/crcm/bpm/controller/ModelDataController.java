package com.crcm.bpm.controller;

import com.crcm.bpm.domain.vo.ModelDataQueryVO;
import com.crcm.bpm.service.ModelDesignDataService;
import com.crcm.core.response.RestResult;
import com.crcm.cloud.start.log.annation.SysLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ModelDataController
 * @Description：模型设计数据来源接口
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/10/23
 **/
@RestController
@RequestMapping("/flow/model/data")
public class ModelDataController {

    @Autowired
    private ModelDesignDataService modelDesignDataService;

    @SysLog(title = "获取用户")
    @GetMapping("/listUser")
    @ApiOperation(value = "获取用户")
    public RestResult getUserList(ModelDataQueryVO queryVO){

        return RestResult.succeed(modelDesignDataService.getUserList(queryVO));
    }

    @SysLog(title = "获取公司树")
    @GetMapping("/companyTree")
    @ApiOperation(value = "获取公司树")
    public RestResult getCompanyTree(ModelDataQueryVO queryVO) {
        return  RestResult.succeed(modelDesignDataService.getCompanyTree(queryVO));
    }

    @SysLog(title = "获取公司部门")
    @GetMapping("/listDept")
    @ApiOperation(value = "获取公司部门")
    public RestResult getDeptList(String companyId) {
        return RestResult.succeed(modelDesignDataService.getDeptList(companyId));
    }

    @SysLog(title = "获取公司部门岗位")
    @GetMapping("/listPost")
    @ApiOperation(value = "获取公司部门岗位")
    public RestResult getPostList(String deptId) {
        return RestResult.succeed(modelDesignDataService.getPostList(deptId));
    }
}
