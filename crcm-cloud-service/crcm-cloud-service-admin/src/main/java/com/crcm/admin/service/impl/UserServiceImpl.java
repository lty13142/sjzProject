package com.crcm.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.api.dto.res.ResourceDTO;
import com.crcm.admin.api.dto.res.RoleDTO;
import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.mapper.UserMapper;
import com.crcm.admin.model.dto.user.UserAvatarUpdateDTO;
import com.crcm.admin.model.dto.user.UserEnabledDTO;
import com.crcm.admin.model.dto.user.UserQueryDTO;
import com.crcm.admin.model.entity.*;
import com.crcm.admin.model.vo.*;
import com.crcm.admin.model.vo.party.MenuVo;
import com.crcm.admin.service.*;
import com.crcm.admin.utils.PasswordValidUtil;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.sso.domain.AuthUser;
import com.crcm.cloud.start.sso.utils.BpwdEncoderUtil;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.SystemBaseConstants;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.constant.enums.BusinessStatusEnum;
import com.crcm.core.constant.enums.ResultCode;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import com.crcm.file.api.feign.RemoteFileService;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    private final MenuService menuService;
    private final UserRoleService userRoleService;
    private final SysSettingsService sysSettingsService;
    private final UserDetailService userDetailService;
    private final ResourceService resourceService;
    private final RemoteFileService remoteFileService;

    @Override
    public boolean addUser(UserDataVO t) {
        // 用户名未输入
        if (StringUtils.isBlank(t.getUsername())) {
            throw new CustomException(ResultCode.USER_NAME_CANNOT_BE_NULL.code, ResultCode.USER_NAME_CANNOT_BE_NULL.msg);
        }
        // 用户名已存在
        if (this.findCountByUserName(t.getUsername()) > 0) {
            throw new CustomException(ResultCode.USER_ALREADY_EXISTS.code, ResultCode.USER_ALREADY_EXISTS.msg);
        }

        // 电话号已存在
        if (this.findCountByUserPhone(t.getPhone()) > 0) {
            throw new CustomException(ResultCode.USER_PHONE_ALREADY_EXISTS.code, ResultCode.USER_PHONE_ALREADY_EXISTS.msg);
        }

        SysUser user = new SysUser();
        BeanUtil.copyProperties(t, user);
        user.setEnabled(SystemConstant.ENABLE_STATUS.ENABLE);
        user.setLocked(SystemConstant.LOCK_STATUS.UN_LOCK);
        user.setExpired(SystemConstant.YES_OR_NO.NO);
        user.setLastActiveTime(LocalDateTime.now());
        // 设置登录密码
        SysSettings valueByCode = sysSettingsService.getValueByCode(SystemBaseConstants.SYSTEM_DEFAULT_PASSWORD);
        user.setPassword(Objects.nonNull(valueByCode) && StringUtils.isNotBlank(valueByCode.getValue()) ?
                valueByCode.getValue() : SystemBaseConstants.SYSTEM_DEFAULT_PASSWORD_VALUE);
        // 密码加密
        user.setMemo(user.getPassword());
        user.setPassword(BpwdEncoderUtil.bCryptPassword(user.getPassword()));
        boolean result = this.save(user);
        if (result) {
            // 添加用户详情
            SysUserDetail userDetail = new SysUserDetail();
            userDetail.setUserId(user.getId());
            userDetail.setName(t.getNickName());
            userDetail.setEmail(t.getEmail());
            userDetail.setComments(t.getComment());
            user.setPhone(t.getPhone());
            userDetailService.saveUserDetail(userDetail);
            // 添加用户角色
            if (StringUtils.isNotBlank(t.getRoles())) {
                List<SysUserRole> userRoles = new ArrayList<>();
                for (String roleId : t.getRoles().split(",")) {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setRoleId(Long.valueOf(roleId));
                    sysUserRole.setUserId(user.getId());
                    userRoles.add(sysUserRole);
                }
                userRoleService.saveBatch(userRoles);
            }
        }
        if(2==t.getIsBuilding()){
            //回写到智慧党建人员表
            this.baseMapper.updateDjUserDetail(user.getId(),t.getPartUserId());
        }
        return result;
    }

    @Override
    public int findCountByUserName(String userName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        return this.baseMapper.selectCount(queryWrapper);
    }

    public int findCountByUserPhone(String phone) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        return this.baseMapper.selectCount(queryWrapper);
    }

    @Override
    public String resetPassword(PasswordVO t) {
        if (StringUtils.isNotBlank(t.getNewPassword())) {
            if (!PasswordValidUtil.evalPassword(t.getNewPassword())) {
                return ResultCode.USER_NOT_PASSWORD.msg;
            }
            SysUser sysUser = new SysUser();
            sysUser.setId(t.getUserId());
            sysUser.setMemo(t.getNewPassword());
            sysUser.setPassword(new BCryptPasswordEncoder().encode(t.getNewPassword()));
            sysUser.setLastUpdatePwdTime(LocalDateTime.now());
            this.baseMapper.updateById(sysUser);
        }
        return null;
    }

    @Override
    public void checkUserPassword(PasswordVO vo) {
        // 用户ID为空则抛出自定义异常
        if (vo.getUserId() == null) {
            throw new CustomException(ResultCode.USER_NO_FOUNT.code, ResultCode.USER_NO_FOUNT.msg);
        }
        SysUser sysUser = this.baseMapper.selectById(vo.getUserId());
        // 未查询到用户则抛出自定义异常
        if (sysUser == null) {
            throw new CustomException(ResultCode.USER_NO_FOUNT.code, ResultCode.USER_NO_FOUNT.msg);
        }
        String password = sysUser.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(vo.getPassword(), password);
        // 两次密码不一致则校验失败
        if (!matches) {
            throw new CustomException(ResultCode.USER_PWD_CHECK_FILD.code, ResultCode.USER_PWD_CHECK_FILD.msg);
        }
    }


    @Override
    public boolean updateUserById(UserDataVO t) {
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(t, sysUser);
        // 用户名已存在
        UserAccountDTO userByUsername = this.findUserByUsername(t.getUsername());
        if (Objects.nonNull(userByUsername) && !Objects.equals(t.getId(), userByUsername.getId())) {
            throw new CustomException(ResultCode.USER_ALREADY_EXISTS.code, ResultCode.USER_ALREADY_EXISTS.msg);
        }
        // 电话号已存在
        UserAccountDTO userByPhone = this.findUserByPhone(t.getPhone());
        if (Objects.nonNull(userByPhone) && !Objects.equals(t.getId(), userByPhone.getId())) {
            throw new CustomException(ResultCode.USER_PHONE_ALREADY_EXISTS.code, ResultCode.USER_PHONE_ALREADY_EXISTS.msg);
        }
        if(2==t.getIsBuilding()){
            //回写到智慧党建人员表
            this.baseMapper.updateDjUserDetail(t.getId(),t.getPartUserId());
        }

        return this.updateById(sysUser);
    }

    @Override
    public boolean deleteById(Serializable id) {
        return this.removeById(id);
    }

    @Override
    public SysUser findById(Serializable id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public Page<SysUser> findPage(Page<SysUser> page, SysUser t) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(t.getUsername() != null, SysUser::getUsername, t.getUsername())
                .eq(t.getEnabled() != null, SysUser::getEnabled, t.getEnabled())
                .eq(t.getLocked() != null, SysUser::getLocked, t.getLocked());
        return this.page(page, wrapper);
    }

    @Override
    public UserAccountDTO findUserByUsername(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        SysUser user = this.baseMapper.selectOne(queryWrapper);
        return composeAuthority(user);
    }

    public UserAccountDTO findUserById(String id) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getId, id);
        SysUser user = this.baseMapper.selectOne(queryWrapper);
        return composeAuthority(user);
    }


    @Override
    public UserAccountDTO findUserByPhone(String phoneNum) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getPhone, phoneNum);
        SysUser user = this.baseMapper.selectOne(queryWrapper);
        return composeAuthority(user);
    }

    /**
     * 组装用户账户角色
     *
     * @param user 系统用户信息
     * @return 用户账户角色信息
     */
    private UserAccountDTO composeAuthority(SysUser user) {
        if (null != user) {
            UserAccountDTO account = BeanUtil.copyProperties(user, UserAccountDTO.class);
            List<SysRole> userRoles = userRoleService.findUserRoles(account.getId());
            if (CollectionUtil.isNotEmpty(userRoles)) {
                List<RoleDTO> authorities = userRoles.stream().map(r -> BeanUtil.copyProperties(r, RoleDTO.class)).collect(Collectors.toList());
                account.setRoles(authorities);
            }
            List<SysResource> userResources = resourceService.findUserResources(account.getId());
            if (CollectionUtil.isNotEmpty(userResources)) {
                account.setResources(userResources.stream()
                        .filter(v -> Objects.equals(SystemConstant.RESOURCE_AUTH_TYPE.BY_CODE, v.getAuthType()))
                        .map(v -> BeanUtil.copyProperties(v, ResourceDTO.class)).collect(Collectors.toList()));
            }
            return account;
        }
        return null;
    }

    @Override
    public List<SysUser> findList(UserQueryDTO queryDTO) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public UserInfoVO findUserInfo() {
        AuthUser currentUser = SecurityUtil.getCurrentUser();
        if (null == currentUser) {
            throw new CustomException(ResultCode.LOGIN_OUT_OF_DATE.code, ResultCode.LOGIN_OUT_OF_DATE.msg);
        }
        UserAccountDTO user = this.findUserById(currentUser.getUserId());
        if (null == user) {
            throw new CustomException(ResultCode.USER_NO_FOUNT.code, ResultCode.USER_NO_FOUNT.msg);
        }
        UserInfoVO userInfoVo = new UserInfoVO();
        BeanUtil.copyProperties(user, userInfoVo);
        List<String> roles = new ArrayList<>();
        List<Long> roleIds = new ArrayList<>();
        // 获取用户角色
        List<SysRole> userRoles = userRoleService.findUserRoles(user.getId());
        if (CollectionUtil.isNotEmpty(userRoles)) {
            userRoles.forEach(role -> {
                roles.add(role.getValue());
                roleIds.add(role.getId());
            });
        }
        // 获取用户菜单
        MenuVO menuVo = new MenuVO();
        menuVo.setRoleIds(roleIds);
        Map<String, Object> menuVoList = menuService.findMenuTreeByRoleIds(menuVo);
        userInfoVo.setRoles(roles);
        userInfoVo.setMenus(menuVoList);
        userInfoVo.setAvatar(getUserAvatar(user.getAvatar()));
        return userInfoVo;
    }

    @Override
    public PageT<UserVO> findPageUser(PageT<UserVO> page, UserQueryDTO queryDTO) {
        queryDTO.setUserType(SystemConstant.USER_TYPE.SUPERVISE_ACCOUNT);
        AuthUser currentUser = SecurityUtil.getCurrentUser();
        // 用户未登录直接返回空集合
        if (Objects.isNull(currentUser)) {
            return page;
        }
        // 分局只能查询辖区内的
        if (Objects.equals(SystemConstant.SUPERVISE_USER_TYPE.BRANCH_ACCOUNT, currentUser.getUserDetailType())) {
            queryDTO.setAreaCode(currentUser.getAreaCode());
        }
        return this.baseMapper.findPageUser(page, queryDTO);
    }


    @Override
    public void updateUserInfo(UserBaseInfoVO userInfoVo) {
        if (null != userInfoVo.getId()) {
            SysUser user = new SysUser();
            user.setId(userInfoVo.getId());
            user.setUsername(userInfoVo.getUsername());
            user.setAvatar(userInfoVo.getAvatar());
            user.setNickName(userInfoVo.getNickName());
            user.setEmail(userInfoVo.getEmail());
            user.setPhone(userInfoVo.getPhone());
            user.setComment(userInfoVo.getComment());
            user.setAreaCode(userInfoVo.getAreaCode());
            user.setTownCode(userInfoVo.getTownCode());
            user.setVillageCode(userInfoVo.getVillageCode());
            user.setPositionId(userInfoVo.getPositionId());
            user.setPartUserId(userInfoVo.getPartUserId());
            user.setArea(userInfoVo.getArea());
            user.setOrgId(userInfoVo.getOrgId());
            user.setUpdateTime(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
//            BeanUtil.copyProperties(userInfoVo,user);
            this.baseMapper.updateUserInfo(user);
        } else {
            throw new CustomException(BusinessStatusEnum.EDIT_WITH_NULL_ID.code,
                    BusinessStatusEnum.EDIT_WITH_NULL_ID.msg);
        }
    }

    @Override
    public void updateAvatar(UserAvatarUpdateDTO sysUser) {
        SysUser newSysUser = new SysUser();
        newSysUser.setId(sysUser.getId());
        newSysUser.setAvatar(sysUser.getAvatar());
        this.baseMapper.updateById(newSysUser);
    }

    @Override
    public UserBaseInfoVO findUserBaseInfo(String id) {
        return this.baseMapper.selectUserBaseInfo(id);
    }

    @Override
    public String updatePassword(PasswordVO vo) {
        if (StringUtils.isNotBlank(vo.getPassword()) && StringUtils.isNotBlank(vo.getNewPassword())) {
            if (!PasswordValidUtil.evalPassword(vo.getNewPassword())) {
                throw new CustomException(ResultCode.USER_NOT_PASSWORD.code, ResultCode.USER_NOT_PASSWORD.msg);
            }
            // 进行用户密码验证
            checkUserPassword(vo);
            // 修改用户密码
            SysUser newSysUser = new SysUser();
            newSysUser.setId(vo.getUserId());
            newSysUser.setMemo(vo.getNewPassword());
            newSysUser.setPassword(new BCryptPasswordEncoder().encode(vo.getNewPassword()));
            newSysUser.setLastUpdatePwdTime(LocalDateTime.now());
            this.baseMapper.updateById(newSysUser);
        }
        return null;
    }

    @Override
    public List<SysRole> getUserRoles(String id) {
        return userRoleService.findUserRoles(id);
    }

    /**
     * 修改用户登录时间
     *
     * @Author GZL
     * @Date 2023/2/2 9:23
     * @Param userName 用户名
     **/
    @Override
    public void updateLoginTime(String userName) {
        this.baseMapper.updateLoginTime(userName, LocalDateTime.now());
    }

    /**
     * 用户停用/启用
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/2/13 9:54
     * @Param enabledDTO enabledDTO
     **/
    @Override
    public boolean updateEnabled(UserEnabledDTO enabledDTO) {
        SysUser sysUser = new SysUser();
        sysUser.setId(enabledDTO.getId());
        sysUser.setEnabled(enabledDTO.getEnabled());
        sysUser.setLastActiveTime(LocalDateTime.now());
        return this.updateById(sysUser);
    }

    /**
     * 分页查询企业用户信息
     *
     * @return 企业用户信息
     * @Author GZL
     * @Date 2023/2/13 11:09
     * @Param page 分页信息
     * @Param queryDTO 条件
     **/
    @Override
    public PageT<UserVO> getPageCompanyUser(PageT<UserVO> page, UserQueryDTO queryDTO) {
        AuthUser currentUser = SecurityUtil.getCurrentUser();
        // 用户未登录或者为企业账号是返回空数据
        if (Objects.isNull(currentUser)) {
            return page;
        }
//        // 分局账号只能查看所辖区域企业账号
//        if (Objects.equals(SystemConstant.USER_TYPE.SUPERVISE_ACCOUNT, currentUser.getUserType()) &&
//                Objects.equals(SystemConstant.SUPERVISE_USER_TYPE.BRANCH_ACCOUNT, currentUser.getUserDetailType())) {
//            queryDTO.setAreaCode(currentUser.getAreaCode());
//        }
        return this.baseMapper.findPageUser(page, queryDTO);
    }

    /**
     * 同步数据处理 TODO
     *
     * @return 结果
     * @Author GZL
     * @Date 2023/2/13 15:33
     **/
    @Override
    public boolean syncDataHandle() {
        List<SysUser> list = this.findList(new UserQueryDTO());
        list.forEach(user -> user.setPassword(new BCryptPasswordEncoder().encode(user.getMemo())));
        return this.updateBatchById(list, list.size());
    }

    /**
     * 分页查询驾驶员用户信息
     *
     * @return 驾驶员用户信息
     * @Author GZL
     * @Date 2023/2/14 15:25
     * @Param page 分页信息
     * @Param queryDTO 条件
     **/
    @Override
    public PageT<UserVO> getPageDriverUser(PageT<UserVO> page, UserQueryDTO queryDTO) {
        AuthUser currentUser = SecurityUtil.getCurrentUser();
        // 用户未登录
        if (Objects.isNull(currentUser)) {
            throw new CustomException(ResultCode.TOKEN_AUTH_FAILED.code, ResultCode.TOKEN_AUTH_FAILED.msg);
        }
        queryDTO.setUserType(SystemConstant.USER_TYPE.ENTERPRISE_ACCOUNT);
        queryDTO.setUserDetailType(SystemConstant.ENTERPRISE_USER_TYPE.DRIVER_ACCOUNT);
        return this.baseMapper.findPageUser(page, queryDTO);
    }

    /**
     * 分页查询医疗科室收集用户信息
     *
     * @return 医疗科室收集用户信息
     * @Author GZL
     * @Date 2023/2/14 15:34
     * @Param page 分页信息
     * @Param queryDTO 条件
     **/
    @Override
    public PageT<UserVO> getPageDepartmentUser(PageT<UserVO> page, UserQueryDTO queryDTO) {
        AuthUser currentUser = SecurityUtil.getCurrentUser();
        // 用户未登录
        if (Objects.isNull(currentUser)) {
            throw new CustomException(ResultCode.TOKEN_AUTH_FAILED.code, ResultCode.TOKEN_AUTH_FAILED.msg);
        }
        queryDTO.setUserType(SystemConstant.USER_TYPE.ENTERPRISE_ACCOUNT);
        queryDTO.setUserDetailType(SystemConstant.ENTERPRISE_USER_TYPE.MEDICAL_DEPARTMENT_COLLECTED_ACCOUNT);
        return this.baseMapper.findPageUser(page, queryDTO);
    }

    /**
     * 弱口令检测
     *
     * @return 是否是弱口令
     * @Author GZL
     * @Date 2023/3/30 17:49
     **/
    @Override
    public Boolean pwdCheck() {
        AuthUser currentUser = SecurityUtil.getCurrentUser();
        if (Objects.isNull(currentUser)) {
            throw new CustomException(ResultCode.TOKEN_AUTH_FAILED.code, ResultCode.TOKEN_AUTH_FAILED.msg);
        }
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SysUser::getMemo);
        queryWrapper.eq(SysUser::getUsername, currentUser.getUsername());
        SysUser user = this.baseMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new CustomException(ResultCode.USER_NO_FOUNT.code, ResultCode.USER_NO_FOUNT.msg);
        }
        return StringUtils.isNotBlank(user.getMemo()) && user.getMemo().length() < 56 && !PasswordValidUtil.evalPassword(user.getMemo());
    }

    @Override
    public UserInfoPartyVO getDjInfo(String code) {
        AuthUser currentUser = SecurityUtil.getCurrentUser();
        if (null == currentUser) {
            throw new CustomException(ResultCode.LOGIN_OUT_OF_DATE.code, ResultCode.LOGIN_OUT_OF_DATE.msg);
        }
        UserAccountDTO user = this.findUserByUsername(currentUser.getUsername());
        if (null == user) {
            throw new CustomException(ResultCode.USER_NO_FOUNT.code, ResultCode.USER_NO_FOUNT.msg);
        }
        UserInfoPartyVO userInfoVo = new UserInfoPartyVO();
        BeanUtil.copyProperties(user, userInfoVo);
        List<String> roles = new ArrayList<>();
        List<Long> roleIds = new ArrayList<>();
        // 获取用户角色
        List<SysRole> userRoles = userRoleService.findUserRoles(user.getId());
        if (CollectionUtil.isNotEmpty(userRoles)) {
            for(SysRole role:userRoles){
                roles.add(role.getValue());
                roleIds.add(role.getId());

                //判断当前登陆用户是否是系统管理员
                if("MANAGER".equals(role.getValue())||"ADMIN".equals(role.getValue())){
                    userInfoVo.setIsManager(1);
                }
            }
        }
        // 获取用户菜单
        MenuVO menuVo = new MenuVO();
        menuVo.setRoleIds(roleIds);
        if (code != null && !"".equals(code)) {
            menuVo.setCode(code);
        } else {
            menuVo.setLevel(0);
        }
        Map<String, Object> menuVoList = menuService.findMenuTreeByRoleIds(menuVo);
        userInfoVo.setRoles(roles);
        List<MenuVO> pcMenu = (List<MenuVO>) menuVoList.get("pc");
        List<MenuVO> djMenu = pcMenu.get(0).getChildren().stream().filter(t->t.getName().equals("智慧党建")).collect(Collectors.toList());
        userInfoVo.setMenus(djMenu.get(0).getChildren());
        userInfoVo.setAvatar(getUserAvatar(user.getAvatar()));
        return userInfoVo;
    }

    @Override
    public SysUser findUserByOpenId(String openId) {

        return this.baseMapper.findUserByOpenId(openId);
    }

	@Override
	public UserAccountDTO getByOpenId(String unionId) {
        UserAccountDTO userAccountDTO = this.baseMapper.getByOpenId(unionId);
        List<SysRole> userRoles = userRoleService.findUserRoles(userAccountDTO.getId());
        if (CollectionUtil.isNotEmpty(userRoles)) {
            List<RoleDTO> authorities = userRoles.stream().map(r -> BeanUtil.copyProperties(r, RoleDTO.class)).collect(Collectors.toList());
            userAccountDTO.setRoles(authorities);
        }
        List<SysResource> userResources = resourceService.findUserResources(userAccountDTO.getId());
        if (CollectionUtil.isNotEmpty(userResources)) {
            userAccountDTO.setResources(userResources.stream()
                    .filter(v -> Objects.equals(SystemConstant.RESOURCE_AUTH_TYPE.BY_CODE, v.getAuthType()))
                    .map(v -> BeanUtil.copyProperties(v, ResourceDTO.class)).collect(Collectors.toList()));
        }
        return userAccountDTO;
	}

    @Override
    public List<SysUser> getUserListByRole(String roleValue) {
        //根据角色获取用户
        return this.baseMapper.getListByRoleValue(roleValue);
    }

    @Override
    public List<SysUser> getTownUserList() {
        return getUserListByRole(SystemConstant.ROlE_VALUE.TOWN_ROLE);
    }

    @Override
    public List<SysUser> getDistrictUserList() {
        return getUserListByRole(SystemConstant.ROlE_VALUE.DISTRICT_ROLE);
    }

    @Override
    public List<SysUser> getVillageUserList() {
        return getUserListByRole(SystemConstant.ROlE_VALUE.VILLAGE_ROLE);
    }

    @Override
    public SysUser getUserByOrgId(UserBaseInfoVO userInfoVo) {
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getOrgId,userInfoVo.getOrgId())
                .eq(SysUser::getPositionId,userInfoVo.getPositionId());
        List<SysUser> sysUsers = this.baseMapper.selectList(queryWrapper);

        return sysUsers.size()>0?sysUsers.get(0):null;
    }

    private String getUserAvatar(String avatar) {
        try {
            if (StringUtils.isBlank(avatar)) {
                SysSettings setting = sysSettingsService.getValueByCode(SystemBaseConstants.SYSTEM_DEFAULT_AVATAR_KEY);
                return setting.getValue();
            }
            return getFilePath(avatar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取图片路径
     *
     * @param attId 附件id
     * @return 图片路径
     */
    @Cacheable(value = {SystemBaseConstants.BASE_PATH + ":attachments"}, key = "#attId")
    public String getFilePath(String attId) {
        try {
            RestResult<String> res = remoteFileService.getFilePath(attId, AuthConstants.FROM_IN);
            if (res.isSuccess()) {
                return String.valueOf(res.getData());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
