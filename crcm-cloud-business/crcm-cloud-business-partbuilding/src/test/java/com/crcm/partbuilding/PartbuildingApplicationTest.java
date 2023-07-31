package com.crcm.partbuilding;


import com.alibaba.excel.EasyExcel;
import com.sjz.partbuilding.PartbuildingApplication;
import com.sjz.partbuilding.listener.excel.UserDetailListener;
import com.sjz.partbuilding.model.dto.UserDetailExcelDTO;
import com.sjz.partbuilding.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PartbuildingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // 解决单元测试websocket报错
@Slf4j
public class PartbuildingApplicationTest {

    @Autowired
    private UserDetailService userDetailService;

    @Test
    public void importUserDetail() {
        String filePath = "C:\\Users\\张天凯\\Desktop\\docs\\党建数据\\附件1：党员信息.xlsx";
        EasyExcel.read(filePath, UserDetailExcelDTO.class, new UserDetailListener(userDetailService)).sheet().doRead();
    }
}
