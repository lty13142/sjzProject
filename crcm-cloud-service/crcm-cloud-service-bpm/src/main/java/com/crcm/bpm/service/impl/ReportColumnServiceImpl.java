package com.crcm.bpm.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.api.dto.response.CompanyDTO;
import com.crcm.bpm.api.vo.ReportColumnVO;
import com.crcm.bpm.domain.entity.ProcessDO;
import com.crcm.bpm.domain.entity.ReportColumnDO;
import com.crcm.bpm.mapper.ReportColumnMapper;
import com.crcm.bpm.service.ProcessService;
import com.crcm.bpm.service.ReportColumnService;
import com.crcm.cloud.start.office.utils.UtilEasyExcel;
import com.crcm.security.utils.SecurityUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ReportColumnServiceImpl extends ServiceImpl<ReportColumnMapper, ReportColumnDO> implements ReportColumnService {

//    @Resource
//    private FeignCompanyService feignCompanyService;

    @Autowired
    private ProcessService processService;
    /**
     *批量添加
     * @param reportColumn
     * @return
     */
    @Override
    public boolean saveHistory(List<ReportColumnDO> reportColumn) {
        return this.saveBatch(reportColumn);
    }

    /**
     * 修改
     * @param reportColumn
     * @return
     */
    @Override
    public boolean updateHistory(List<ReportColumnDO> reportColumn) {
            //先删除在添加
        UpdateWrapper<ReportColumnDO> deleteWrapper=new UpdateWrapper<>();
        deleteWrapper.eq("filed",reportColumn.stream().map(ReportColumnDO::getFiled).distinct().collect(Collectors.toList()).get(0));
        this.baseMapper.delete(deleteWrapper);
        return this.saveBatch(reportColumn);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(String id) {
        UpdateWrapper<ReportColumnDO> deleteWrapper=new UpdateWrapper<>();
        deleteWrapper.eq("filed",id);
        deleteWrapper.eq("deleted",0);
        return this.baseMapper.delete(deleteWrapper);
    }

    @Override
    public List<ReportColumnDO> findById(String id) {
        LambdaQueryWrapper<ReportColumnDO> reportColumnDOLambdaQueryWrapper =new LambdaQueryWrapper<>();
        reportColumnDOLambdaQueryWrapper.eq(ReportColumnDO::getFiled,id);
        return this.baseMapper.selectList(reportColumnDOLambdaQueryWrapper);
    }

    @Override
    public Object findList(ReportColumnDO reportColumn) {
        return null;
    }

    @Override
    public Page<ReportColumnDO> findPage(Page page, ReportColumnDO reportColumn) {
        LambdaQueryWrapper<ReportColumnDO> reportColumnDOLambdaQueryWrapper = new QueryWrapper<ReportColumnDO>()
                .groupBy("company_name","filed")
                .lambda()
                .eq(StringUtils.isNotBlank(reportColumn.getCompanyId()), ReportColumnDO::getCompanyId, reportColumn.getCompanyId())
                .like(StringUtils.isNotBlank(reportColumn.getName()), ReportColumnDO::getName, reportColumn.getName())
                .orderByDesc(ReportColumnDO::getUpdateTime);
        Page page1 = this.page(page, reportColumnDOLambdaQueryWrapper);
        return page1;
    }

    @Override
    public List<Tree<String>> treeReportColumn() {
        //第一节点
        Map<String, Object> parentIdMap = new HashMap<>();
        //查询公司
        // TODO 对接公司查询接口
//        RestResult<List<CompanyVo>> childrenCom = feignCompanyService.getChildrenCom();
//        List<CompanyVo> companyList = childrenCom.getData();
        List<CompanyDTO> companyList = new ArrayList<>();

        List<String> collect = companyList.stream().map(CompanyDTO::getCompanyId).collect(Collectors.toList());

        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        //添加公司
        companyList.forEach(item -> {
            if (item.getCompanyId().equals(SecurityUtil.getCurrentUserNoNull().getComId())){
                parentIdMap.put("parentId",item.getParentId());
            }
            Map<String, Object> map = new HashMap<>();
            //类型是公司
            map.put("type", 1);
            map.put("filed","");
            nodeList.add(new TreeNode<>(item.getCompanyId(), item.getParentId(), item.getCompanyName(), 1).setExtra(map));
        });
        //添加模板
        LambdaQueryWrapper<ReportColumnDO> reportColumnDOLambdaQueryWrapper = new QueryWrapper<ReportColumnDO>()
                .groupBy("filed")
                .lambda()
                .in(ReportColumnDO::getCompanyId,collect);
        List<ReportColumnDO> reportColumnDOS = this.baseMapper.selectList(reportColumnDOLambdaQueryWrapper);
        reportColumnDOS.forEach(i ->{
            Map<String, Object> map = new HashMap<>();
            //类型是模板
            map.put("type", 2);
            map.put("filed",i.getFiled());
            nodeList.add(new TreeNode<>(i.getId().toString(), i.getCompanyId(), i.getName(), 0).setExtra(map));
        });
        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setIdKey("id");
        //查询当前公司模板
        List<Tree<String>> treeNodes = TreeUtil.build(nodeList, parentIdMap.get("parentId").toString(), treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setWeight(treeNode.getWeight());
                    tree.setName(treeNode.getName());
                    // 扩展属性 ...
                    tree.putExtra("filed", treeNode.getExtra().get("filed"));
                });
        return treeNodes;
    }

    @Override
    public Page  searchPage(Page page, ReportColumnDO reportColumn) {
        //先根据Key查询processId
        LambdaQueryWrapper<ReportColumnDO> reportColumnDOLambdaQueryWrapper = new QueryWrapper<ReportColumnDO>()
                .lambda()
                .eq(ReportColumnDO::getFiled,reportColumn.getFiled());
        List<ReportColumnDO> reportColumnDOS = this.baseMapper.selectList(reportColumnDOLambdaQueryWrapper);
        //字段英文
        List<String> collect = reportColumnDOS.stream().map(a -> a.getValue()).collect(Collectors.toList());
        //head
        List<String> collect3 = reportColumnDOS.stream().map(a -> a.getColumnTypeName()).collect(Collectors.toList());
        //取key
        List<String> collect1 = reportColumnDOS.stream().map(a -> a.getProcessKey()).distinct().collect(Collectors.toList());
        List<LinkedHashMap> listMap=new ArrayList<>();

        //key查询processId
        LambdaQueryWrapper<ProcessDO> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ProcessDO::getProcessKey,collect1.get(0));
        ProcessDO one = this.processService.getOne(wrapper);

        List<ReportColumnVO> reportColumnDOIPage = this.baseMapper.searchPage(collect, one.getId(), reportColumn.getFiled());

        Map<String, List<ReportColumnVO>> collect2 = reportColumnDOIPage.stream().collect(Collectors.groupingBy(ReportColumnVO::getApplyId));
        //匹配转化
        for (Map.Entry<String, List<ReportColumnVO>> entry:collect2.entrySet()){
            //一个key就是一组;
            List<ReportColumnVO> value = entry.getValue();
            LinkedHashMap<String,Object> map=new LinkedHashMap<>();
            map.put("流程号",entry.getKey());
            List<String> temp=new ArrayList<>();
            temp.addAll(collect3);
            for(String s:collect3){
                value.forEach(e->{
                   if (s.equals(e.getColumnTypeName())){
                       map.put(e.getColumnTypeName(),e.getDataValue());
                       temp.remove(s);
                   }
                });
            }
            if (temp.size()>0){
                for (String f:temp){
                   map.put(f,"");
                }
            }
            listMap.add(map);
        }
        //在连表查询数据
        if(CollUtil.isNotEmpty(listMap)){
            //listMap.remove(0);
            int beginIndex = (int) ((page.getCurrent()-1) * page.getSize());
            int finishIndex = (int) (beginIndex + page.getSize());
            List<LinkedHashMap> sub = CollUtil.sub(listMap, beginIndex, finishIndex);
            PageHelper.startPage((int) page.getCurrent(), (int) page.getSize());

            Page info = page.setRecords(sub);
            info.setTotal(listMap.size());
            int v = new BigDecimal(info.getTotal()).divide(new BigDecimal(page.getSize()))
                    .setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
            info.setPages(v + 1);
            return info;
        }
        return null;
    }

    @Override
    public void downloadBatchByIds(ReportColumnDO reportColumn, HttpServletResponse response)  {
        //先根据Key查询processId
        LambdaQueryWrapper<ReportColumnDO> reportColumnDOLambdaQueryWrapper = new QueryWrapper<ReportColumnDO>()
                .lambda()
                .eq(ReportColumnDO::getFiled,reportColumn.getFiled());
        List<ReportColumnDO> reportColumnDOS = this.baseMapper.selectList(reportColumnDOLambdaQueryWrapper);
        //字段英文
        List<String> collect = reportColumnDOS.stream().map(a -> a.getValue()).collect(Collectors.toList());
        //head
        List<String> collect3 = reportColumnDOS.stream().map(a -> a.getColumnTypeName()).collect(Collectors.toList());
        //取key
        List<String> collect1 = reportColumnDOS.stream().map(a -> a.getProcessKey()).distinct().collect(Collectors.toList());
        //key查询processId
        LambdaQueryWrapper<ProcessDO> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ProcessDO::getProcessKey,collect1.get(0));
        ProcessDO one = this.processService.getOne(wrapper);

        List<ReportColumnVO> reportColumnDOIPage = this.baseMapper.searchPage(collect, one.getId(), reportColumn.getFiled());

        Map<String, List<ReportColumnVO>> collect2 = reportColumnDOIPage.stream().collect(Collectors.groupingBy(ReportColumnVO::getApplyId));
        // 表头集合
        List<String> head = new ArrayList<>();
        head.add("流程号");
        List<List<Object>> data = new ArrayList<>();
        //匹配转化
        for (Map.Entry<String, List<ReportColumnVO>> entry:collect2.entrySet()){
            //一个key就是一组;
            List<ReportColumnVO> value = entry.getValue();
            List<Object> list = new ArrayList<>();
            list.add(entry.getKey());
            List<String> temp=new ArrayList<>();
            temp.addAll(collect3);
            for(String s:collect3){
                value.forEach(e->{
                    if (s.equals(e.getColumnTypeName())){
                        //表头
                        head.add(e.getColumnTypeName());
                        //数据
                        list.add(e.getDataValue());
                        temp.remove(s);
                    }
                });
            }
            if (temp.size()>0){
                for (String f:temp){
                    head.add(f);
                    list.add("");
                }
            }
            data.add(list);
        }

        List<String> distinct = head.stream().distinct().collect(Collectors.toList());
        String filePath = reportColumn.getName() + System.currentTimeMillis() + ".xlsx";

        if (data.size() > 0) {
            // 导出流程统计数据
            UtilEasyExcel.exportExcel(response, filePath, data, distinct);
        }
      /*  try {
            if(listMap.size()>0){
                // 通过工具类创建writer
                ExcelWriter writer = ExcelUtil.getWriter();
                // 合并单元格后的标题行，使用默认标题样式
                //writer.merge(rows.size() - 1, "一班成绩单");
                // 一次性写出内容，使用默认样式，强制输出标题
                writer.write(listMap, true);
                //response为HttpServletResponse对象
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition","attachment;filename="+new String(reportColumn.getName().getBytes("UTF-8"),"GBK")+".xls");
                ServletOutputStream out=response.getOutputStream();
                writer.flush(out, true);
                // 关闭writer，释放内存
                writer.close();
                //此处记得关闭输出Servlet流
                IoUtil.close(out);
            }
        }catch (IOException e){
            e.printStackTrace();
        }*/
    }
}
