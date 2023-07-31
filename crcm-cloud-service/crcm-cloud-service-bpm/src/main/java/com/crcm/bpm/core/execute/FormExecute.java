package com.crcm.bpm.core.execute;

public interface FormExecute {

    // 保存到草稿箱标识
    String PROCESS_CODE_DRAFT = "draft";

    // 定制表单发起、审核执行方法
    void execute(Long applyId, String formData, String authority) throws Exception;

    // 定制表单流程结束执行方法
    void finishExecute(Long applyId) throws Exception;

}
