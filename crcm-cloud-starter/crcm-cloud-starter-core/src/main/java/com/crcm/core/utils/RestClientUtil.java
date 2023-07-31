package com.crcm.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: RestClientUtil
 * @Description HTTP访问封装类，将RestTemplate封装为静态方法
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author Diaoy
 * @Date 2019/9/17
 **/
@Slf4j
@Component
public class RestClientUtil {

    private static RestTemplate httpClientTemplate;

    @Resource
    public void setHttpClientTemplate(RestTemplate httpClientTemplate) {
        RestClientUtil.httpClientTemplate = httpClientTemplate;
    }

    /**
     * Get请求，参数放在URL中
     * 例：URL=localhost:8080/api=1111&&name=小明
     * @param url 资源路径
     * @param responseType 返回类型
     * @return
     * @throws RestClientException
     */
    public static <T> T doGet(String url, Class<T> responseType)  throws RestClientException {
        return httpClientTemplate.getForObject(url, responseType);
    }

    /**
     * Get请求，参数放在Map中
     * @param url 资源路径
     * @param responseType 返回类型
     * @param params 参数
     * @return
     * @throws RestClientException
     */
    public static <T> T doGet(String url, Map<String, ?> params, Class<T> responseType)  throws RestClientException {
        if (Objects.isNull(params)) {
            params = new HashMap<>();
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        params.entrySet().stream().forEach(o -> builder.queryParam(o.getKey(),o.getValue()));
        String newUrl = builder.build().encode().toString();
        return httpClientTemplate.getForObject(newUrl, responseType, params);
    }

    /**
     * Get请求，参数放在URL中，根据传入的值自动填充
     * 例：URL=localhost:8080/api?id={?}&&name={?}, 传值 11111, 小明
     * @param url 资源路径
     * @param responseType 返回类型
     * @param params 参数
     * @return
     * @throws RestClientException
     */
    public static <T> T doGet(String url, Class<T> responseType, Object... params)  throws RestClientException {
        return httpClientTemplate.getForObject(url, responseType, params);
    }


    /**
     * Post请求，参数传递方式为JSON，服务端通过@RequestBody 接收参数
     * @param url 资源路径
     * @param params 参数
     * @param responseType 返回类型
     * @param <T> 泛型
     * @return
     */
    public static <T> T doPost(String url, Map<String, ?> params, Class<T> responseType) throws RestClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(params,headers);
        return httpClientTemplate.postForObject(url, httpEntity, responseType);
    }

    /**
     * Post请求，自定义请求头,服务端通过@RequestBody 接收参数
     * @param url 资源路径
     * @param headers 自定义请求头
     * @param params 参数
     * @param responseType 返回类型
     * @param <T> 泛型
     * @return
     * @throws RestClientException
     */
    public static <T> T doPost(String url, HttpHeaders headers, Map<String, ?> params, Class<T> responseType) throws RestClientException {
        HttpEntity httpEntity = new HttpEntity(params,headers);
        return httpClientTemplate.postForObject(url, httpEntity, responseType);
    }

    /**
     * Post请求，表单方式提交，服务端通过@RequestParam，@RequestHeader接收参数
     * @param url 资源路径
     * @param params 参数
     * @param responseType 返回类型
     * @param <T> 泛型
     * @return
     * @throws RestClientException
     */
    public static <T> T doPost(String url, MultiValueMap<String, Object> params, Class<T> responseType) throws RestClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity httpEntity = new HttpEntity(params,headers);
        return httpClientTemplate.postForObject(url, httpEntity, responseType);
    }


}
