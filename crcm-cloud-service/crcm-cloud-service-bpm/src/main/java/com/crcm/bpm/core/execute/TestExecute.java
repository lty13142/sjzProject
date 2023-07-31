package com.crcm.bpm.core.execute;

import com.crcm.bpm.domain.entity.ApplyDO;
import com.crcm.bpm.service.ApplyService;
import com.crcm.core.utils.SpringContextHolder;

/**
 * @program: open-ai-center
 * @description:
 **/
public class TestExecute implements FormExecute{

    private static ApplyService applyService = SpringContextHolder.getBean(ApplyService.class);

    @Override
    public void execute(Long applyId, String formData, String authority) throws Exception {
        // 保存到自己的业务表。。。

        System.out.println(applyId);
        System.out.println(formData);

        ApplyDO applyDO = applyService.findById("542");
        System.out.println(applyDO.getId());
    }

    @Override
    public void finishExecute(Long applyId) throws Exception {
        System.out.println(applyId);
    }
}
