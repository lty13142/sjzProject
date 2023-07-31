package com.crcm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crcm.admin.api.dto.res.UserAccountDTO;
import com.crcm.admin.model.dto.user.UserQueryDTO;
import com.crcm.admin.model.entity.SysUser;
import com.crcm.admin.model.vo.UserBaseInfoVO;
import com.crcm.admin.model.vo.UserVO;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.core.dto.TreeView;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface UserMapper extends BaseMapper<SysUser> {

    PageT<UserVO> findPageUser(@Param("page") PageT<UserVO> page, @Param("user") UserQueryDTO user);

    List<TreeView> findTree(SysUser sysUser);

    List<Map<String, Object>> findAll();

    UserBaseInfoVO selectUserBaseInfo(String id);

    /**
     * 修改用户登录时间
     * @Author GZL
     * @Date 2023/2/2 9:23
     * @Param userName 用户名
     * @Param lastLoginTime 最后登录时间
     **/
    int updateLoginTime(@Param("userName") String userName, @Param("lastLoginTime") LocalDateTime lastLoginTime);

	SysUser findUserByOpenId(String openId);

	UserAccountDTO getByOpenId(String openId);

	int updateDjUserDetail(@Param("userId") String userId,@Param("partUserId") String partUserId);

    List<SysUser> getListByRoleValue(@Param("roleValue")String roleValue);

    int updateUserInfo(SysUser sysUser);
}
