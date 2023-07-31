package com.sjz.partbuilding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjz.partbuilding.model.dto.NumDTO;
import com.sjz.partbuilding.model.entity.UserDetail;
import com.sjz.partbuilding.model.vo.DicContentVo;
import com.sjz.partbuilding.model.vo.TreeViewVo;
import com.sjz.partbuilding.model.vo.UserDetailVo;
import com.sjz.partbuilding.model.vo.UserTotalVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * @author zzyt
 * @version 1.0
 * @date 2020/7/20 9:26
 */
public interface UserDetailService extends IService<UserDetail> {

    /**
     * 新增
     *
     * @param userDetail
     * @return
     */
    int saveUserDetail(UserDetail userDetail);

    /**
     * 修改
     *
     * @param userDetailVo
     * @return
     */
    int updateUserDetail(UserDetailVo userDetailVo);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 真实删除
     *
     * @param id
     * @return
     */
    int realDelete(String id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    UserDetail findById(String id);

    /**
     * 查询列表
     *
     * @param userDetail
     * @return
     */
    List<UserDetail> findList(UserDetail userDetail);

    /**
     * 分页查询
     *
     * @param page
     * @param userDetail
     * @return
     */
    IPage<UserDetail> findPage(Page page, UserDetail userDetail);

    /**
     * 根据userId获取UserDetail的扩展类vo
     *
     * @param userId
     * @return
     */
    UserDetailVo getDetail(String userId);

    /**
     * 个人中心查询用户详细信息
     *
     * @param userId
     * @return
     */
    UserDetailVo getPersonInfoDetail(String userId);

    /**
     * 获取人员管理分页查询
     *
     * @param userDetail 用户详情
     * @param page       分页参数
     * @return
     */
    Page getPersonManagementPage(UserDetail userDetail, Page page);

    /**
     * 根据组织id查询组织下人员
     *
     * @param OrgId
     * @return
     */
    List<Map<String, Object>> findListByOrgId(String OrgId);

    /**
     * 获取数据统计.年龄占比
     *
     * @param OrgId
     * @return
     */
    Map<String, Object> findAgeByOrgId(String OrgId);

    /**
     * 获取首页党员类别统计图
     *
     * @return
     */
    List<Map<String, Object>> getLoginPartyType(DicContentVo dicContent, String orgId);

    /**
     * 获取首页党员学历占比统计图
     *
     * @return
     */
    List<Map<String, Object>> getLoginEducation(DicContentVo dicContent);

    /**
     * 数据统计根据orgId获取党员学历占比
     *
     * @param dicContent 数据字典
     * @param orgId      组织id
     * @return
     */
    Map<String, List<String>> getEducationByOrgId(DicContentVo dicContent, String orgId);

    /**
     * 获取首页党员男女统计图
     *
     * @return
     */
    List<Map<String, Object>> getLoginSex(DicContentVo dicContent);

    /**
     * 获取学历占比
     *
     * @param orgId
     * @return
     */
    List<NumDTO> findPersonNum(String orgId);

    /**
     * 获取首页年龄分布统计图
     *
     * @param dicContent
     * @return
     */
    Map<String, Object> getLoginAge(DicContentVo dicContent);

    /**
     * 获取数据统计党组织类型占比
     *
     * @param dicContent
     * @return
     */
    Map<String, Object> getLoginPartyOrg(DicContentVo dicContent);

    /**
     * 获取首页党龄分布统计图
     *
     * @param dicContent
     * @return
     */
    Map<String, Object> getLoginPartyAge(DicContentVo dicContent);

    /**
     * 根据orgId获取组织下人员
     *
     * @param userDetail
     * @return
     */
    List<UserDetail> getPersonByOrgId(UserDetail userDetail);

    /**
     * 获取入党积极分子信息
     *
     * @param userId 用户id
     * @return
     */
    UserDetailVo getPartyActivistsDetail(String userId);
    /**
     * 获取数据统计人员信息综合表
     *
     * @param userDetail 用户详情
     * @param page       分页参数
     * @return
     */
    Page getStatisticsPersonManagementPage(UserDetail userDetail, Page page);

    /**
     * 获取数据统计人员信息综合表
     *
     * @param userDetail 用户详情
     * @return
     */
    List<UserDetail> getStatisticsPersonManagement(UserDetail userDetail);

    /**
     * 获取列表数据统计人员信息综合表
     *
     * @param userDetail 用户详情
     * @return
     */
    List<UserDetail> getStatisticsPersonManagementList(UserDetail userDetail);

    /**
     * 导出党员数据
     * @param userDetail
     */
    void exportPartyMember(UserDetail userDetail, HttpServletResponse response);

    /**
     * 获取记录图片
     * @param id
     * @return
     */
    String getRecordPic(String id);

    /**
     * 查询男女党员
     * @param orgId
     * @return
     */
    Map<String, Object> getPartyAge(String orgId);

    /**
     * 查询党员，入党积极分子，预备党员
     * @return
     */
    List<Map<String, Object>> getLogOrg(String orgId);

    /**
     * 获取人员信息
     *
     * @param userDetail 用户详情
     * @return
     */
    List<UserDetail> getPersonManagementDowload(UserDetail userDetail);

    /**
     * 人员实力统计
     * @param userDetail
     * @return
     */
    UserTotalVo countPersonnelStrength(UserDetail userDetail, Page page);
    /**
     * 查询党委一张图党员，入党积极分子，预备党员
     * @param orgId
     * @return
     */
    List<Map<String, Object>> getLogPartyCount(String orgId);

    UserDetail findByUserId(String userId);

    List<TreeViewVo> findUsersByOrgId2(String orgId);
}
