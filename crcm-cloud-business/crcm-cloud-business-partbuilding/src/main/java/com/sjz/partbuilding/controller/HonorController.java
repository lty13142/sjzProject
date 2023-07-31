package com.sjz.partbuilding.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.Honor;
import com.sjz.partbuilding.model.entity.UserDetail;
import com.sjz.partbuilding.model.vo.HonorVo;
import com.sjz.partbuilding.service.HonorService;
//import com.zzyt.core.basic.beans.UserContext;
//import com.zzyt.core.basic.utils.RestResult;
//import com.zzyt.model.entity.system.user.User;
//import com.zzyt.service.system.user.UserService;
import com.sjz.partbuilding.service.UserDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"荣誉表接口"})
@RestController
@RequestMapping("/honor")
public class HonorController extends BaseController {

    @Autowired
    private HonorService honorService;

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    @SysLog(title = "新增荣誉表", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.INSERT)
    public RestResult save(@RequestBody Honor honor){
        String userId = SecurityUtil.getCurrentUser().getUserId();
        UserDetail byId = userDetailService.findByUserId(userId);
        honor.setComBy(byId.getOrgId());
        honorService.saveHonor(honor);
        return RestResult.succeed();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改荣誉表", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody Honor honor){
        honorService.updateHonor(honor);
        return RestResult.succeed();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    @SysLog(title = "刪除荣誉表", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.DELETE)
    public RestResult delete(@RequestBody Honor honor){
        honorService.deleteById(honor.getId());
        return RestResult.succeed();
    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id){

        return RestResult.succeed(honorService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(Honor honor){
        return RestResult.succeed(
                honorService.findList(honor));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "分页查询")
    public RestResult getPage(Honor honor, PageVO pageVo){
        return RestResult.succeed(honorService.findPage(UtilPage.initPage(pageVo),honor));
    }

    @GetMapping("findGroupHonorPage")
    @ApiOperation(value = "分页查询集体荣誉")
    public RestResult getPersonManagementPage(PageVO pageVo, HonorVo honor) {
        Page iPage = honorService.findGroupHonorPage(UtilPage.initPage(pageVo),honor);
        return RestResult.succeed(iPage);
    }

    @GetMapping("findPersonHonorPage")
    @ApiOperation(value = "分页查询个人荣誉")
    public RestResult findPersonHonorPage(PageVO pageVo,HonorVo honor) {
        Page iPage = honorService.findPersonHonorPage(UtilPage.initPage(pageVo),honor);
        return RestResult.succeed(iPage);
    }

}

