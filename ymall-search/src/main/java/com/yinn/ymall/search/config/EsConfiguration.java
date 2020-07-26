package com.yinn.ymall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfiguration {

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
    @Bean
    public RestHighLevelClient EsRestClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.206.130", 9200)));
    }

}