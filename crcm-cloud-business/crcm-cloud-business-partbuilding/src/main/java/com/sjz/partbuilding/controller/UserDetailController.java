package com.sjz.partbuilding.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.cloud.start.data.utils.UtilPage;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.exception.SystemException;
import com.crcm.core.response.RestResult;
import com.crcm.core.vo.PageVO;
import com.sjz.partbuilding.model.entity.UserDetail;
import com.sjz.partbuilding.model.vo.DicContentVo;
import com.sjz.partbuilding.model.vo.UserDetailVo;
import com.sjz.partbuilding.service.UserDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * person党员接口
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/7/8 9:29
 */
@Api(tags = {"person党员接口"})
@RestController
@RequestMapping("/userDetail")
public class UserDetailController extends BaseController {

    @Autowired
    private UserDetailService userDetailService;

//    @PostMapping("/save")
//    @ApiOperation(value = "新增")
//    public RestResult save(@RequestBody UserDetail userDetail){
//        userDetailService.saveUserDetail(userDetail);
//        return RestResult.succeed();
//    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改")
    @SysLog(title = "修改person党员", serviceId = ServiceNameConstants.PARTBUILDING_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult edit(@RequestBody UserDetailVo userDetailVo) {
        userDetailService.updateUserDetail(userDetailVo);
        return RestResult.succeed();
    }

    @GetMapping("/getDetail")
    @ApiOperation(value = "查询用户详细信息")
    public RestResult getDetail(String userId) {
        return RestResult.succeed(userDetailService.getDetail(userId));
    }

    @GetMapping("/getPersonInfoDetail")
    @ApiOperation(value = "个人中心查询用户详细信息")
    public RestResult getPersonInfoDetail(String userId) {
        UserDetailVo userDetailVo = new UserDetailVo();
        try {
            userDetailVo = userDetailService.getPersonInfoDetail(userId);
        } catch (SystemException e) {
            return RestResult.failed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResult.succeed(userDetailVo);
    }


//    @PostMapping("/delete")
//    @ApiOperation(value = "删除")
//    public RestResult delete(@RequestBody UserDetail userDetai    l){
//        userDetailService.deleteById(userDetail.getId());
//        return RestResult.succeed();
//    }

    @GetMapping("/getById")
    @ApiOperation(value = "通过ID查询")
    public RestResult getById(String id) {
        return RestResult.succeed(userDetailService.findById(id));
    }

    @GetMapping("/getList")
    @ApiOperation(value = "查询列表")
    public RestResult getList(UserDetail userDetail) {
        return RestResult.succeed(userDetailService.findList(userDetail));
    }
//
//
//    @GetMapping("/getPage")
//    @ApiOperation(value = "分页查询")
//    public RestResult getPage(UserDetail userDetail,PageVo pageVo){
//        return RestResult.succeed(userDetailService.findPage(UtilPage.initPage(pageVo),userDetail));
//    }

    @GetMapping("getPersonManagementPage")
    @ApiOperation(value = "获取人员管理分页查询")
        public RestResult getPersonManagementPage(UserDetail userDetail, PageVO pageVo) {
        Page iPage = userDetailService.getPersonManagementPage(userDetail, UtilPage.initPage(pageVo));
        return RestResult.succeed(iPage);
    }

    @GetMapping("getStatisticsPersonManagementPage")
    @ApiOperation(value = "获取数据统计人员信息综合表")
    public RestResult getStatisticsPersonManagementPage(UserDetail userDetail, PageVO pageVo) {
        Page iPage = userDetailService.getStatisticsPersonManagementPage(userDetail, UtilPage.initPage(pageVo));
        return RestResult.succeed(iPage);
    }

    @GetMapping("/getLoginPartyType")
    @ApiOperation(value = "获取首页党员类别统计图")
    public RestResult getLoginPartyType(DicContentVo dicContent, String orgId) {
        List<Map<String, Object>> mapList = userDetailService.getLoginPartyType(dicContent,orgId);
        return RestResult.succeed(mapList);
    }

    @GetMapping("/getLoginEducation")
    @ApiOperation(value = "获取首页党员学历占比统计图")
    public RestResult getLoginEducation(DicContentVo dicContent) {
        List<Map<String, Object>> mapList = userDetailService.getLoginEducation(dicContent);
        return RestResult.succeed(mapList);
    }

    @GetMapping("/getEducationByOrgId")
    @ApiOperation(value = "根据党组织id查询党员学历占比统计图")
    public RestResult getLoginEducation(DicContentVo dicContent,String orgId) {
        Map<String, List<String>> mapList = userDetailService.getEducationByOrgId(dicContent,orgId);
        return RestResult.succeed(mapList);
    }

    @GetMapping("/getEducation")
    @ApiOperation(value = "根据党组织id查询党员学历占比统计图")
    public RestResult getEducation(DicContentVo dicContent,String orgId) {
        Map<String, List<String>> mapList = userDetailService.getEducationByOrgId(new DicContentVo(){{setDicCode("EDUCATION");}},orgId);
        return RestResult.succeed(mapList);
    }

    @GetMapping("/getLoginSex")
    @ApiOperation(value = "获取首页党员男女统计图")
    public RestResult getLoginSex(DicContentVo dicContent) {
        List<Map<String, Object>> mapList = userDetailService.getLoginSex(dicContent);
        return RestResult.succeed(mapList);
    }

    @GetMapping("/getSex")
    @ApiOperation(value = "获取首页党员男女统计图")
    public RestResult getSex(DicContentVo dicContent) {
        dicContent.setDicCode("SEX");
        List<Map<String, Object>> mapList = userDetailService.getLoginSex(dicContent);
        return RestResult.succeed(mapList);
    }

    @GetMapping("/getPersonInfo")
    @ApiOperation(value = "获取组织统计人员信息")
    public RestResult getLoginSex(String orgId) {
        List<Map<String, Object>> mapList = userDetailService.findListByOrgId(orgId);
        return RestResult.succeed(mapList);
    }
    @GetMapping("/getLoginAge")
    @ApiOperation(value = "获取首页年龄分布统计图")
    public RestResult getLoginAge(DicContentVo dicContent) {
        Map<String,Object> map = userDetailService.getLoginAge(dicContent);
        return RestResult.succeed(map);
    }
    @GetMapping("getLogOrg")
    @ApiOperation(value = "查询党员，入党积极分子，预备党员")
    public RestResult getLogOrg(String orgId){
        List<Map<String,Object>> maps=userDetailService.getLogOrg(orgId);
        return RestResult.succeed(maps);
    }

    @GetMapping("getLogPartyCount")
    @ApiOperation(value = "查询党委一张图党员，入党积极分子，预备党员")
    public RestResult getLogPartyCount(String orgId){
        List<Map<String,Object>> maps=userDetailService.getLogPartyCount(orgId);
        return RestResult.succeed(maps);
    }

    @GetMapping("/findPersonNum")
    @ApiOperation(value = "获取学历占比")
    public RestResult findPersonNum(String orgId) {
        return RestResult.succeed(userDetailService.findPersonNum(orgId));
    }

    @GetMapping("/findAgeByOrgId")
    @ApiOperation(value = "获取年龄占比")
    public RestResult findAgeByOrgId(String orgId) {
        return RestResult.succeed(userDetailService.findAgeByOrgId(orgId));
    }

    @GetMapping("/getLoginPartyOrg")
    @ApiOperation(value = "获取首页党组织类型占比")
    public RestResult getLoginPartyOrg(DicContentVo dicContent) {
        Map<String, Object> map = userDetailService.getLoginPartyOrg(dicContent);
        return RestResult.succeed(map);
    }

    @GetMapping("getLoginPartyAge")
    @ApiOperation(value = "获取首页党龄分布统计图")
    public RestResult getLoginPartyAge(DicContentVo dicContent) {
        Map<String,Object> map = userDetailService.getLoginPartyAge(dicContent);
        return RestResult.succeed(map);
    }

    @GetMapping("getPartyAge")
    @ApiOperation(value = "总支党龄分布雷达图")
    public RestResult getPartyAge(String orgId) {
        Map<String,Object> map =userDetailService.getPartyAge(orgId);
        return RestResult.succeed(map);
    }

    @GetMapping("getPersonByOrgId")
    @ApiOperation(value = "根据orgId获取底下人员")
    public RestResult getPersonByOrgId(UserDetail userDetail) {
        List<UserDetail> personByOrgId = userDetailService.getPersonByOrgId(userDetail);
        return RestResult.succeed(personByOrgId);
    }

    @GetMapping("getPartyActivistsDetail")
    @ApiOperation(value = "获取入党积极分子信息")
    public RestResult getPartyActivistsDetail(String userId) {
        UserDetailVo vo = userDetailService.getPartyActivistsDetail(userId);
        return RestResult.succeed(vo);
    }

    /**
     * 导出党员数据
     * @param userDetail
     * @return
     */
    @GetMapping("/exportPartyMember")
    public RestResult exportPartyMember(UserDetail userDetail, HttpServletResponse response){
        userDetailService.exportPartyMember(userDetail,response);
        return RestResult.succeed();
    }

    /**
     * 获取记录图片
     * @param id
     * @return
     */
    @GetMapping("/getRecordPic")
    public RestResult getRecordPic(String id){
        return RestResult.succeed(userDetailService.getRecordPic(id));
    }

    /**
     * 人员实力统计
     * @param userDetail
     * @return
     */
    @GetMapping("/countPersonStrength")
    @ApiOperation(value = "人员实力统计")
    public   RestResult countPersonnelStrength(UserDetail userDetail,PageVO pageVo){
        return RestResult.succeed(userDetailService.countPersonnelStrength(userDetail,UtilPage.initPage(pageVo)));
    }


}

