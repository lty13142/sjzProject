package com.crcm.admin.controller;

import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.model.dto.user.UserAvatarUpdateDTO;
import com.crcm.admin.model.dto.user.UserEnabledDTO;
import com.crcm.admin.model.dto.user.UserQueryDTO;
import com.crcm.admin.model.entity.SysRole;
import com.crcm.admin.model.entity.SysUser;
import com.crcm.admin.model.vo.*;
import com.crcm.admin.service.UserService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.cloud.start.sso.annation.Inner;
import com.crcm.core.base.BaseController;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = {"用户接口"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/add")
    @ApiOperation(value = "新增用户")
    @SysLog(title = "新增用户", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.INSERT)
    public RestResult<Boolean> add(@Valid @RequestBody UserDataVO t) {
        return RestResult.succeed(userService.addUser(t));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改用户")
    @SysLog(title = "修改用户", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> update(@Valid @RequestBody UserDataVO t) {
        return RestResult.succeed(userService.updateUserById(t));
    }

    @PostMapping("/updateEnabled")
    @ApiOperation(value = "修改用户启用状态")
    @SysLog(title = "修改用户启用状态", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Boolean> update(@Valid @RequestBody UserEnabledDTO enabledDTO) {
        return RestResult.succeed(userService.updateEnabled(enabledDTO));
    }

    @PostMapping("/updateAvatar")
    @ApiOperation(value = "修改用户头像")
    @SysLog(title = "修改用户头像", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Object> updateAvatar(@RequestBody UserAvatarUpdateDTO t) {
        userService.updateAvatar(t);
        return RestResult.succeed();
    }

    @PostMapping("/resetPwd")
    @ApiOperation(value = "重置用户密码")
    @SysLog(title = "重置用户密码", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<String> resetPwd(@RequestBody PasswordVO t) {
        String result = userService.resetPassword(t);
        return StringUtils.isBlank(result) ? RestResult.succeed() : RestResult.failed(result);
    }

    @PostMapping("/editPwd")
    @ApiOperation(value = "用户修改密码")
    @SysLog(title = "用户修改密码", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<String> editPwd(@RequestBody PasswordVO t) {
        String result = userService.updatePassword(t);
        return StringUtils.isBlank(result) ? RestResult.succeed() : RestResult.failed(result);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除用户")
    @SysLog(title = "删除用户", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.DELETE)
    public RestResult<Boolean> delete(@PathVariable("id") String id) {
        return RestResult.succeed(userService.deleteById(id));
    }


//    @GetMapping("/getPageUser")
//    @ApiOperation(value = "分页查询监管用户信息")
//    public RestResult<PageT<UserVO>> getPageUser(UserQueryDTO queryDTO, PageT<UserVO> page) {
//        return RestResult.succeed(userService.findPageUser(page, queryDTO));
//    }

    @GetMapping("/getList")
    @ApiOperation(value = "用户列表查询")
    public RestResult<List<SysUser>> getList(UserQueryDTO queryDTO) {
        return RestResult.succeed(userService.findList(queryDTO));
    }

    @GetMapping("/getInfo")
    @ApiOperation("登录用户信息获取")
    public RestResult<UserInfoVO> getUserInfo() {
        return RestResult.succeed(userService.findUserInfo());
    }


    @PostMapping("/editInfo")
    @ApiOperation("用户信息修改")
    @SysLog(title = "用户信息修改", serviceId = ServiceNameConstants.ADMIN_SERVICE, businessType = BusinessType.UPDATE)
    public RestResult<Object> editUserInfo(@Valid @RequestBody UserBaseInfoVO userInfoVo) {
        userService.updateUserInfo(userInfoVo);
        return RestResult.succeed();
    }

    @GetMapping("/getBaseInfo")
    @ApiOperation("获取用户账号基本信息")
    public RestResult<UserBaseInfoVO> getUserBaseInfo(String id) {
        return RestResult.succeed(userService.findUserBaseInfo(id));
    }

    @GetMapping("/role/{id}")
    @ApiOperation("获取用户角色")
    public RestResult<List<SysRole>> getUserRoles(@PathVariable("id") String id) {
        return RestResult.succeed(userService.getUserRoles(id));
    }

    @Inner
    @GetMapping("/account")
    @ApiOperation("通过账户获取用户")
    public RestResult<UserAccountDTO> getUserByAccount(String username) {
        return RestResult.succeed(userService.findUserByUsername(username));
    }

    @Inner
    @GetMapping("/phone")
    @ApiOperation("通过手机号获取用户")
    public RestResult<UserAccountDTO> getUserByPhone(String phoneNum) {
        return RestResult.succeed(userService.findUserByPhone(phoneNum));
    }

    @Inner
    @PostMapping("/updateLoginTime")
    @ApiOperation(value = "修改用户登录时间")
    public RestResult<Object> updateLoginTime(@RequestBody String userName) {
        userService.updateLoginTime(userName);
        return RestResult.succeed();
    }

//    @GetMapping("/syncDataHandle")
//    @ApiOperation(value = "同步数据处理(临时使用)")
//    public RestResult<Boolean> encryptPwd() {
//        return RestResult.succeed(userService.syncDataHandle());
//    }


    @GetMapping("/getPageCompanyUser")
    @ApiOperation(value = "分页查询用户信息")
    public RestResult<PageT<UserVO>> getPageCompanyUser(UserQueryDTO queryDTO, PageT<UserVO> page) {
        return RestResult.succeed(userService.getPageCompanyUser(page, queryDTO));
    }

//    @GetMapping("/getPageDriverUser")
//    @ApiOperation(value = "分页查询驾驶员用户信息")
//    public RestResult<PageT<UserVO>> getPageDriverUser(UserQueryDTO queryDTO, PageT<UserVO> page) {
//        return RestResult.succeed(userService.getPageDriverUser(page, queryDTO));
//    }
//
//    @GetMapping("/getPageDepartmentUser")
//    @ApiOperation(value = "分页查询医疗科室收集用户信息")
//    public RestResult<PageT<UserVO>> getPageDepartmentUser(UserQueryDTO queryDTO, PageT<UserVO> page) {
//        return RestResult.succeed(userService.getPageDepartmentUser(page, queryDTO));
//    }

    @GetMapping("/pwdCheck")
    @ApiOperation(value = "弱口令检测")
    public RestResult<Boolean> pwdCheck() {
        return RestResult.succeed(userService.pwdCheck());
    }

    @GetMapping("/getDjInfo")
    @ApiOperation("登录用户信息获取--党建")
    public RestResult<UserInfoPartyVO> getDjInfo(String code) {
        return RestResult.succeed(userService.getDjInfo(code));
    }

    @Inner
    @GetMapping("/getByOpenId")
    @ApiOperation("微信唯一id获取用户信息--微信授权登录")
    public RestResult<UserAccountDTO> getByOpenId(String unionId) {
        return RestResult.succeed(userService.getByOpenId(unionId));
    }


    @GetMapping("/getUserListByRole")
    @ApiOperation(value = "查询用户信息根据用户角色")
    public RestResult<List<SysUser>> getUserListByRole(String roleValue) {
        return RestResult.succeed(userService.getUserListByRole(roleValue));
    }

    @GetMapping("/getTownUserList")
    @ApiOperation(value = "查询镇区用户")
    public RestResult<List<SysUser>> getTownUserList() {
        return RestResult.succeed(userService.getTownUserList());
    }

    @GetMapping("/getDistrictUserList")
    @ApiOperation(value = "查询管区用户")
    public RestResult<List<SysUser>> getDistrictUserList() {
        return RestResult.succeed(userService.getDistrictUserList());
    }

    @GetMapping("/getVillageUserList")
    @ApiOperation(value = "查询村用户")
    public RestResult<List<SysUser>> getVillageUserList() {
        return RestResult.succeed(userService.getVillageUserList());
    }

    @Inner
    @PostMapping("/getUserByOrgId")
    @ApiOperation(value = "根据部门id，岗位ID查询人")
    RestResult<SysUser> getUserByOrgId(@RequestBody UserBaseInfoVO userInfoVo){
        return RestResult.succeed(userService.getUserByOrgId(userInfoVo));
    }

}

