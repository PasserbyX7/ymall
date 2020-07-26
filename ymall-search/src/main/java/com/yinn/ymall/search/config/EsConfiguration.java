package com.yinn.ymall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@RefreshScope
@Configuration
public class EsConfiguration {

    @Value("${spring.data.elasticsearch.cluster-node}")
    private String host="139.198.188.61";
    /**
     * ES通用设置项
     */
    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }

    /**
     * 给容器注入RestHighLevelClient
     *
     * @Date: 2020-05-08 19:30:54
     */
    @Primary
    @Bean
    public RestHighLevelClient EsRestClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(host, 9200)));
    }

}