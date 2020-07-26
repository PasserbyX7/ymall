package com.yinn.ymall.search.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.yinn.ymall.common.dto.SkuEsDTO;
import com.yinn.ymall.search.config.EsConfiguration;
import com.yinn.ymall.search.constant.EsConstant;
import com.yinn.ymall.search.exception.ProductUpException;
import com.yinn.ymall.search.service.ProductService;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private RestHighLevelClient client;

    @Override
    public void productUp(List<SkuEsDTO> SkuEsDTOs){
        var bulkRequest=new BulkRequest();
        for (var skuEsDTO : SkuEsDTOs) {
            var indexRequest=new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(skuEsDTO.getSkuId().toString());
            indexRequest.source(JSON.toJSONString(skuEsDTO),XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse res=null;
        try {
            res = client.bulk(bulkRequest, EsConfiguration.COMMON_OPTIONS);
        } catch (IOException e) {
            throw new ProductUpException();
        }
        var errorIds=Arrays.stream(res.getItems()).map(BulkItemResponse::getId).collect(Collectors.toList());
        log.info("商品上架成功：{}", errorIds);
    }
}