package com.crcm.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.model.dto.user.UserAvatarUpdateDTO;
import com.crcm.admin.model.dto.user.UserEnabledDTO;
import com.crcm.admin.model.dto.user.UserQueryDTO;
import com.crcm.admin.model.entity.SysRole;
import com.crcm.admin.model.entity.SysUser;
import com.crcm.admin.model.vo.*;
import com.crcm.cloud.start.data.mybatis.bean.PageT;

import java.io.Serializable;
import java.util.List;

public interface UserService extends IService<SysUser> {

    boolean addUser(UserDataVO t);

    boolean updateUserById(UserDataVO t);

    boolean deleteById(Serializable id);

    SysUser findById(Serializable id);

    Page<SysUser> findPage(Page<SysUser> page, SysUser t);

    UserAccountDTO findUserByUsername(String username);

    UserAccountDTO findUserByPhone(String phoneNum);

    List<SysUser> findList(UserQueryDTO queryDTO);

    UserInfoVO findUserInfo();

    PageT<UserVO> findPageUser(PageT<UserVO> page, UserQueryDTO queryDTO);

    int findCountByUserName(String userName);

    String resetPassword(PasswordVO vo);

    void checkUserPassword(PasswordVO vo);

    void updateUserInfo(UserBaseInfoVO userInfoVo);

    /**
     * 修改用户头像
     *
     * @param sysUser 用户头像
     */
    void updateAvatar(UserAvatarUpdateDTO sysUser);

    /**
     * 查询用户基础信息
     *
     * @param id id
     * @return 用户基础信息
     */
    UserBaseInfoVO findUserBaseInfo(String id);

    /**
     * 修改密码
     *
     * @param t 密码
     */
    String updatePassword(PasswordVO t);

    /**
     * 获取用户角色
     *
     * @param id id
     * @return 用户角色
     */
    List<SysRole> getUserRoles(String id);

    /**
     * 修改用户登录时间
     *
     * @Author GZL
     * @Date 2023/2/2 9:23
     * @Param userName 用户名
     **/
    void updateLoginTime(String userName);

    /**
     * 用户停用/启用
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/2/13 9:54
     * @Param enabledDTO enabledDTO
     **/
    boolean updateEnabled(UserEnabledDTO enabledDTO);

    /**
     * 分页查询企业用户信息
     *
     * @return 企业用户信息
     * @Author GZL
     * @Date 2023/2/13 11:09
     * @Param page 分页信息
     * @Param queryDTO 条件
     **/
    PageT<UserVO> getPageCompanyUser(PageT<UserVO> page, UserQueryDTO queryDTO);

    /**
     * 同步数据处理
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/2/13 15:33
     **/
    boolean syncDataHandle();

    /**
     * 分页查询驾驶员用户信息
     *
     * @return 驾驶员用户信息
     * @Author GZL
     * @Date 2023/2/14 15:25
     * @Param page 分页信息
     * @Param queryDTO 条件
     **/
    PageT<UserVO> getPageDriverUser(PageT<UserVO> page, UserQueryDTO queryDTO);

    /**
     * 分页查询医疗科室收集用户信息
     *
     * @return 医疗科室收集用户信息
     * @Author GZL
     * @Date 2023/2/14 15:34
     * @Param page 分页信息
     * @Param queryDTO 条件
     **/
    PageT<UserVO> getPageDepartmentUser(PageT<UserVO> page, UserQueryDTO queryDTO);

    /**
     * 弱口令检测
     * @Author GZL
     * @Date 2023/3/30 17:49
     * @return 是否是弱口令
     **/
    Boolean pwdCheck();

    UserInfoPartyVO getDjInfo(String code);

    /**
     * 通过微信openId查询用户信息
     * @param openId
     * @return
     */
	SysUser findUserByOpenId(String openId);

    /**
     * 微信唯一id获取用户行
     * @param unionId
     * @return
     */
    UserAccountDTO getByOpenId(String unionId);

    /**
     * 根据角色获取用户
     * @param roleValue
     * @return
     */
    List<SysUser> getUserListByRole(String roleValue);
    /**
     * 获取镇用户
     * @param
     * @return
     */
    List<SysUser> getTownUserList();
    /**
     * 获取管区用户
     * @param
     * @return
     */
    List<SysUser> getDistrictUserList();
    /**
     * 获取村用户
     * @param
     * @return
     */
    List<SysUser> getVillageUserList();

    SysUser getUserByOrgId(UserBaseInfoVO userInfoVo);
}
