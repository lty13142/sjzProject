package com.crcm.bpm.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crcm.bpm.domain.entity.ReportColumnDO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ReportColumnService extends IService<ReportColumnDO> {
    boolean saveHistory(List<ReportColumnDO> reportColumn);

    boolean updateHistory(List<ReportColumnDO> reportColumn);

    int deleteById(String id);

    List<ReportColumnDO> findById(String id);

    Object findList(ReportColumnDO reportColumn);

    Page<ReportColumnDO> findPage(Page page, ReportColumnDO reportColumn);

    List<Tree<String>> treeReportColumn();

    Page searchPage(Page page, ReportColumnDO reportColumn);

    void downloadBatchByIds(ReportColumnDO reportColumn, HttpServletResponse response);
}
