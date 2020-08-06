// package com.yinn.ymall.search;

// import java.io.IOException;

// import com.yinn.ymall.search.config.EsConfiguration;
// import com.yinn.ymall.search.constant.EsConstant;

// import org.apache.http.HttpHost;
// import org.elasticsearch.action.bulk.BulkRequest;
// import org.elasticsearch.action.bulk.BulkResponse;
// import org.elasticsearch.action.index.IndexRequest;
// import org.elasticsearch.client.RestClient;
// import org.elasticsearch.client.RestHighLevelClient;
// import org.elasticsearch.common.xcontent.XContentType;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// // @SpringBootTest
// public class EsTest {
//     @Autowired
//     RestHighLevelClient client;

//     static String json="""
//     {"attrs":[{"attrId":26,"attrName":"CPU核心数","attrValue":"双核"},{"attrId":27,"attrName":"机身颜色","attrValue":"黑色"},{"attrId":29,"attrName":"触摸屏类型","attrValue":"触摸屏"}],"brandId":1,"brandImg":"","spuId":13,"title":"商品名称（华为手机） 蓝色 4G"}
//     """;

//     @Test
//     void test() throws IOException {
//         var client=new RestHighLevelClient(RestClient.builder(new HttpHost("yinn.live", 9200)));
//         var bulkRequest=new BulkRequest();
//         bulkRequest.add(new IndexRequest(EsConstant.PRODUCT_INDEX).id("1").source(json,XContentType.JSON));
//         BulkResponse res = client.bulk(bulkRequest, EsConfiguration.COMMON_OPTIONS);
//         System.out.println(res);
//     }

// }