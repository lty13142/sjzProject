package com.sjz.partbuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.admin.api.feign.RemoteOrgService;
import com.crcm.cloud.start.sso.utils.SecurityUtil;
import com.crcm.core.exception.CustomException;
import com.sjz.partbuilding.constants.YTSystemConstant;
import com.sjz.partbuilding.mapper.OrgEventMapper;
import com.sjz.partbuilding.mapper.OrgMapper;
import com.sjz.partbuilding.model.dto.StatisticsDTO;
import com.sjz.partbuilding.model.entity.*;
import com.sjz.partbuilding.model.vo.*;
import com.sjz.partbuilding.service.AttachmentsService;
import com.sjz.partbuilding.service.OrgEventService;
import com.sjz.partbuilding.service.OrgService;
import com.sjz.partbuilding.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Slf4j
@Service
@Transactional
public class OrgEventServiceImpl extends ServiceImpl<OrgEventMapper, OrgEvent> implements OrgEventService {

    private String regex = ",";

    @Autowired
    private AttachmentsService attachmentsService;
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private MinioProperties minioProperties;
    @Autowired
    private OrgService orgService;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private UserDetailService userDetailService;


    @Override
    public int saveOrgEvent(OrgEvent orgEvent) {
        if(orgEvent.getPresentCount()!=null&&orgEvent.getPresentCount()==0){
            throw new CustomException("出席人数不能为0");
        }
        if (validate(orgEvent)) {
            throw new CustomException(orgEvent.getTheme() + "已经存在");
        }
        String orgId = userDetailService.findByUserId(SecurityUtil.getCurrentUser().getUserId()).getOrgId();
        orgEvent.setOrgId(orgId);

        orgEvent.setHost(SecurityUtil.getCurrentUser().getUserId());
        Org byId = orgMapper.selectById(orgId);
        orgEvent.setOrgName(byId.getName());
        return this.baseMapper.insert(orgEvent);
    }

    @Override
    public int updateOrgEvent(OrgEvent orgEvent) {
        if(orgEvent.getPresentCount()!=null&&orgEvent.getPresentCount()==0){
            throw new CustomException("出席人数不能为0");
        }
        if (validate(orgEvent)) {
            throw new CustomException(orgEvent.getTheme() + "已经存在");
        }
        if(StringUtils.isEmpty(orgEvent.getAbsenceIds())){
            orgEvent.setAbsenceIds(",");
        }
        if(StringUtils.isEmpty(orgEvent.getAttendIds())){
            orgEvent.setAttendIds(",");
        }
        UserDetail detail = userDetailService.findById(SecurityUtil.getCurrentUser().getUserId());
        if (null != detail) {
            orgEvent.setOrgId(detail.getOrgId());
        }
        return this.baseMapper.updateById(orgEvent);
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
    public OrgEventVo findById(String id) {
        OrgEventVo orgEvent = this.baseMapper.selectCustomerById(id);
        orgEvent.setMaterialsList(attachmentsService.getAttachmentListByIds(orgEvent.getMaterialIds()));
        orgEvent.setNotesList(attachmentsService.getAttachmentListByIds(orgEvent.getNotesIds()));
        orgEvent.setContent(updateContent(orgEvent.getContent()));
        return orgEvent;
    }

    /**
     * 对新闻富文本内容加工-图片地址增加服务器地址和文件根目录
     *
     * @param content
     * @return
     */

    public String updateContent(String content) {
        // 图片标志
        final String imageSign = "src";
//        String url = minioProperties.getUrl() + "/" + minioProperties.getBucketName();
//        if (StringUtils.isNotBlank(content) && content.contains(imageSign)) {
//            return content.replaceAll("src=\"", "src=\"" + url);
//        }
        return content;
    }

    @Override
    public List<OrgEvent> findList(OrgEvent orgEvent) {
        QueryWrapper<OrgEvent> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<OrgEventVo> findPage(Page page, OrgEvent orgEvent) {
        if (StringUtils.isBlank(orgEvent.getType())) {
            throw new CustomException("type字段不能为空");
        }

        IPage<OrgEventVo> orgEventVoIPage = this.baseMapper.selectCustomerPage(page, orgEvent);
        List<OrgEventVo> records = orgEventVoIPage.getRecords();
        List<OrgEventVo> newRecords = new ArrayList<>();

        for (OrgEventVo eventVo : records) {
            // 出席
            String presentIds = eventVo.getPresentIds();
            if (StringUtils.isNotBlank(presentIds)) {
                String[] split1 = presentIds.split(",");
                String present = "";
                for (String presentId : split1) {
                    UserDetail personInfoDetail = userDetailService.getById(presentId);
//                    UserDetailVo personInfoDetail = userDetailService.getPersonInfoDetail(presentId);
                    if(personInfoDetail!=null) {
                        String name = personInfoDetail.getName();
                        if (present.equals("")) {
                            present = name;
                        } else {
                            present += "，" + name;
                        }
                    }
                }
                eventVo.setPresent(present);
            }
            // 列席
            String attendIds = eventVo.getAttendIds();
            if (StringUtils.isNotBlank(attendIds)) {
                String[] split2 = attendIds.split(",");
                String attend = "";
                for (String attendId : split2) {
                    UserDetail personInfoDetail = userDetailService.getById(attendId);
//                    UserDetailVo personInfoDetail = userDetailService.getPersonInfoDetail(attendId);
                    if(personInfoDetail!=null) {
                        String name = personInfoDetail.getName();
                        if (attend.equals("")) {
                            attend = name;
                        } else {
                            attend += "，" + name;
                        }
                    }
                }
                eventVo.setAttend(attend);
            }
            // 缺席
            String absenceIds = eventVo.getAbsenceIds();
            if (StringUtils.isNotBlank(absenceIds)) {
                String[] split3 = absenceIds.split(",");
                String absence = "";
                for (String absenceId : split3) {
                    UserDetail personInfoDetail = userDetailService.getById(absenceId);
//                    UserDetailVo personInfoDetail = userDetailService.getPersonInfoDetail(absenceId);
                    if(personInfoDetail!=null) {
                        String name = personInfoDetail.getName();
                        if (absence.equals("")) {
                            absence = name;
                        } else {
                            absence += "，" + name;
                        }
                    }
                }
                eventVo.setAbsence(absence);
            }
            newRecords.add(eventVo);
        }
        orgEventVoIPage.setRecords(newRecords);
        return orgEventVoIPage;
    }


    @Override
    public IPage<OrgEventVo> getMeets(Page page, String title) {
//        String orgId = this.userService.findById(UserContext.current().getUserId()).getOrgId();
//        IPage<OrgEventVo> orgEventVoIPage = this.baseMapper.selectMeets(page, title, orgId);
//        List<OrgEventVo> records = orgEventVoIPage.getRecords();
//        if(records!=null&&records.size()>0){
//            for (OrgEventVo record : records) {
//                String presentNames="";
//                String attendNames="";
//                String absenceNames="";
//                //出席人员名单
//                String presentIds = record.getPresentIds();
//                if(StringUtils.isNotEmpty(presentIds)){
//                    String[] ids = presentIds.split(",");
//                    for (int i = 0; i < ids.length; i++) {
//                        User user = userService.findById(ids[i]);
//                        if(user==null){
//                            continue;
//                        }
//                        presentNames+=user.getUsername()+",";
//                    }
//                    if(StringUtils.isNotEmpty(presentNames)){
//                        presentNames=presentNames.substring(0, presentNames.length() - 1);
//                    }
//                }
//                //列席人员名单
//                String attendIds = record.getAttendIds();
//                if(StringUtils.isNotEmpty(attendIds)){
//                    String[] ids = attendIds.split(",");
//                    for (int i = 0; i < ids.length; i++) {
//                        User user = userService.findById(ids[i]);
//                        if(user==null){
//                            continue;
//                        }
//                        attendNames+=user.getUsername()+",";
//                    }
//                    if(StringUtils.isNotEmpty(attendNames)){
//                        attendNames=attendNames.substring(0,attendNames.length()-1);
//                    }
//                }
//                //缺席人员名单
//                String absenceIds = record.getAbsenceIds();
//                if(StringUtils.isNotEmpty(absenceIds)){
//                    String[] ids = absenceIds.split(",");
//                    for (int i = 0; i < ids.length; i++) {
//                        User user = userService.findById(ids[i]);
//                        if(user==null){
//                            continue;
//                        }
//                        absenceNames+=user.getUsername()+",";
//                    }
//                    if(StringUtils.isNotEmpty(absenceNames)){
//                        absenceNames=absenceNames.substring(0,absenceNames.length()-1);
//                    }
//                }
//                record.setPresentNames(presentNames);
//                record.setAttendNames(attendNames);
//                record.setAbsenceNames(absenceNames);
//                record.setNotesList(attachmentsService.getMinIoListByAttIds(record.getNotesIds()));
//            }
//        }
//        this.baseMapper.selectMeets(page, title, orgId);
        return null;
    }



    private boolean validate(OrgEvent orgEvent) {
        LambdaQueryWrapper<OrgEvent> wrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(orgEvent.getId())) {
            wrapper.ne(OrgEvent::getId, orgEvent.getId())
                    .eq(OrgEvent::getOrgId,orgEvent.getOrgId())
                    .eq(OrgEvent::getTheme, orgEvent.getTheme());
        } else {
            wrapper.eq(OrgEvent::getOrgId,orgEvent.getOrgId())
                    .eq(OrgEvent::getTheme, orgEvent.getTheme());
        }
        return this.baseMapper.selectCount(wrapper) > 0;
    }

    @Override
    public List<TypeVo> findTypeList(OrgEvent orgEvent) {
        QueryWrapper<OrgEvent> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(orgEvent.getOrgId())) {
            queryWrapper.eq("org_id", orgEvent.getOrgId());
        }
        List<OrgEvent> orgEvents = this.baseMapper.selectList(queryWrapper);
        List<TypeVo> typeVos = new ArrayList<>();
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        for (OrgEvent event : orgEvents) {
            if (event.getType().equals("0")) {
                a++;
            } else if (event.getType().equals("1")) {
                b++;
            } else if (event.getType().equals("2")) {
                c++;
            } else if (event.getType().equals("3")) {
                d++;
            } else {
                e++;
            }
        }
        for (int i = 0; i < 5; i++) {
            TypeVo typeVo = new TypeVo();
            typeVo.setType(i + "");
            if (i == 0) {
                typeVo.setTypeName("党员大会");
                typeVo.setNumber(a + "");
            } else if (i == 1) {
                typeVo.setTypeName("支委会");
                typeVo.setNumber(b + "");
            } else if (i == 2) {
                typeVo.setTypeName("党小组会");
                typeVo.setNumber(c + "");
            } else if (i == 3) {
                typeVo.setTypeName("党课");
                typeVo.setNumber(d + "");
            } else {
                typeVo.setTypeName("主题党日活动");
                typeVo.setNumber(e + "");
            }
            typeVos.add(typeVo);
        }
        return typeVos;
    }

    /**
     * 获取数据统计组织生活综合表
     *
     * @param orgEvent
     * @return
     */
    @Override
    public List<EventStatisticsVo> getStatisticsOrgEvent(OrgEvent orgEvent) {
        Org org = orgMapper.findById(orgEvent.getOrgId());
//        String orgName = org.getName();
//        if(StringUtils.isNotEmpty(orgEvent.getOrgName())&&orgName.indexOf(orgEvent.getOrgName())==-1){
//            return null;
//        }
        //获取当前月和前11月
        List<EventStatisticsVo> eventList = this.baseMapper.getMonthStatisticsOrgEvent(orgEvent);
        //组装返回参数
        int congressOfParty = 0;
        int branchCommittee = 0;
        int partyGroupMeeting = 0;
        int partyClass = 0;
        int themePartyDay = 0;
        int orgLifeMeeting = 0;
        if (eventList.size() > 0) {
            for (EventStatisticsVo vo : eventList) {
                if (YTSystemConstant.ORG_EVENT_TYPE.CONGRESS_OF_PARTY.equals(vo.getType())) {
                    congressOfParty++;
                } else if (YTSystemConstant.ORG_EVENT_TYPE.BRANCH_COMMITTEE.equals(vo.getType())) {
                    branchCommittee++;
                } else if (YTSystemConstant.ORG_EVENT_TYPE.PARTY_GROUP_MEETING.equals(vo.getType())) {
                    partyGroupMeeting++;
                } else if (YTSystemConstant.ORG_EVENT_TYPE.PARTY_CLASS.equals(vo.getType())) {
                    partyClass++;
                } else if (YTSystemConstant.ORG_EVENT_TYPE.THEME_PARTY_DAY.equals(vo.getType())) {
                    themePartyDay++;
                } else if (YTSystemConstant.ORG_EVENT_TYPE.ORG_LIFE_MEETING.equals(vo.getType())) {
                    orgLifeMeeting++;
                }
            }
        }
        //组装返回值
        EventStatisticsVo eventStatisticsVo = new EventStatisticsVo();
        eventStatisticsVo.setOrgName(org.getName());
        eventStatisticsVo.setCongressOfParty(congressOfParty);
        eventStatisticsVo.setBranchCommittee(branchCommittee);
        eventStatisticsVo.setPartyGroupMeeting(partyGroupMeeting);
        eventStatisticsVo.setPartyClass(partyClass);
        eventStatisticsVo.setThemePartyDay(themePartyDay);
        eventStatisticsVo.setOrgLifeMeeting(orgLifeMeeting);
        List<EventStatisticsVo> eventStatisticsVoList = new ArrayList<>();
        eventStatisticsVoList.add(eventStatisticsVo);
        return eventStatisticsVoList;
    }

    @Override
    public IPage<OrgEvenVo> getFileOrgEvent(OrgEvent orgEvent, Page page) {

        IPage<OrgEvenVo> orgEvenVos = baseMapper.selectLists(page,orgEvent);
        for (OrgEvenVo e:orgEvenVos.getRecords()){
            if (StringUtils.isNotEmpty(e.getNotesIds())){
                List<Attachments> minIoListByAttIds = attachmentsService.getMinIoListByAttIds(e.getNotesIds());
//                e.setNotesList(minIoListByAttIds);
//                if (minIoListByAttIds.size()>0){
//                    e.setFilePath(minIoListByAttIds.get(0).getMinioPath());
//                    e.setFileName(minIoListByAttIds.get(0).getFileFullName());
//                }

            }
        }
        return orgEvenVos;
    }

    @Override
    public OrgMapVo findTypeMap() {
        OrgMapVo orgMapVo = new OrgMapVo();
        List<OrgEventsVo> list = baseMapper.findTypeMap();
        ArrayList<Object> arrayList1 = new ArrayList<>();
        ArrayList<Object> arrayList2 = new ArrayList<>();
        ArrayList<Object> arrayList3 = new ArrayList<>();
        ArrayList<Object> arrayList4 = new ArrayList<>();
        ArrayList<Object> arrayList5 = new ArrayList<>();
        //创建6给数组
        if(list!=null&&list.size()>0){
            for (OrgEventsVo orgEventsVo : list) {
                arrayList1.add(orgEventsVo.getMonth());
                //orgMapVo.setMonth(arrayList1);
                arrayList2.add(orgEventsVo.getDangyuan());
               // orgMapVo.setDangwei(arrayList2);
                arrayList3.add(orgEventsVo.getZhiwei());
               // orgMapVo.setZhiweihui(arrayList3);
                arrayList4.add(orgEventsVo.getXiaozu());
               // orgMapVo.setDangxiaozu(arrayList4);
                arrayList5.add(orgEventsVo.getDangke());
               // orgMapVo.setDangke(arrayList5);
            }
            orgMapVo.setMonth(arrayList1);
            orgMapVo.setDangwei(arrayList2);
            orgMapVo.setZhiweihui(arrayList3);
            orgMapVo.setDangxiaozu(arrayList4);
            orgMapVo.setDangke(arrayList5);
        }
        return orgMapVo;
    }

    @Override
    public List<StatisticsDTO> getParticipationRateMonthly(String orgId) {
        //获取当前登录人
//        String userId = UserContext.current().getUserId();
//        User user = userService.findById(userId);

        int year = Calendar.getInstance().get(Calendar.YEAR);
        // 查询所有组织活动的活动时间和参训率
        List<StatisticsDTO> participationRate = this.baseMapper.getParticipationRate(orgId);

        Map<String, StatisticsDTO> map = new HashMap<>();
        for (StatisticsDTO each : participationRate) {
            String time = each.getTime();
            BigDecimal rate = each.getRate();

            // 对今年的数据进行处理
            if (time.contains(String.valueOf(year))) {
                String month = time.split("-")[1];
                Boolean flag = true;
                // 遍历map是否有此键
                for (Map.Entry<String, StatisticsDTO> entry : map.entrySet()) {
                    // 如果存在这个月,添加数量和总和
                    if (month.equals(entry.getKey())) {
                        StatisticsDTO value = entry.getValue();
                        int i = value.getCount() + 1;
                        value.setCount(i);

                        BigDecimal b = value.getRate().add(rate);
                        value.setRate(b);
                        flag = false;
                        break;
                    }
                }
                // 遍历完map没有这个月的则put一条
                if (flag) {
                    StatisticsDTO dto = new StatisticsDTO();
                    dto.setCount(1);
                    dto.setRate(rate);
                    map.put(month, dto);
                }
            }
        }
        // 计算平均参训率,封装数据
        List<StatisticsDTO> list = new ArrayList<>();
        for (Map.Entry<String, StatisticsDTO> entry : map.entrySet()) {
//            Map<String, BigDecimal> resultMap = new HashMap<>();
            StatisticsDTO dto = new StatisticsDTO();
            String month = entry.getKey();
            BigDecimal rate = entry.getValue().getRate().divide(new BigDecimal(entry.getValue().getCount()), 4, BigDecimal.ROUND_HALF_UP);
            dto.setTime(month);
            dto.setRate(rate);
            list.add(dto);
        }
        return list;
    }

    @Override
    public OrgEventCensus getOrgEventCensus(String userId) {
//        User user = userService.findById(userId);
        OrgEventCensus orgEventCensus=new OrgEventCensus();
        ArrayList<String> orgEventName=new ArrayList<>();
        ArrayList<Integer> joinCount=new ArrayList<>();
        ArrayList<Integer> hostCount=new ArrayList<>();
        orgEventName.add("党员大会");
        orgEventName.add("支委会");
        orgEventName.add("党小组会");
        orgEventName.add("党课");
//        int orgEventHostCount0 = getOrgEventHostCount("0",user.getOrgId(),userId);
//        hostCount.add(orgEventHostCount0);
//        int orgEventHostCount1 = getOrgEventHostCount("1",user.getOrgId(),userId);
//        hostCount.add(orgEventHostCount1);
//        int orgEventHostCount2 = getOrgEventHostCount("2",user.getOrgId(),userId);
//        hostCount.add(orgEventHostCount2);
//        int orgEventHostCount3 = getOrgEventHostCount("3",user.getOrgId(),userId);
//        hostCount.add(orgEventHostCount3);
//
//        int orgEventJoinCount0 = getOrgEventJoinCount("0",user.getOrgId(),userId);
//        joinCount.add(orgEventJoinCount0);
//        int orgEventJoinCount1 = getOrgEventJoinCount("1",user.getOrgId(),userId);
//        joinCount.add(orgEventJoinCount1);
//        int orgEventJoinCount2 = getOrgEventJoinCount("2",user.getOrgId(),userId);
//        joinCount.add(orgEventJoinCount2);
//        int orgEventJoinCount3 = getOrgEventJoinCount("3",user.getOrgId(),userId);
//        joinCount.add(orgEventJoinCount3);

        orgEventCensus.setOrgEventName(orgEventName);
        orgEventCensus.setJoinCount(joinCount);
        orgEventCensus.setHostCount(hostCount);
        return orgEventCensus;
    }

    public int  getOrgEventHostCount(String type, String orgId, String host){
        QueryWrapper<OrgEvent> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("type",type);
        queryWrapper.eq("org_id",orgId);
        queryWrapper.eq("host",host);
        queryWrapper.eq("deleted",0);
        List<OrgEvent> orgEventList = this.baseMapper.selectList(queryWrapper);
        if(orgEventList==null){
            return 0;
        }else {
            return orgEventList.size();
        }
    }

    public int getOrgEventJoinCount(String type, String orgId, String userId){
        QueryWrapper<OrgEvent> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("type",type);
        queryWrapper.eq("org_id",orgId);
        queryWrapper.like("present_ids",userId);
        queryWrapper.eq("deleted",0);
        List<OrgEvent> orgEventList = this.baseMapper.selectList(queryWrapper);
        if(orgEventList==null){
            return 0;
        }else {
            return orgEventList.size();
        }
    }

}
