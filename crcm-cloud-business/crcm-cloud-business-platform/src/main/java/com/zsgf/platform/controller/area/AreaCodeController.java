package com.zsgf.platform.controller.area;

import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.dto.AreaCodeQueryDTO;
import com.zsgf.platform.service.area.AreaCodeService;
import com.zsgf.platform.vo.AreaCodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统行政区划(省平台代码一致)Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/areaCode")
@Api(tags = "系统行政区划(省平台代码一致)")
public class AreaCodeController extends BaseController {

    private final AreaCodeService areaCodeService;

    /**
     * 查询系统行政区划(省平台代码一致)列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询系统行政区划(省平台代码一致)列表")
    public RestResult<List<AreaCodeVo>> findAreaCodeList(AreaCodeQueryDTO queryDTO) {
        return RestResult.succeed(areaCodeService.findAreaCodeList(queryDTO));
    }

    /**
     * 刷新行政区划redis
     */
    @GetMapping("/refreshAreaCodeRedis")
    @ApiOperation(value = "刷新行政区划redis")
    public RestResult<Object> refreshAreaCodeRedis() {
        areaCodeService.refreshAreaCodeRedis();
        return RestResult.succeed();
    }

}
