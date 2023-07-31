package com.crcm.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.crcm.core.response.RestResult;
import com.crcm.license.LicenseManagerHolder;
import com.crcm.license.LicenseVerify;
import com.crcm.license.core.domain.LicenseCheckModel;
import com.crcm.license.core.domain.LicenseVerifyParam;
import com.crcm.license.core.info.AbstractServerInfos;
import com.crcm.license.core.info.LinuxServerInfos;
import com.crcm.license.core.info.WindowsServerInfos;
import com.crcm.license.core.utils.LicenseUtil;
import com.crcm.license.properties.LicenseProperties;
import de.schlichtherle.license.LicenseManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName CommonController
 * @Description：
 * @Copyright：Copyright(c) 2021
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2021/9/13
 **/
@Slf4j
@RestController
@RequestMapping("/license")
@RequiredArgsConstructor
public class LicenseController {

    private final ApplicationContext applicationContext;
    private final LicenseProperties licenseProperties;


    /**
     * 获取服务器硬件信息
     *
     * @param osName 操作系统类型，如果为空则自动判断
     * @return com.ccx.models.license.LicenseCheckModel
     */
    @RequestMapping(value = "/serverInfo", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public RestResult<LicenseCheckModel> getServerInfos(@RequestParam(value = "osName", required = false) String osName) {
        //操作系统类型
        if (StringUtils.isBlank(osName)) {
            osName = System.getProperty("os.name");
        }
        osName = osName.toLowerCase();

        AbstractServerInfos abstractServerInfos = null;

        //根据不同操作系统类型选择不同的数据获取方法
        if (osName.startsWith("windows")) {
            abstractServerInfos = new WindowsServerInfos();
        } else if (osName.startsWith("linux")) {
            abstractServerInfos = new LinuxServerInfos();
        } else {//其他服务器类型
            abstractServerInfos = new LinuxServerInfos();
        }
        return RestResult.succeed(abstractServerInfos.getServerInfos());
    }


    @SneakyThrows
    @RequestMapping(value = "/licenseInfo")
    public RestResult getLicenseInfo() {
        LicenseManager licenseManager = LicenseManagerHolder.getInstance(null);
        return RestResult.succeed(BeanUtil.beanToMap(licenseManager.verify()));
    }


    @SneakyThrows
    @PostMapping(value = "/install")
    public RestResult install(@RequestParam("file") MultipartFile file) {
        String savePath = LicenseUtil.getRootPath() + "/license.lic";
        File targetFile = new File(savePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(targetFile);
            out.write(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.install();
        return RestResult.succeed(null, "安装成功！");
    }

    private void install() {
        log.info("++++++++ 开始安装证书 ++++++++");
        LicenseVerifyParam param = new LicenseVerifyParam();
        param.setSubject(licenseProperties.getSubject());
        param.setPublicAlias(licenseProperties.getPublicAlias());
        param.setStorePass(licenseProperties.getStorePass());
        param.setLicensePath(licenseProperties.getLicensePath());
        param.setPublicKeysStorePath(licenseProperties.getPublicKeysStorePath());
        LicenseVerify licenseVerify = new LicenseVerify();
        //安装证书
        licenseVerify.install(param);
        log.info("++++++++ 证书安装结束 ++++++++");
    }
}
