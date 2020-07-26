package com.yinn.ymall.search;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EsTest {
    @Autowired
    private RestHighLevelClient client;
    @Test
    void test(){
        System.out.println(client);
    }
}