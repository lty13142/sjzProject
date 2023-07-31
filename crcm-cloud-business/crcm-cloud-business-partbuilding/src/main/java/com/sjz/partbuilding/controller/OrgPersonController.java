package com.sjz.partbuilding.controller;


import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.OrgPerson;
import com.sjz.partbuilding.service.OrgPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"组织人员关系表接口"})
@RestController
@RequestMapping("/orgPerson")
public class OrgPersonController extends BaseController {

    @Autowired
    private OrgPersonService orgPersonService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增组织人员关系", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody OrgPerson orgPerson){
        orgPersonService.saveOrgPerson(orgPerson);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改组织人员关系", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody OrgPerson orgPerson){
        orgPersonService.updateOrgPerson(orgPerson);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "删除组织人员关系", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody OrgPerson orgPerson){
        orgPersonService.deleteById(orgPerson.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id){
        return RestResult.succeed(orgPersonService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(OrgPerson orgPerson){
        return RestResult.succeed(orgPersonService.findList(orgPerson));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(OrgPerson orgPerson, PageVO pageVo){
        return RestResult.succeed(orgPersonService.findPage(UtilPage.initPage(pageVo),orgPerson));
    }

    /**
     * 获取组织机构树+组织班子成员
     * @return
     */
    @GetMapping("/getOrgPersonTree")
    public RestResult getOrgPersonTree(OrgPerson orgPerson){
        return RestResult.succeed(orgPersonService.getOrgPersonTree(orgPerson));
    }

    @GetMapping("/getPartyGroup")
    @ApiOperation(value = "获取所在组织所有党小组")
    public RestResult getPartyGroup(){
        return RestResult.succeed(orgPersonService.getPartyGroup());
    }
}

