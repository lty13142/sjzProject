package com.crcm.bpm.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.bpm.core.annotation.MyLog;
import com.crcm.bpm.core.constant.MyLogType;
import com.crcm.bpm.domain.entity.FormDO;
import com.crcm.bpm.service.FormService;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 流程单Controller
 *
 * @author zzyt
 * @date 2020-08-26
 */
@RestController
@RequestMapping("/flow/form")
public class FormController extends BaseController {
    @Autowired
    private FormService formService;

    /**
     * 新增流程单
     */
    @SysLog(title = "新增流程单")
    @MyLog(value = "新增流程单", type = MyLogType.FORM)
    @PostMapping("/save")
    public RestResult add(@RequestBody FormDO formDo) {
        return RestResult.succeed(formService.saveFlowForm(formDo));
    }

    /**
     * 修改流程单
     */
    @SysLog(title = "修改流程单")
    @MyLog(value = "修改流程单", type = MyLogType.FORM)
    @PostMapping("/edit")
    public RestResult edit(@RequestBody FormDO formDo) {
        return RestResult.succeed(formService.updateFlowForm(formDo), "保存成功");
    }


    /**
     * 分页查询流程单
     */
    @SysLog(title = "分页查询流程单")
    @GetMapping("/page")
    public RestResult getPage(Page page, FormDO formDo) {
        return RestResult.succeed(formService.findFlowFormPage(page, formDo));
    }

    /**
     * 查询流程单列表
     */
    @SysLog(title = "查询流程单列表")
    @GetMapping("/list")
    public RestResult getList(FormDO formDo) {
        return RestResult.succeed(formService.findFlowFormList(formDo));
    }

    /**
     * 获取流程单详细信息
     */
    @SysLog(title = "获取流程单详细信息")
    @GetMapping(value = "/{id}")
    public RestResult getInfo(@PathVariable("id") String id) {
        return RestResult.succeed(formService.findFlowFormById(id));
    }

    /**
     * 删除流程单
     */
    // @MyLog(value = "删除流程单", type = MyLogType.FORM)
    @SysLog(title = "删除流程单")
    @PostMapping(value = "/delete/{id}")
    public RestResult delete(@PathVariable("id") String id) {
            formService.deleteFlowFormById(id);
        return RestResult.succeed();
    }

    /**
     * 分页查询未使用的本公司流程单
     */
    @SysLog(title = "分页查询未使用的本公司流程单")
    @GetMapping("/getPageUnUse")
    public RestResult getPageUnUse(Page page, FormDO formDo) {
        return RestResult.succeed(formService.getPageUnUse(page, formDo));
    }

    /**
     * 复制表单
     */
    @SysLog(title = "复制表单")
    @PostMapping("/copyForm")
    public RestResult copyForm(@RequestBody FormDO formDo) {
        return RestResult.succeed(formService.copyForm(formDo));
    }

    /**
     * 中文转拼音
     * @param pinyin
     * @return
     */
    @SysLog(title = "中文转拼音")
    @GetMapping("/getPinyin")
    public RestResult getPinyin(@RequestParam(value = "pinyin") String pinyin) {
        return RestResult.succeed(StrUtil.toCamelCase(PinyinUtil.getPinyin(pinyin, "_")));
    }
}
