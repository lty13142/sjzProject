package com.crcm.cloud.start.es.config;

import com.crcm.cloud.start.es.properties.RestClientPoolProperties;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * @ClassName RestAutoConfigure
 * @Description es自动配置
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/12/1
 **/
@EnableConfigurationProperties(RestClientPoolProperties.class)
public class RestAutoConfigure {
    @Bean
    public RestClientBuilderCustomizer restClientBuilderCustomizer(RestClientPoolProperties poolProperties
            , ElasticsearchRestClientProperties restProperties) {
        return (builder) -> {
            setRequestConfig(builder, poolProperties);

            setHttpClientConfig(builder, poolProperties, restProperties);
        };
    }

    /**
     * 异步httpclient连接延时配置
     */
    private void setRequestConfig(RestClientBuilder builder, RestClientPoolProperties poolProperties){
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(poolProperties.getConnectTimeOut())
                    .setSocketTimeout(poolProperties.getSocketTimeOut())
                    .setConnectionRequestTimeout(poolProperties.getConnectionRequestTimeOut());
            return requestConfigBuilder;
        });
    }

    /**
     * 异步httpclient连接数配置
     */
    private void setHttpClientConfig(RestClientBuilder builder, RestClientPoolProperties poolProperties, ElasticsearchRestClientProperties restProperties){
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(poolProperties.getMaxConnectNum())
                    .setMaxConnPerRoute(poolProperties.getMaxConnectPerRoute());

            PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
            // TODO es兼容性问题
//            map.from(restProperties::getUsername).to(username -> {
//                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//                credentialsProvider.setCredentials(AuthScope.ANY,
//                        new UsernamePasswordCredentials(username, restProperties.getPassword()));
//                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//            });
            return httpClientBuilder;
        });
    }

    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchRestTemplate elasticsearchRestTemplate(RestHighLevelClient restHighLevelClient) {
        return new ElasticsearchRestTemplate(restHighLevelClient);
    }
}