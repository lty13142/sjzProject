package com.zsgf.platform.utils.provincial;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * @ClassName RequestResult
 * @Description 响应结果
 * @Author GZL
 * @Date 2023/2/10 10:36
 * @Version 1.0
 **/
@Data
public class RequestResult {

    private boolean success;
    private String error;
    private String errorCode;
    private int total;
    private int pageNum;
    private int pageSize;
    private int pages;
    private JSONArray obj;
    private JSONArray zdxxlist;
}
