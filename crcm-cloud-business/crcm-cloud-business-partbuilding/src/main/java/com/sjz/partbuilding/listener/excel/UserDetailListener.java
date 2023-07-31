package com.sjz.partbuilding.listener.excel;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.crcm.core.utils.DateUtil;
import com.sjz.partbuilding.model.dto.UserDetailExcelDTO;
import com.sjz.partbuilding.model.entity.UserDetail;
import com.sjz.partbuilding.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class UserDetailListener extends AnalysisEventListener<UserDetailExcelDTO> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    private List<UserDetailExcelDTO> cachedList = new ArrayList<>(BATCH_COUNT);

    private UserDetailService userDetailService;

    public UserDetailListener(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public void invoke(UserDetailExcelDTO data, AnalysisContext context) {
        cachedList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedList.size() >= BATCH_COUNT) {
            if (CollUtil.isNotEmpty(cachedList)) {
                addData(cachedList);
            }
            // 存储完成清理 list
            cachedList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (CollUtil.isNotEmpty(cachedList)) {
            addData(cachedList);
        }
        log.info("所有数据解析完成！");
    }

    private void addData(List<UserDetailExcelDTO> cachedList) {
        List<UserDetail> userDetailList = new ArrayList<>();
        for (UserDetailExcelDTO excelDTO : cachedList) {
            UserDetail userDetail = new UserDetail();
            userDetail.setName(excelDTO.getUsername());
            userDetail.setSex(excelDTO.getGender());
            userDetail.setPhone(excelDTO.getPhone());
            userDetail.setPartyPosts(excelDTO.getPartyPositions());
            userDetail.setJobDuties(excelDTO.getUnit());
            userDetail.setAddress(excelDTO.getAddress());
            userDetail.setBirthday(parseBirthday(excelDTO.getBirthday()));
            userDetail.setNation(excelDTO.getNation());
            userDetail.setEnterPartyTime(DateUtil.parse(excelDTO.getJoiningTime().replace(".", "-"), DateUtil.FULL_YEAR_MONTH_DAY));
            userDetailList.add(userDetail);
        }
        userDetailService.saveBatch(userDetailList);
    }

    private Date parseBirthday(String yearMonth) {
        String replace = yearMonth.replace(".", "-") + "-01";
        return DateUtil.parse(replace, DateUtil.FULL_YEAR_MONTH_DAY);
    }

}
