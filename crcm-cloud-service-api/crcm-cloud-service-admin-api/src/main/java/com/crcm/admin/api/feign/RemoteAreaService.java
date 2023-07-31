package com.crcm.admin.api.feign;

import com.crcm.admin.api.dto.res.AreaDTO;
import com.crcm.core.constant.AuthConstants;
import com.crcm.core.constant.ServiceNameConstants;
import com.crcm.core.response.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName RemoteUserService
 * @Description 远程调用用户服务
 * @Copyright Copyright(c) 2020
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/2/26
 **/
@FeignClient(contextId = "remoteAreaService", value = ServiceNameConstants.ADMIN_SERVICE)
public interface RemoteAreaService {

    /**
     * 通过ID查询区域信息
     *
     * @param id
     * @return R
     */
    @GetMapping(value = "/area/get/{id}")
    RestResult<AreaDTO> getInfo(@PathVariable("id")  String id, @RequestHeader(AuthConstants.FROM) String from);

    /**
     * 通过区域代码查询区域信息
     *
     * @param code 区域代码
     * @return R
     */
    @GetMapping(value = "/area/getInfoByCode")
    RestResult<AreaDTO> getInfoByCode(@RequestParam("code")  String code, @RequestHeader(AuthConstants.FROM) String from);


    @GetMapping(value = "/area/findAreaByType/{type}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询当前类型的区域")
    public RestResult<List<AreaDTO>> findAreaByType(@PathVariable("type") String type, @RequestHeader(AuthConstants.FROM) String from);

    /**
     * 查询区域列表
     *
     * @param id
     * @return R
     */
    @GetMapping(value = "/area/list")
    RestResult<List<AreaDTO>> getList(@RequestParam("id") String id, @RequestHeader(AuthConstants.FROM) String from);

    @GetMapping("/area/getSubsetByPid")
    public RestResult<List<AreaDTO>> getSubsetByPid(@RequestParam("pid")String pid,@RequestHeader(AuthConstants.FROM) String from);
    @GetMapping("/area/getAreaListById")
    public RestResult<List<AreaDTO>> getAreaListById(@RequestParam("id")String id, @RequestHeader(AuthConstants.FROM) String from);

    /**
     * 查询所有村级区域数据，按照管区CODE的ASC顺序来ASC
     *
     * @param from 内部标识
     * @param regionCode 父code
     * @return 所有村级区域列表
     */
    @GetMapping("/area/getVillageAsc")
    RestResult<List<AreaDTO>> getVillageAsc(@RequestParam(value = "regionCode",required = false) String regionCode, @RequestHeader(AuthConstants.FROM) String from);

    /**
     * 查询所有管区区域数据，按照管区CODE的ASC顺序
     *
     * @param from 内部标识
     * @param regionCode 管区code
     * @return 所有村级区域列表
     */
    @GetMapping("/area/getRegionAsc")
    RestResult<List<AreaDTO>> getRegionAsc(@RequestParam(value = "regionCode",required = false) String regionCode, @RequestHeader(AuthConstants.FROM) String from);
}
