package com.crcm.develop.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.develop.core.base.BaseController;
import com.crcm.develop.core.conf.security.service.TokenService;
import com.crcm.develop.core.base.RestResult;
import com.crcm.develop.system.entity.SysUser;
import com.crcm.develop.system.service.SysUserService;
import com.crcm.develop.system.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * 用户
 *
 * @author diaoy
 * @date 2020-03-31 18:47:48
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController extends BaseController {

  private final SysUserService sysUserService;
  private final TokenService tokenService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param sysUser 用户
   * @return
   */
  @GetMapping("/page")
  public RestResult getUserEntityPage(Page page, SysUser sysUser) {
    return  RestResult.succeed(sysUserService.page(page,Wrappers.query(sysUser)));
  }


  /**
   * 通过id查询用户
   * @param id id
   * @return R
   */
  @GetMapping("/{id}")
  public RestResult getById(@PathVariable("id") Integer id){
    return RestResult.succeed(sysUserService.getById(id));
  }

  /**
   * 新增用户
   * @param sysUser 用户
   * @return R
   */
  @PostMapping("/save")
  public RestResult save(@RequestBody SysUser sysUser){
    return RestResult.succeed(sysUserService.save(sysUser));
  }

  /**
   * 修改用户
   * @param sysUser 用户
   * @return R
   */
  @PutMapping("/update")
  public RestResult updateById(@RequestBody SysUser sysUser){
    return RestResult.succeed(sysUserService.updateById(sysUser));
  }

  /**
   * 通过id删除用户
   * @param id id
   * @return R
   */
  @DeleteMapping("/delete/{id}")
  public RestResult removeById(@PathVariable Integer id){
    return RestResult.succeed(sysUserService.removeById(id));
  }

  /**
   * 查询用户信息
   * @return R
   */
  @GetMapping("/info")
  public RestResult getById(HttpSession session){
    String userName = tokenService.getUserName(request);
    SysUser user = sysUserService.selectUserByUserName(userName);
    UserInfoVo userInfoVo = new UserInfoVo();
    if (user != null) {
      userInfoVo.setId(String.valueOf(user.getId()));
      userInfoVo.setUsername(user.getUsername());
    }
    return RestResult.succeed(userInfoVo);
  }
}
