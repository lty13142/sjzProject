package com.crcm.develop.tool.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crcm.develop.core.base.RestResult;
import com.crcm.develop.tool.entity.DatasourceConfEntity;
import com.crcm.develop.tool.service.DatasourceConfService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 数据源配置
 *
 * @author diaoy
 * @date 2020-04-01 17:12:29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/datasourceconf")
public class DatasourceConfController {

  
  private final  DatasourceConfService datasourceConfService;


  /**
   * 分页查询
   * @param page 分页对象
   * @param datasourceConfEntity 数据源配置
   * @return
   */
  @GetMapping("/page")
  public RestResult getDatasourceConfEntityPage(Page page, DatasourceConfEntity datasourceConfEntity) {
    return  RestResult.succeed(datasourceConfService.page(page,Wrappers.query(datasourceConfEntity)));
  }


  /**
   * 通过id查询数据源配置
   * @param id id
   * @return R
   */
  @GetMapping("/{id}")
  public RestResult getById(@PathVariable("id") Long id){
    return RestResult.succeed(datasourceConfService.getById(id));
  }

  /**
   * 查询出所有的数据源名称
   * @return R
   */
  @GetMapping("/list")
  public RestResult getList(){
    return RestResult.succeed(datasourceConfService.list());
  }

  /**
   * 新增数据源配置
   * @param datasourceConfEntity 数据源配置
   * @return R
   */
  @PostMapping("/save")
  public RestResult save(@RequestBody DatasourceConfEntity datasourceConfEntity){
    return RestResult.succeed(datasourceConfService.saveDataSource(datasourceConfEntity));
  }

  /**
   * 修改数据源配置
   * @param datasourceConfEntity 数据源配置
   * @return R
   */
  @PostMapping("/update")
  public RestResult updateById(@RequestBody DatasourceConfEntity datasourceConfEntity){
    return RestResult.succeed(datasourceConfService.updateDataSource(datasourceConfEntity));
  }

  /**
   * 通过id删除数据源配置
   * @param id id
   * @return R
   */
  @PostMapping("/delete/{id}")
  public RestResult removeById(@PathVariable Long id){
    return RestResult.succeed(datasourceConfService.removeById(id));
  }

  /**
   * 通过id查询数据源配置
   * @param id id
   * @return R
   */
  @GetMapping("/check/{id}")
  public RestResult testDataSource(@PathVariable("id") Long id){
    return RestResult.succeed(datasourceConfService.testDataSource(id));
  }


}
