package com.sjz.partbuilding.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.core.base.BaseController;
import com.crcm.core.exception.SystemException;
import com.crcm.core.response.RestResult;
import com.sjz.partbuilding.model.entity.Org;
import com.sjz.partbuilding.model.vo.org.OrgListCountVo;
import com.sjz.partbuilding.model.vo.org.OrgPersonVo;
import com.sjz.partbuilding.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzyt
 * @version 1.0
 * @date 2020/7/10 10:06
 */
@Api(tags = {"部门组织表接口"})
@RestController
@RequestMapping("/org")
public class OrgController extends BaseController {

    @Resource
    private OrgService orgService;

    @GetMapping("/getOrgUserTree")
    @ApiOperation(value = "查询组织人员树")
    public RestResult getOrgUserTree(Org t) {
        return RestResult.succeed(orgService.findOrgUserTree(t));
    }

//    @PostMapping("/save")
//    @ApiOperation(value = "新增")
//    public RestResult save(@RequestBody Org t) {
//        try {
//            orgService.saveOrg(t);
//        } catch (SystemException e) {
////            return UtilRest.failure(Integer.valueOf(e.getCode()), e.getMsg());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return RestResult.succeed();
//    }
//
//    @PostMapping("/update")
//    @ApiOperation(value = "修改")
//    public RestResult update(@RequestBody Org t) {
//        orgService.updateOrg(t);
//        return RestResult.succeed();
//    }
//
//    @PostMapping("/delete")
//    @ApiOperation(value = "删除")
//    public RestResult delete(@RequestBody Org t) {
//        orgService.deleteById(t.getId());
//        return RestResult.succeed();
//    }
//
//    @GetMapping("/getById")
//    @ApiOperation(value = "通过ID查询")
//    public RestResult getById(String id) {
//        return RestResult.succeed(orgService.findById(id));
//    }
//
//    @GetMapping("/getPage")
//    @ApiOperation(value = "分页查询")
//    public RestResult getPage(Org t, Page page) {
//        return RestResult.succeed(orgService.findPage(page, t));
//    }
//
    @GetMapping("/getTree")
    @ApiOperation(value = "查询组织树")
    public RestResult getTree(Org t) {
        return RestResult.succeed(orgService.findTree(t));
    }
//
    @GetMapping("/getList")
    @ApiOperation(value = "查询组织列表")
    public RestResult getList(Org t) {
        return RestResult.succeed(orgService.findList(t));
    }
//
//    @GetMapping("/getTreeOrg")
//    @ApiOperation(value = "查询类型为组织的树")
//    public RestResult getTreeOrg(Org t) {
////        List<TreeView> treeOrg = orgService.findTreeOrg(t);
//        return RestResult.succeed(orgService.findTreeOrg(t));
//    }
//
//    @GetMapping("/getOrgPersonTree")
//    @ApiOperation(value = "查询组织及其下的人员")
//    public RestResult findOrgPersonTree(OrgPersonVo t) {
//        List<OrgPersonVo> orgList = orgService.findOrgPersonTree(t);
//        return RestResult.succeed(orgList);
//    }
//
//    @GetMapping("/getOrgNumberByPid")
//    @ApiOperation(value = "根据上级组织id得到组织编号")
//    public RestResult getOrgNumberByPid(String id, String updateId) {
//        String number = orgService.getOrgNumberByPid(id, updateId);
//        return RestResult.succeed(number);
//    }
//
//    @GetMapping("/judgeDirectlyBranchById")
//    @ApiOperation(value = "判断是否是地属支部")
//    public RestResult judgeDirectlyBranchById(String id) {
//        boolean flag = orgService.judgeDirectlyBranchById(id);
//        return RestResult.succeed(flag);
//    }
//
//    @GetMapping("/getBranchOrgIdsByCurrentLogin")
//    @ApiOperation(value = "根据当前登录人获取组织及其附属ids")
//    public RestResult getBranchOrgIdsByCurrentLogin() {
//        List<String> ids = orgService.getBranchOrgIdsByCurrentLogin();
//        return RestResult.succeed(ids);
//    }
//
    @GetMapping("/getBranchManagementTree")
    @ApiOperation(value = "获取支部管理党组织树")
    public RestResult getBranchManagementTree(Org org) {
        List<Org> orgList = orgService.getBranchManagementTree(org);
        return RestResult.succeed(orgList);
    }
//
//    @GetMapping("/getOrgHierarchyByCurrentLogin")
//    @ApiOperation(value = "根据当前登录人获取组织层级结构")
//    public RestResult getOrgHierarchyByCurrentLogin() {
//        List<Org> orgList = orgService.getOrgHierarchyByCurrentLogin();
//        return RestResult.succeed(orgList);
//    }
//
//    @GetMapping("getLoginOrgList")
//    @ApiOperation(value = "获取大屏组织列表")
//    public RestResult getLoginOrgList() {
//        List<Org> orgList = orgService.getLoginOrgList();
//        return RestResult.succeed(orgList);
//    }
//
//    @GetMapping("getLoginCount")
//    @ApiOperation(value = "获取大屏组织列表")
//    public RestResult getLoginCount(){
//        OrgListCountVo maps= orgService.getLoginCount();
//        return RestResult.succeed(maps);
//    }

    @GetMapping("/getOrgInfo")
    @ApiOperation(value = "获取三会一课信息")
    public RestResult getOrgInfo(String orgId) {
        return RestResult.succeed(orgService.getOrgInfo(orgId));
    }
//
//    @GetMapping("/getOrgTreeAndUsers")
//    @ApiOperation(value = "查询组织树及其下的人员")
//    public RestResult getOrgTreeAndUsers(OrgPersonVo t) {
//        OrgPersonVo orgPersonVo = orgService.getOrgTreeAndUsers(t);
//        return RestResult.succeed(orgPersonVo);
//    }
//
//    @GetMapping("/findOrgTreeAndUsers")
//    @ApiOperation(value = "根据公司层级关系查询组织树及其下的人员")
//    public RestResult findOrgTreeAndUsers(OrgPersonVo t) {
//        return RestResult.succeed(orgService.findOrgTreeAndUsers(t));
//    }
//
    @GetMapping("/getBranchManagement")
    @ApiOperation(value = "获取固定组织数")
    public RestResult getBranchManagement() {
        Org orgList = orgService.getBranchManagement();
        return RestResult.succeed(orgList);
    }
//
//    @GetMapping("/getIdsByOrgId")
//    @ApiOperation(value = "获取当前组织的子组织id集合")
//    public RestResult getIdsByOrgId(String orgId,String type) {
//        ArrayList<String> list = orgService.getIdsByOrgId(orgId, type);
//        return RestResult.succeed(list);
//    }

}

