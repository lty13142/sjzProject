package com.sjz.partbuilding.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.csp.sentinel.transport.command.http.StatusCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjz.partbuilding.constants.YTDicConstant;
import com.sjz.partbuilding.enums.EducationEnum;
import com.sjz.partbuilding.mapper.OrgMapper;
import com.sjz.partbuilding.mapper.UserDetailMapper;
import com.sjz.partbuilding.model.dto.NumDTO;
import com.sjz.partbuilding.model.entity.Attachments;
import com.sjz.partbuilding.model.entity.Org;
import com.sjz.partbuilding.model.entity.Post;
import com.sjz.partbuilding.model.entity.UserDetail;
import com.sjz.partbuilding.model.vo.DicContentVo;
import com.sjz.partbuilding.model.vo.TreeViewVo;
import com.sjz.partbuilding.model.vo.UserDetailVo;
import com.sjz.partbuilding.model.vo.UserTotalVo;
import com.sjz.partbuilding.service.AttachmentsService;

import com.sjz.partbuilding.service.OrgService;
import com.sjz.partbuilding.service.PostService;
import com.sjz.partbuilding.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author zzyt
 * @version 1.0
 * @date 2020/7/20 11:35
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements UserDetailService {

//    @Autowired
//    private UserService userService;

//    @Autowired
//    private UserServiceImpl userServiceImpl;

    @Autowired
    private OrgMapper orgMapper;
//
//    @Resource
//    private DicController dicController;
//
    @Resource
    private PostService postService;

    @Resource
    private AttachmentsService attachmentsService;

//    @Resource
//    private LeaderService leaderService;
//
//    @Resource
//    private LeaderMapper leaderMapper;
//
//    @Resource
//    private UserMapper userMapper;

    @Resource
    private UserDetailMapper userDetailMapper;

    @Override
    public int saveUserDetail(UserDetail userDetail) {
        return this.baseMapper.insert(userDetail);
    }

    @Override
    public int updateUserDetail(UserDetailVo userDetailVo) {
        //保存头像
//        if (userDetailVo.getAvatar() != null && !"".equals(userDetailVo.getAvatar())) {
//            User user = new User();
//            user.setId(userDetailVo.getUserId());
//            user.setAvatar(userDetailVo.getAvatar());
//            userService.updateAvatar(user);
//        }
//        //关联user表的用户名
//        User user1 = userMapper.selectById(userDetailVo.getUserId());
//        if (user1 != null && !user1.getUsername().equals(userDetailVo.getName())) {
//            userServiceImpl.isNullHandler(userDetailVo.getName());
//            user1.setUsername(userDetailVo.getName());
//            userMapper.updateById(user1);
//        }
//        //关联修改班子成员党内职务信息
//        Leader leader = null;
//        QueryWrapper<Leader> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("org_id", userDetailVo.getOrgId());
//        queryWrapper.eq("user_id", userDetailVo.getUserId());
//        List<Leader> leaders = leaderMapper.selectList(queryWrapper);
//        if (leaders != null && leaders.size() > 0) {
//            leader = leaders.get(0);
//        }
//        if (leader != null) {
//            leader.setPostId(userDetailVo.getPartyPosts());
//            leaderMapper.updateById(leader);
//        }

        return this.baseMapper.updateById(userDetailVo);
    }

    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public UserDetail findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<UserDetail> findList(UserDetail userDetail) {
        QueryWrapper<UserDetail> queryWrapper = new QueryWrapper<>();
        if (userDetail.getName() != null && !"".equals(userDetail.getName())) {
            queryWrapper.like("name", userDetail.getName());
        }
        queryWrapper.isNotNull("name");
        queryWrapper.orderByDesc("create_time");
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<UserDetail> findPage(Page page, UserDetail userDetail) {
        QueryWrapper<UserDetail> queryWrapper = new QueryWrapper<>();
        IPage<UserDetail> pageUserDetail = this.baseMapper.selectPage(page, queryWrapper);
        return pageUserDetail;
    }

    @Override
    public UserDetailVo getDetail(String userId) {
        UserDetail userDetail = this.baseMapper.getDetail(userId);
        if (userDetail == null || "".equals(userDetail)) {
            return null;
        }
        String facePic = userDetail.getFacePic();
        if (StringUtils.isNotEmpty(facePic) && facePic.contains("?")) {
            facePic = facePic.substring(0, facePic.indexOf("?"));
            userDetail.setFacePic(facePic);
        }
//        // 转换学历
//        /*String education = UtilDic.getChNameByCode("EDUCATION", userDetail.getEducation());userDetail.setEducation(education);*/
        Org org = orgMapper.selectById(userDetail.getOrgId());
        UserDetailVo userDetailVo = new UserDetailVo();
        BeanUtils.copyProperties(userDetail, userDetailVo);
        if (org != null) {
            userDetailVo.setOrgName(org.getName());
        }
        // 转换党内职位
        if (StringUtils.isNotBlank(userDetailVo.getPartyPosts())) {
            Post post = postService.getById(userDetailVo.getPartyPosts());
            if (Objects.nonNull(post)) {
                userDetailVo.setPostName(post.getPostName());
            }
        }
        if (StringUtils.isNotBlank(userDetailVo.getFacePic())) {
//            String filePath = attachmentsService.findFilePath(userDetailVo.getFacePic());
            if (!userDetailVo.getFacePic().startsWith("http")) {
                Attachments attachments = attachmentsService.getById(userDetailVo.getFacePic());
                userDetailVo.setFacePicPath(attachments.getMinioPath());
            }
        }
        return userDetailVo;
    }

    /**
     * 个人中心查询用户详细信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserDetailVo getPersonInfoDetail(String userId) {
        UserDetail userDetail = this.baseMapper.getDetail(userId);
        //不为党员则异常
        if (userDetail == null || "".equals(userDetail)) {
            return null;
        }
        /*//党员状态码
        String partyTypeIndex = "0";
        if (!partyTypeIndex.equals(userDetail.getPartyType())) {
            throw new SystemException(YTSystemStatusEnum.IS_NOT_PARTY_PERSON.code, YTSystemStatusEnum.IS_NOT_PARTY_PERSON.desc);
        }*/
//        Org org = orgService.findById(userDetail.getOrgId());
//        Post post = postService.findById(userDetail.getPartyPosts());
//        UserDetailVo userDetailVo = new UserDetailVo();
//        UtilBean.copyTo(userDetail, userDetailVo);
//        userDetailVo.setOrgName(org.getName());
//        if (post != null) {
//            userDetailVo.setPostName(post.getPostName());
//        }
//        return userDetailVo;
        return null;
    }

    /**
     * 获取人员管理分页查询
     *
     * @param userDetail 用户详情
     * @param page       分页参数
     * @return
     */
    @Override
    public Page getPersonManagementPage(UserDetail userDetail, Page page) {
        List<UserDetail> userDetailList = this.baseMapper.getPersonManagementPage(userDetail, page);
        //获取现任党内职务
        getPostNameToUserDetailList(userDetailList);
        //组装进page
        page.setRecords(userDetailList);
        return page;
    }

    @Override
    public List<Map<String, Object>> findListByOrgId(String orgId) {
        //全部人员集合
        List<UserDetail> listByOrgId = baseMapper.findListByOrgId(orgId);
        //党员集合
        List<UserDetail> member = new ArrayList<>();
        //预备役
        List<UserDetail> reserve = new ArrayList<>();
        //积极分子
        List<UserDetail> activists = new ArrayList<>();
        for (UserDetail userDetail : listByOrgId) {
            if (userDetail.getPartyType().equals("0")) {
                member.add(userDetail);
            } else if (userDetail.getPartyType().equals("1")) {
                activists.add(userDetail);
            } else {
                reserve.add(userDetail);
            }
        }
        List<Map<String, Object>> map = new ArrayList<>();
        Map<String, Object> person = new HashMap<>();
        person.put("member", member.size());
        person.put("reserve", reserve.size());
        person.put("activists", activists.size());
        map.add(person);
        List<UserDetail> man = new ArrayList<>();
        for (UserDetail userDetail : member) {
            if (userDetail.getSex().equals("0")) {
                man.add(userDetail);
            }
        }
        Map<String, Object> sex = new HashMap<>();
        sex.put("man", man.size());
        sex.put("woman", member.size() - man.size());
        map.add(sex);
        return map;
    }


    /**
     * 获取首页党员类别统计图
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getLoginPartyType(DicContentVo dicContent, String orgId) {
        //获取字典数据
        List<DicContentVo> dicContentList = this.getDicContentList(dicContent);
        //获取人员党员类别
        List<Map<String, Object>> mapList = this.baseMapper.getLoginPartyType(orgId);
        //创建结果集
        List<Map<String, Object>> resultList = new ArrayList<>();
        //组装为0的数据
        for (DicContentVo array : dicContentList) {
            boolean flag = false;
            for (Map<String, Object> map : mapList) {
                if (array.getValue().equals(map.get("partyType"))) {
                    resultList.add(map);
                    flag = true;
                    break;
                }
            }
            //为0则加空
            if (flag == false) {
                Map<String, Object> fileMap = new HashMap<>(16);
                fileMap.put("partyType", array.getValue());
                fileMap.put("partyTypeCount", 0);
                resultList.add(fileMap);
            }
        }
        return resultList;
    }

    /**
     * 获取首页党员学历占比统计图
     *
     * @param dicContent 数据字典
     * @return
     */
    @Override
    public List<Map<String, Object>> getLoginEducation(DicContentVo dicContent) {
        //获取字典数据
        EducationEnum[] dicContentList = EducationEnum.values();
//        //获取字典数据
//        List<DicContentVo> dicContentList = this.getDicContentList(dicContent);
        //获取人员党员学历占比
        List<Map<String, Object>> mapList = this.baseMapper.getLoginEducation();
        //组装数据
        List<Map<String, Object>> resultList = toResultList(dicContentList, mapList);
        return resultList;
    }

    /**
     * 数据统计根据orgId获取党员学历占比
     *
     * @param dicContent 数据字典
     * @param orgId      组织id
     * @return
     */
    @Override
    public Map<String, List<String>> getEducationByOrgId(DicContentVo dicContent, String orgId) {
        //获取字典数据
        EducationEnum[] dicContentList = EducationEnum.values();
        //只取大的
        //获取人员党员学历占比
        List<Map<String, Object>> mapList = this.baseMapper.getEducationByOrgId(orgId);
//        List<Map<String, Object>> resultList = toResultList(dicContentList, mapList);
        Map<String, List<String>> map = new HashMap<>();
//        String[] xAisData = new String[resultList.size()];
//        String[] data = new String[resultList.size()];
        List<String> xAisData = new ArrayList<>();
        List<String> data = new ArrayList<>();
        for (EducationEnum educationEnum : dicContentList) {
            xAisData.add(educationEnum.getValue());
            boolean flag = false;
            for (Map<String, Object> objectMap : mapList) {
                if (educationEnum.getCode().equals(Integer.parseInt((String) objectMap.get("education")))) {
                    data.add(String.valueOf(objectMap.get("education_count")));
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                data.add(String.valueOf(0L));
            }
        }
//        for (int i = 0; i < resultList.size(); i++) {
//            Map map1 = (Map) resultList.get(i);
//            Iterator iterator = map1.keySet().iterator();
//            while (iterator.hasNext()) {
//                String next = (String) iterator.next();
//                if ("educationName".equals(next)) {
//                    String o = (String) map1.get(next);
//                    xAisData[i] = o;
//                }
//
//                if ("educationCount".equals(next)) {
//                    Long o = (Long) map1.get(next);
//                    String s = o.toString();
//                    data[i] = s;
//                }
//            }
//        }
        map.put("xA", xAisData);
        map.put("data", data);
        return map;
    }

    /**
     * 公共处理党员学历占比组装数据
     *
     * @param dicContentList
     * @param mapList
     * @return
     */
    public List<Map<String, Object>> toResultList(EducationEnum[] dicContentList, List<Map<String, Object>> mapList) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (EducationEnum array : dicContentList) {
            boolean flag = false;
            Integer value = EducationEnum.getCode(array);
            for (Map<String, Object> map : mapList) {
                if (Integer.toString(value).equals(map.get("education"))) {
                    map.put("educationName", EducationEnum.getValueByCode(value));
//                    map.put("color", array.getComments());
                    resultList.add(map);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                Map<String, Object> fileMap = new HashMap<>(16);
                fileMap.put("education", value);
//                fileMap.put("color", array.getComments());
                fileMap.put("educationCount", 0L);
                fileMap.put("educationName", EducationEnum.getValueByCode(value));
                resultList.add(fileMap);
            }
        }
        return resultList;
    }

    /**
     * 获取首页党员男女统计图
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getLoginSex(DicContentVo dicContent) {
        //获取字典数据
//        List<DicContentVo> dicContentList = this.getDicContentList(dicContent);
        //获取人员性别
        ArrayList<String> ids = new ArrayList<>();
//        ids.add("47d2567c9b0342712a17a026341cdf4f");
//        ids.add("cb2f3cae31c0417d4427b45a27e7d6b0");
//        ids.add("6f3ea4a19ab72068c9699980b3f03944");
//        ids.add("550b8741acb7408c8869942e6074c418");
        List<Map<String, Object>> mapList = this.baseMapper.getLoginSex(ids);
//        List<Map<String, Object>> resultList = new ArrayList<>();
//        for (DicContentVo array : dicContentList) {
//            boolean flag = false;
//            for (Map<String, Object> map : mapList) {
//                if (array.getValue().equals(map.get("sex"))) {
//                    map.put("sexName", array.getName());
//                    resultList.add(map);
//                    flag = true;
//                    break;
//                }
//            }
//            if (flag == false) {
//                Map<String, Object> fileMap = new HashMap<>(16);
//                fileMap.put("sex", array.getValue());
//                fileMap.put("sexName", array.getName());
//                fileMap.put("sexCount", 0);
//                resultList.add(fileMap);
//            }
//        }
        return mapList;
    }

    /**
     * 根据条件获取字典数据
     *
     * @param dicContent 查询条件
     * @return
     */
    public List<DicContentVo> getDicContentList(DicContentVo dicContent) {
//        RestResult restResult = dicController.getByCode(dicContent);
//        List<DicContentVo> dicContentList = (List<DicContentVo>) restResult.getData();
//        return dicContentList;
        return null;
    }

    /**
     * 获取数据统计党员男女统计图
     *
     * @return
     */
    @Override
    public List<NumDTO> findPersonNum(String orgId) {
        return baseMapper.findPersonNum(orgId);
    }

    /**
     * 获取年龄占比
     *
     * @param OrgId
     * @return
     */
    @Override
    public Map<String, Object> findAgeByOrgId(String OrgId) {
        Map<String, Object> parms = new HashMap<>();
        List<UserDetail> listByOrgId = baseMapper.findAgeByOrgId(OrgId);
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        for (UserDetail userDetail : listByOrgId) {
            if (userDetail.getAge() < 25) {
                a++;
            } else if (userDetail.getAge() >= 25 && userDetail.getAge() < 30) {
                b++;
            } else if (userDetail.getAge() >= 30 && userDetail.getAge() < 40) {
                c++;
            } else if (userDetail.getAge() >= 40 && userDetail.getAge() < 50) {
                d++;
            } else if (userDetail.getAge() >= 50 && userDetail.getAge() < 60) {
                e++;
            } else {
                f++;
            }
        }
        parms.put("0-25", a);
        parms.put("25-30", b);
        parms.put("30-40", c);
        parms.put("40-50", d);
        parms.put("50-60", e);
        parms.put("60-99", f);
        return parms;
    }

    /**
     * 年龄分布
     *
     * @return
     */
    @Override
    public Map<String, Object> getLoginAge(DicContentVo dicContent) {
        Map<String, Object> map = new HashMap<>();
        //年龄阶段
//        List<DicContentVo> dicContentVos = UtilSystemConstant.getClassData(dicContent.getDicCode());
//        //男党员年龄信息
//        List<UserDetail> maleList = this.baseMapper.getLoginAge(StatusCode.MALE.code);
//        //女党员年龄信息
//        List<UserDetail> femaleList = this.baseMapper.getLoginAge(StatusCode.FEMALE.code);
//        //组装首页年龄分布雷达图
//        map.put("male", userDetailListToIntArray(dicContentVos, maleList));
//        map.put("female", userDetailListToIntArray(dicContentVos, femaleList));
        return map;
    }

    /**
     * 公共处理年龄分布阶段
     *
     * @param dicContentVos  年龄阶段
     * @param userDetailList 党员年龄信息
     * @return
     */
    public int[] userDetailListToIntArray(List<DicContentVo> dicContentVos, List<UserDetail> userDetailList) {
        //初始化返回值
        int[] ageArray = new int[dicContentVos.size() - 1];
        //循环年龄阶段
        for (int i = 0; i < dicContentVos.size(); i++) {
            //如果此时为最后一个则退出
            if (i == dicContentVos.size() - 1) {
                break;
            }
            for (int j = userDetailList.size() - 1; j >= 0; j--) {
                if (Integer.valueOf(dicContentVos.get(i).getValue()) <= userDetailList.get(j).getAge() && userDetailList.get(j).getAge() < Integer.valueOf(dicContentVos.get(i + 1).getValue())) {
                    ageArray[i] += 1;
                    userDetailList.remove(j);
                }
            }
        }
        return ageArray;
    }

    void dealUserDetailList(Map<Integer,Integer> map,List<UserDetail> userDetailList) {

        for (UserDetail userDetail : userDetailList) {
            if (0<=userDetail.getAge()&&userDetail.getAge()<=5){
                if (map.containsKey(5)){
                    map.put(5,map.get(5)+1);
                }else {
                    map.put(5,1);
                }
            }
            else if (5<userDetail.getAge()&&userDetail.getAge()<=10){
                if (map.containsKey(10)){
                    map.put(10,map.get(10)+1);
                }else {
                    map.put(10,1);
                }
            }
            else if (10<userDetail.getAge()&&userDetail.getAge()<=15){
                if (map.containsKey(15)){
                    map.put(15,map.get(15)+1);
                }else {
                    map.put(15,1);
                }
            }
            else if (15<userDetail.getAge()&&userDetail.getAge()<=20){
                if (map.containsKey(20)){
                    map.put(20,map.get(20)+1);
                }else {
                    map.put(20,1);
                }
            }else if(20<userDetail.getAge()&&userDetail.getAge()<=25){
                if (map.containsKey(25)){
                    map.put(25,map.get(25)+1);
                }else {
                    map.put(25,1);
                }
            }
            else if(25<userDetail.getAge()){
                if (map.containsKey(30)){
                    map.put(30,map.get(30)+1);
                }else {
                    map.put(30,1);
                }
            }
        }
        for (int i = 5; i <=30; i+=5) {
            if (!map.containsKey(i)) {
                map.put(i,0);
            }
        }
    }

    /**
     * 获取数据统计党组织类型占比
     *
     * @param dicContent
     * @return
     */
    @Override
    public Map<String, Object> getLoginPartyOrg(DicContentVo dicContent) {
        //获取首页党组织类型占比
//        List<Org> orgList = orgService.getLoginPartyOrg();
//        if (orgList.size() < 1) {
//            return null;
//        }
//        //初始化直属地属支部数量
//        int directly = 0;
//        int groundGenus = 0;
//        for (Org org : orgList) {
//            if (org.getNumber().startsWith(StatusCode.DIRECTLY_BRANCH_NUMBER.code)) {
//                directly++;
//            } else if (org.getNumber().startsWith(StatusCode.GROUND_GENUS_BRANCH_NUMBER.code)) {
//                groundGenus++;
//            }
//        }
        Map<String, Object> map = new HashMap<>();
//        map.put("directly", directly);
//        map.put("groundGenus", groundGenus);
        return map;
    }

    /**
     * 获取首页党龄分布统计图
     *
     * @param dicContent
     * @return
     */
    @Override
    public Map<String, Object> getLoginPartyAge(DicContentVo dicContent) {
        ArrayList<String> ids = new ArrayList<>();
        ids.add("47d2567c9b0342712a17a026341cdf4f");
        ids.add("cb2f3cae31c0417d4427b45a27e7d6b0");
        ids.add("6f3ea4a19ab72068c9699980b3f03944");
        ids.add("550b8741acb7408c8869942e6074c418");
        Map<String, Object> map = new HashMap<>();
        //获取首页党龄阶段
//        List<DicContentVo> dicContentVos = UtilSystemConstant.getClassData(dicContent.getDicCode());
        //男党员党龄信息
//        List<UserDetail> maleList = this.baseMapper.getLoginPartyAge(ids,StatusCode.MALE.code);
//        //女党员党龄信息
//        List<UserDetail> femaleList = this.baseMapper.getLoginPartyAge(ids,StatusCode.FEMALE.code);
        //组装首页年龄分布雷达图
//        map.put("male", userDetailListToIntArray(dicContentVos, maleList));
//        map.put("female", userDetailListToIntArray(dicContentVos, femaleList));
        return map;
    }


    @Override
    public Map<String, Object> getPartyAge(String orgId) {
        ArrayList<String> ids = new ArrayList<>();
//        ids.add("47d2567c9b0342712a17a026341cdf4f");
//        ids.add("cb2f3cae31c0417d4427b45a27e7d6b0");
//        ids.add("6f3ea4a19ab72068c9699980b3f03944");
//        ids.add("550b8741acb7408c8869942e6074c418");
        Map<Integer, Integer> man = new TreeMap<>();
        Map<Integer, Integer> woman = new TreeMap<>();
        Map<String, Object> result = new HashMap<>();
        //查询所有的党龄
        List<DicContentVo> dicContentVos = getDicontent();
        //男党员党龄信息
        List<UserDetail> maleList = this.baseMapper.getLoginPartyAge(ids,"男");
        //女党员党龄信息
        List<UserDetail> femaleList = this.baseMapper.getLoginPartyAge(ids,"女");
        //转数组时出现部分数据丢失，重写统计方法
        dealUserDetailList(man,maleList);
        result.put("man",man.values());
        dealUserDetailList(woman,femaleList);
        result.put("woman",woman.values());
        dicContentVos.forEach(d -> d.setMax(NumberUtil.max(man.values().stream().max(Integer::compareTo).get(), woman.values().stream().max(Integer::compareTo).get())));
        result.put("name", dicContentVos);
        return result;
    }

    /**
     * 查询党员，入党积极分子，预备党员
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getLogOrg(String orgId) {
        List<Map<String, Object>> maps = this.baseMapper.getLogOrg(orgId);
        return maps;
    }

    @Override
    public List<UserDetail> getPersonManagementDowload(UserDetail userDetail) {
        List<UserDetail> userDetailList = this.baseMapper.getPersonManagementDowload(userDetail);
        //获取现任党内职务
        getPostNameToUserDetailList(userDetailList);
        return userDetailList;
    }

    @Override
    public UserTotalVo countPersonnelStrength(UserDetail userDetail, Page page) {
        UserTotalVo totalVo = new UserTotalVo();
        List<UserDetail> personManagementPage = userDetailMapper.getPersonManagementPage(userDetail);
        ArrayList<UserTotalVo> list = new ArrayList<>();
        int partyCount=0;//党员
        int prePartyCount=0;//预备党员
        int womanPartCount=0;//女党员
        int jjPartyCount=0;//积极分子
        int ssPartyCount=0;//少数名族
        if (CollUtil.isNotEmpty(personManagementPage)) {
            for (UserDetail userDetail1 : personManagementPage) {
                if (userDetail1.getPartyType() == null || userDetail1.getNation() == null) {
                    continue;
                }
                if (userDetail1.getPartyType().equals("0") || userDetail1.getPartyType().equals("1") || userDetail1.getPartyType().equals("2")) {
                    if (userDetail1.getPartyType().equals("0")){
                        partyCount++;
                    } if (userDetail1.getPartyType().equals("1")){
                        prePartyCount++;
                    } if (userDetail1.getPartyType().equals("2")){
                        jjPartyCount++;
                    } if ((!userDetail1.getPartyType().equals("2"))&&userDetail1.getSex().equals("1")){
                        womanPartCount++;
                    } if ((!userDetail1.getPartyType().equals("2"))&&!userDetail1.getNation().equals("0")){
                        ssPartyCount++;
                    }
                }
                if (StringUtils.isEmpty(userDetail1.getOrgId())){
                    totalVo.setPartyName(null);
                }
                Org org = orgMapper.selectById(userDetail1.getOrgId());
                if (null!=org) {
                    totalVo.setPartyName(org.getName());
                }
            }
            list.add(totalVo);
            page.setTotal(list.size());
            page.setRecords(list);
        }
        totalVo.setPartyCount(partyCount);
        totalVo.setPrePartyCount(prePartyCount);
        totalVo.setWomanPartCount(womanPartCount);
        totalVo.setJjPartyCount(jjPartyCount);
        totalVo.setSsPartyCount(ssPartyCount);

        return totalVo;
    }

    /**
     * 查询党委一张图党员，入党积极分子，预备党员
     * @param orgId
     * @return
     */
    @Override
    public List<Map<String, Object>> getLogPartyCount(String orgId) {
        ArrayList<String> ids = new ArrayList<>();

//        ids.add("47d2567c9b0342712a17a026341cdf4f");
//        ids.add("cb2f3cae31c0417d4427b45a27e7d6b0");
//        ids.add("6f3ea4a19ab72068c9699980b3f03944");
//        ids.add("550b8741acb7408c8869942e6074c418");
        List<Map<String, Object>> mapList = this.baseMapper.getLogPartyCount(ids);


        return mapList;
    }

    @Override
    public UserDetail findByUserId(String userId) {
        return this.baseMapper.findByUserId(userId);
    }

    @Override
    public List<TreeViewVo> findUsersByOrgId2(String orgId) {
        return this.baseMapper.findUsersByOrgId2(orgId);
    }

    /**
     * 公共处理年龄分布阶段
     *
     * @param dicContentVos  年龄阶段
     * @param userDetailList 党员年龄信息
     * @return
     */
    public int[] userDetailToIntArray(List<DicContentVo> dicContentVos, List<UserDetail> userDetailList) {
        //初始化返回值
        int[] ageArray = new int[dicContentVos.size()];
        //循环年龄阶段
        for (int i = 0; i < dicContentVos.size(); i++) {
            //如果此时为最后一个则退出
            if (i == dicContentVos.size() - 1) {
                for (int j = userDetailList.size() - 1; j >= 0; j--) {
                    if (Integer.valueOf(dicContentVos.get(i).getValue()) >= userDetailList.get(j).getAge()) {
                        ageArray[i] += 1;
                        userDetailList.remove(j);
                    }
                }
                break;
            }
            for (int j = userDetailList.size() - 1; j >= 0; j--) {
                if (Integer.valueOf(dicContentVos.get(i).getValue()) <= userDetailList.get(j).getAge() && userDetailList.get(j).getAge() < Integer.valueOf(dicContentVos.get(i + 1).getValue())) {
                    ageArray[i] += 1;
                    userDetailList.remove(j);
                }
            }
        }
        return ageArray;
    }

    //s首页党龄标准
    public List<DicContentVo> getDicontent() {
        List<DicContentVo> dicContentVoList = new ArrayList<>();
        dicContentVoList.add(new DicContentVo() {{
            setComments("5年以下");
            setDicCode("0");
            setValue("0");
        }});
        dicContentVoList.add(new DicContentVo() {{
            setComments("5到10年");
            setDicCode("5");
            setValue("5");
        }});
        dicContentVoList.add(new DicContentVo() {{
            setComments("10到15年");
            setDicCode("10");
            setValue("10");
        }});
        dicContentVoList.add(new DicContentVo() {{
            setComments("15到20年");
            setDicCode("15");
            setValue("15");
        }});
        dicContentVoList.add(new DicContentVo() {{
            setComments("20到25年");
            setDicCode("20");
            setValue("20");
        }});
        dicContentVoList.add(new DicContentVo() {{
            setComments("25年以上");
            setDicCode("25");
            setValue("25");
        }});
        return dicContentVoList;
    }


    @Override
    public List<UserDetail> getPersonByOrgId(UserDetail userDetail) {
        return baseMapper.getPersonByOrgId(userDetail);
    }


    /**
     * 获取入党积极分子信息
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public UserDetailVo getPartyActivistsDetail(String userId) {
        //获取入党积极分子信息
        UserDetail userDetail = this.baseMapper.getPartyActivistsDetail(userId);
        if (userDetail == null || "".equals(userDetail)) {
            return null;
        }
        String facePic = userDetail.getFacePic();
        if (StringUtils.isNotEmpty(facePic) && facePic.contains("?")) {
            int index = facePic.indexOf("?");
            facePic = facePic.substring(0, index);
            userDetail.setFacePic(facePic);
        }
        //根据组织id获取组织信息
//        Org org = orgService.findById(userDetail.getOrgId());
//        UserDetailVo userDetailVo = new UserDetailVo();
//        UtilBean.copyTo(userDetail, userDetailVo);
//        if (org != null && !"".equals(org)) {
//            userDetailVo.setOrgName(org.getName());
//        }
        /*if (StringUtils.isNotBlank(userDetailVo.getFacePic())) {
            String filePath = attachmentsService.findFilePath(userDetailVo.getFacePic());
            userDetailVo.setFacePicPath(filePath);
        }*/
//        return userDetailVo;
        return null;
    }

    /**
     * 获取现任党内职务
     *
     * @param userDetailList
     */
    public void getPostNameToUserDetailList(List<UserDetail> userDetailList) {
        //获取现任党内职位
        List<Post> postList = postService.findList(new Post());
        //不为空
        if (userDetailList.size() > 0) {
            for (UserDetail detail : userDetailList) {
                for (Post post : postList) {
                    //匹配
                    if (post.getId().equals(detail.getPartyPosts())) {
                        detail.setPostName(post.getPostName());
                        //匹配成功则进行下一个人的对比
                        break;
                    } else {
                        detail.setPostName("无");
                    }
                }
            }
        }
    }

    /**
     * 获取数据统计人员信息综合表
     *
     * @param userDetail 用户详情
     * @param page       分页参数
     * @return
     */
    @Override
    public Page getStatisticsPersonManagementPage(UserDetail userDetail, Page page) {
        List<UserDetail> userDetailList = this.baseMapper.getStatisticsPersonManagementPage(userDetail, page);
        //获取现任党内职务
        getPostNameToUserDetailList(userDetailList);
        //组装进page
        page.setRecords(userDetailList);
        return page;
    }

    @Override
    public List<UserDetail> getStatisticsPersonManagement(UserDetail userDetail) {
        Page page = new Page();
        List<UserDetail> userDetailList = this.baseMapper.getStatisticsPersonManagementPage(userDetail, page);
        //获取现任党内职务
        getPostNameToUserDetailList(userDetailList);
        return userDetailList;
    }

    @Override
    public List<UserDetail> getStatisticsPersonManagementList(UserDetail userDetail) {
        List<UserDetail> userDetailList = this.baseMapper.getStatisticsPersonManagementList(userDetail);
        //获取现任党内职务
        getPostNameToUserDetailList(userDetailList);
        return userDetailList;
    }

    @Override
    public void exportPartyMember(UserDetail userDetail, HttpServletResponse response) {
        List<UserDetail> userDetailList = this.baseMapper.getPersonManagementPage(userDetail);
        //获取现任党内职务
        getPostNameToUserDetailList(userDetailList);
        int row = 0;
        for (UserDetail detail : userDetailList) {
            row = row + 1;
            detail.setRowNo(row);
        }
        StringBuffer title = new StringBuffer();
//        if (StringUtils.isNotEmpty(userDetail.getOrgId())) {
//            Org org = orgService.findById(userDetail.getOrgId());
//            if (org != null) {
//                title.append(org.getName());
//            }
//        }
//        if (StringUtils.isNotEmpty(userDetail.getPartyType())) {
//            title.append(UtilDic.getChNameByCode(YTDicConstant.POLITICAL.CODE, userDetail.getPartyType()));
//        }
//        title.append("花名册");
//        if (userDetailList.size() > 0) {
//            title.append("(共计：" + userDetailList.size() + "人)");
//        }
//
//        ExcelVo excelVo = new ExcelVo();
//        excelVo.setResponse(response);
//        excelVo.setFileName("党员信息花名册.xls");
//        excelVo.setTemplateName("党员信息花名册.xls");
//        Context context = new Context();
//
//        context.putVar("title", title);
//        context.putVar("list", userDetailList);
//        excelVo.setContext(context);
//        try {
//            UtilExcel.getInstance().exportExcelByStream(excelVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public String getRecordPic(String id) {
        UserDetail detail = this.baseMapper.selectById(id);
        if (detail != null) {
            return detail.getRecordPicBase64();
        }
        return null;
    }


}
