package com.sjz.partbuilding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.core.dto.TreeView;
import com.sjz.partbuilding.model.dto.NumDTO;
import com.sjz.partbuilding.model.entity.UserDetail;
import com.sjz.partbuilding.model.vo.TreeViewVo;
import com.sjz.partbuilding.model.vo.UserDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 用户详情Mapper
 *
 * @author zzyt
 * @version 1.0
 * @date 2020/7/21 17:39
 */
public interface UserDetailMapper extends BaseMapper<UserDetail> {

    /**
     * 真实删除
     *
     * @param id ID
     * @return
     */
    int realDelete(@Param("id") String id);


    /**
     * 根据orgId和OrgIds集合查询userDetailVo集合
     *
     * @param orgIds
     * @param userId
     * @return
     */
    List<UserDetailVo> selectPersonByOrgIds(@Param("orgIds") List<String> orgIds, @Param("userId") String userId);

    /**
     * 获取人员管理分页查询
     *
     * @param userDetail 用户详情
     * @param page       分页参数
     * @return
     */
    List<UserDetail> getPersonManagementPage(@Param("userDetail") UserDetail userDetail, Page page);

    /**
     * 获取人员管理分页查询
     *
     * @param userDetail 用户详情
     * @param page       分页参数
     * @return
     */
    List<UserDetail> getStatisticsPersonManagementPage(@Param("userDetail") UserDetail userDetail, Page page);

    /**
     * 获取人员管理列表查询
     *
     * @param userDetail 用户详情
     * @return
     */
    List<UserDetail> getStatisticsPersonManagementList(@Param("userDetail") UserDetail userDetail);

    /**
     * 查询用户详细信息
     *
     * @param userId
     * @return
     */
    UserDetail getDetail(String userId);

    /**
     * 获取人员党员类别
     *
     * @return
     */
    List<Map<String, Object>> getLoginPartyType(@Param("orgId") String orgId);

    /**
     * 获取首页党员学历占比统计图
     *
     * @return
     */
    List<Map<String, Object>> getLoginEducation();

    /**
     * 数据统计根据orgId获取党员学历占比
     *
     * @param orgId 党组织id
     * @return
     */
    List<Map<String, Object>> getEducationByOrgId(@Param("orgId") String orgId);

    /**
     * 获取首页党员男女统计图
     *
     * @return
     */
    List<Map<String, Object>> getLoginSex(@Param("ids") ArrayList<String> ids);

    List<UserDetail> findListByOrgId(@Param("orgId") String orgId);

    List<NumDTO> findPersonNum(@Param("orgId") String orgId);

    List<UserDetail> findAgeByOrgId(@Param("orgId") String orgId);

    /**
     * 党员年龄信息
     *
     * @param sexStr 男或女
     * @return
     */
    List<UserDetail> getLoginAge(@Param("sexStr") String sexStr);

    /**
     * 党员年龄信息 党员党龄信息
     *
     * @param sexStr 男或女
     * @return
     */
    List<UserDetail> getLoginPartyAge(@Param("ids")ArrayList<String> ids, @Param("sexStr") String sexStr);

    /**
     * 根据orgId获取组织下人员list
     *
     * @param userDetail 用户详情
     * @return
     */
    List<UserDetail> getPersonByOrgId(@Param("userDetail") UserDetail userDetail);

    /**
     * 获取入党积极分子信息
     *
     * @param userId 用户id
     * @return
     */
    UserDetail getPartyActivistsDetail(String userId);

    /**
     * 获取人员信息（党员，预备党员，入党积极份子）
     *
     * @param userDetail 用户详情
     * @return
     */
    List<UserDetail> getPersonManagementPage(@Param("userDetail") UserDetail userDetail);

    /**
     * 查询党员，入党积极分子，预备党员
     * @return
     */
    List<Map<String, Object>> getLogOrg(String orgId);

    /**
     * 人员列表导出
     *
     * @param userDetail 用户详情
     * @return
     */
    List<UserDetail> getPersonManagementDowload(@Param("userDetail") UserDetail userDetail);

    /**
     * 查询党委一张图党员，入党积极分子，预备党员
     * @param ids
     * @return
     */
    List<Map<String, Object>> getLogPartyCount(@Param("ids")ArrayList<String> ids);

    List<TreeView> findUsersByOrgId(@Param("orgId") String orgId);

    UserDetail findByUserId(@Param("userId") String userId);

    List<TreeViewVo> findUsersByOrgId2(String orgId);
}
