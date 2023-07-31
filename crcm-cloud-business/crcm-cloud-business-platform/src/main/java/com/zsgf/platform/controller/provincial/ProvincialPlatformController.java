package com.zsgf.platform.controller.provincial;


import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import com.zsgf.platform.dto.ProvincialDTO;
import com.zsgf.platform.service.provincial.ProvincialPlatformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 省平台接口回流Controller
 *
 * @author gzl
 * @date 2023-02-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/provincialPlatform")
@Api(tags = "省平台接口回流控制器")
public class ProvincialPlatformController extends BaseController {

    public final ProvincialPlatformService provincialPlatformService;

    /**
     * 省平台企业信息回流
     */
    @PostMapping("/companyInfo")
    @ApiOperation(value = "省平台企业信息回流")
    public RestResult<Boolean> getCompanyInfo(@RequestBody ProvincialDTO dto) {
        provincialPlatformService.syncCompanyInfo(dto);
        return RestResult.succeed();
    }

    /**
     * 省平台企业角色信息回流
     */
    @PostMapping("/companyRole")
    @ApiOperation(value = "省平台企业角色信息回流")
    public RestResult<Object> getCompanyRole(@RequestBody ProvincialDTO dto) {
        provincialPlatformService.syncCompanyRole(dto);
        return RestResult.succeed();
    }

    /**
     * 省平台经营许可信息回流
     */
    @PostMapping("/businessLicense")
    @ApiOperation(value = "省平台经营许可信息回流")
    public RestResult<Object> getBusinessLicense(@RequestBody ProvincialDTO dto) {
        provincialPlatformService.syncBusinessLicense(dto);
        return RestResult.succeed();
    }

    /**
     * 省平台废物名录信息回流
     */
    @PostMapping("/wasteList")
    @ApiOperation(value = "省平台废物名录信息回流")
    public RestResult<Object> getWasteList(@RequestBody ProvincialDTO dto) {
        provincialPlatformService.syncWasteList(dto);
        return RestResult.succeed();
    }

}
