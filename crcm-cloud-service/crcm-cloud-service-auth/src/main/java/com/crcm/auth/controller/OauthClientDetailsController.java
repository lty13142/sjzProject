package com.crcm.auth.controller;

import com.crcm.auth.model.entity.SysOauthClientDetails;
import com.crcm.auth.service.OauthClientDetailsService;
import com.crcm.cloud.start.data.mybatis.bean.PageT;
import com.crcm.cloud.start.log.annation.SysLog;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.constant.enums.BusinessType;
import com.crcm.core.exception.CustomException;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName OauthClientDetailsController
 * @Description 客户端配置管理
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/25
 **/
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("client")
@Api(tags = {"客户端管理"})
public class OauthClientDetailsController {
    private final OauthClientDetailsService oauthClientDetailsService;

    @GetMapping("check/{clientId}")
    @ApiOperation("检测客户端是否存在")

    public boolean checkClient(@NotBlank(message = "{required}") @PathVariable String clientId) {
        SysOauthClientDetails client = this.oauthClientDetailsService.findById(clientId);
        return client == null;
    }

    @GetMapping("secret/{clientId}")
    @ApiOperation("获取客户端的秘钥")
    public RestResult getOriginClientSecret(@NotBlank(message = "{required}") @PathVariable String clientId) {
        SysOauthClientDetails client = this.oauthClientDetailsService.findById(clientId);
        String origin = client != null ? client.getClientSecret() : StringUtils.EMPTY;
        return RestResult.succeed(origin);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询客户端")
    public RestResult oauthCliendetailsList(PageT page, SysOauthClientDetails oAuthClientDetails) {
        return RestResult.succeed(this.oauthClientDetailsService.findOauthClientDetails(page, oAuthClientDetails));
    }

    @PostMapping("/save")
    @ApiOperation("新增客户端")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @SysLog(title = "新增客户端",serviceId = ServiceNameConstants.AUTH_SERVICE,businessType = BusinessType.INSERT)
    public RestResult addOauthCliendetails(@Valid @RequestBody SysOauthClientDetails oAuthClientDetails) throws CustomException {
        this.oauthClientDetailsService.createOauthClientDetails(oAuthClientDetails);
        return RestResult.succeed(null,"添加客户端成功！");
    }

    @PostMapping("/delete/{clientId}")
    @ApiOperation("删除客户端")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @SysLog(title = "删除客户端",serviceId = ServiceNameConstants.AUTH_SERVICE,businessType = BusinessType.DELETE)
    public RestResult deleteOauthCliendetails(@PathVariable("clientId") String clientId) throws CustomException {
        this.oauthClientDetailsService.deleteOauthClientDetails(clientId);
        return RestResult.succeed(null,"删除客户端成功！");
    }

    @PostMapping("/edit")
    @ApiOperation("更新客户端信息")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @SysLog(title = "更新客户端信息",serviceId = ServiceNameConstants.AUTH_SERVICE,businessType = BusinessType.UPDATE)
    public RestResult updateOauthCliendetails(@Valid @RequestBody SysOauthClientDetails oAuthClientDetails) throws CustomException {
        this.oauthClientDetailsService.updateOauthClientDetails(oAuthClientDetails);
        return RestResult.succeed(null,"更新客户端成功！");
    }
}
