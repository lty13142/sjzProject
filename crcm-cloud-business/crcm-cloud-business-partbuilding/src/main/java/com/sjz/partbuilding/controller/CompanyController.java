package com.sjz.partbuilding.controller;


import com.crcm.core.base.BaseController;
import com.crcm.core.dto.TreeView;
import com.crcm.core.response.RestResult;
import com.sjz.partbuilding.model.entity.Org;
import com.sjz.partbuilding.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司controller
 *
 * @author zzyt
 * @version 1.0
 */
@Api(tags = {"公司表接口"})
@RestController
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Resource
    private OrgService orgService;

    @GetMapping("/getPartyManagementByCompany")
    @ApiOperation(value = "根据公司获取党组织")
    public RestResult getPartyManagementByCompany() {
        List<TreeView> treeList = orgService.getPartyManagementByCompany();
        return RestResult.succeed(treeList);
    }
}

