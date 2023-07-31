package com.sjz.governance.controller.common;

import com.crcm.core.base.BaseController;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName MapController
 * @Description 地图api
 * @Author GZL
 * @Date 2023/4/6 16:56
 * @Version 1.0
 **/
@RestController
@AllArgsConstructor
@RequestMapping("/map")
@Api(tags = "地图api")
public class MapController extends BaseController {


    @GetMapping("/getBaiDuNGeoCoder")
    public RestResult<StringBuilder> getBaiDuNGeoCoder(Double lon, Double lat) {
        StringBuilder result = new StringBuilder();
        try {
            String yx = lat + "," + lon;
            String baiDu_NGeocoder = "http://api.map.baidu.com/geocoder/v2/?location=&output=json&ak=z8XKeiFNsOpWUL6II3nkHoBu7H3hoBn7";
            String tempAddressCodeUrl = baiDu_NGeocoder.replace("=&", "=" + yx + "&");
            URL baidu_url = new URL(tempAddressCodeUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(baidu_url.openStream(), StandardCharsets.UTF_8));
            String strTemp;
            while (null != (strTemp = br.readLine())) {
                result.append(strTemp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResult.succeed(result);
    }
}
