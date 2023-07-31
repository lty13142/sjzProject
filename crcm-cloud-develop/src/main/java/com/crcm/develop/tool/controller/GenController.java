package com.crcm.develop.tool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.develop.core.base.BaseController;
import com.crcm.develop.core.base.RestResult;
import com.crcm.develop.core.text.Convert;
import com.crcm.develop.tool.domain.GenTable;
import com.crcm.develop.tool.domain.GenTableColumn;
import com.crcm.develop.tool.service.DataTableService;
import com.crcm.develop.tool.service.GenTableColumnService;
import com.crcm.develop.tool.service.GenTableService;
import com.crcm.develop.tool.vo.TableDataInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author zzyt
 */
@RestController
@RequestMapping("/tool/gen")
@AllArgsConstructor
@Slf4j
public class GenController extends BaseController {
    private GenTableService genTableService;
    private GenTableColumnService genTableColumnService;
    private DataTableService dataTableService;

    /**
     * 查询代码生成列表
     */
    // @PreAuthorize("@ss.hasPermi('tool:gen:list')")
    @GetMapping("/list")
    public RestResult genList(Page page, GenTable genTable) {
        IPage<GenTable> page1 = genTableService.selectPageGenTable(page, genTable);
        return RestResult.succeed(page1);
    }

    /**
     * 修改代码生成业务
     */
    // @PreAuthorize("@ss.hasPermi('tool:gen:query')")
    @GetMapping(value = "/{talbleId}")
    public RestResult getInfo(@PathVariable String talbleId) {
        GenTable table = genTableService.selectGenTableById(talbleId);
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(talbleId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        return RestResult.succeed(map);
    }

    /**
     * 查询数据库列表
     */
//    @PreAuthorize("@ss.hasPermi('tool:gen:list')")
    @GetMapping("/db/list")
    public RestResult dataList(GenTable genTable, String datasourceId) {
        List<String> importedNames = new ArrayList<>();
        // 切换数据源
        if (StringUtils.isNotBlank(datasourceId)) {
            List<GenTable> tables = genTableService.findTablesImported(datasourceId);
            tables.stream().forEach(genTable1 -> {
                importedNames.add(genTable1.getTableName());
            });
        }
        List<GenTable> list = dataTableService.findDbTableList(genTable, importedNames,datasourceId);
        // 重置数据源
        return RestResult.succeed(list);
    }

    /**
     * 查询数据表字段列表
     */
    // @PreAuthorize("@ss.hasPermi('tool:gen:list')")
    @GetMapping(value = "/column/{talbleId}")
    public TableDataInfo columnList(String tableId) {
        TableDataInfo dataInfo = new TableDataInfo();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    public RestResult importTableSave(String tables, String datasourceId) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = dataTableService.findDbTableListByNames(tableNames, datasourceId);
        // 查询表列数据
        Map<String, List<GenTableColumn>> columnMap = new HashMap<>();
        for (GenTable genTable : tableList) {
            List<GenTableColumn> columnList = dataTableService.selectDbTableColumnsByName(genTable.getTableName(), datasourceId);
            columnMap.put(genTable.getTableName(), columnList);
        }
        // 导入表数据
        genTableService.importGenTable(tableList, columnMap, datasourceId);
        return RestResult.succeed();
    }

    /**
     * 修改保存代码生成业务
     */
    // @PreAuthorize("@ss.hasPermi('tool:gen:edit')")
//    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PutMapping
    public RestResult editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return RestResult.succeed();
    }

    /**
     * 删除代码生成
     */
    // @PreAuthorize("@ss.hasPermi('tool:gen:remove')")
//    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableIds}")
    public RestResult remove(@PathVariable String[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return RestResult.succeed();
    }

    /**
     * 预览代码
     */
    // @PreAuthorize("@ss.hasPermi('tool:gen:preview')")
    @GetMapping("/preview/{tableId}")
    public RestResult preview(@PathVariable("tableId") String tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return RestResult.succeed(dataMap);
    }

    /**
     * 生成代码
     */
    // @PreAuthorize("@ss.hasPermi('tool:gen:code')")
//    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.generatorCode(tableName);
        genCode(response, data);
    }

    /**
     * 批量生成代码
     */
    // @PreAuthorize("@ss.hasPermi('tool:gen:code')")
//    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.generatorCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();

        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        // JSONP 解决跨域问题
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        IOUtils.write(data, response.getOutputStream());
    }


}