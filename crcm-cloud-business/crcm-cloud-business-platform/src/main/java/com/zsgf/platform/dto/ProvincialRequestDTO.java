package com.zsgf.platform.dto;

import com.zsgf.platform.enums.ProvincialPlatformInterfaceEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName ProvincialRequestDTO
 * @Description 省平台数据请求DTO
 * @Author GZL
 * @Date 2023/3/17 16:22
 * @Version 1.0
 **/
@Getter
@Setter
@AllArgsConstructor
public class ProvincialRequestDTO<T> {

    private ProvincialDTO queryDTO;

    private  Class<T> clazz;

    private List<T> resultList;

    private ProvincialPlatformInterfaceEnum interfaceEnum;
}
