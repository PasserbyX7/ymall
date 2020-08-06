package com.yinn.ymall.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.yinn.ymall.common.dto.SkuEsDTO;
import com.yinn.ymall.search.config.EsConfiguration;
import com.yinn.ymall.search.constant.EsConstant;
import com.yinn.ymall.search.dto.SearchParamDTO;
import com.yinn.ymall.search.dto.SearchResultDTO;
import com.yinn.ymall.search.exception.EsSearchFailException;
import com.yinn.ymall.search.service.SearchService;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public SearchResultDTO search(SearchParamDTO searchParamDTO) {
        // 构造查询条件
        var searchRequest = buildSearchRequest(searchParamDTO);
        // 进行查询
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, EsConfiguration.COMMON_OPTIONS);
        } catch (IOException e) {
            throw new EsSearchFailException();
        }
        // 封装返回数据
        return buildSearchResultDTO(searchResponse, searchParamDTO);
    }

    /**
     * 通过ES检索结果构建SearchResultDTO
     *
     * @param response ES检索结果
     * @param param    检索条件DTO
     * @return SearchResultDTO
     * @Date: 2020-05-09 07:18:31
     */
    private SearchResultDTO buildSearchResultDTO(SearchResponse response, SearchParamDTO param) {
        var res = new SearchResultDTO();
        var hits = response.getHits();
        // 保存分页信息
        var pageDTO = new SearchResultDTO.PageDTO().setTotal(hits.getTotalHits().value)
                .setSize(param.getSize()).setCurrent(param.getCurrent());
        res.setPage(pageDTO);
        // 保存商品信息
        List<SkuEsDTO> products = new ArrayList<>();
        for (var hit : hits.getHits()) {
            var skuEs = JSON.parseObject(hit.getSourceAsString(), SkuEsDTO.class);
            if (StringUtils.hasText(param.getKeyword())) {// 设置高亮
                var title = hit.getHighlightFields().get("title").getFragments()[0].toString();
                skuEs.setTitle(title);
            }
            products.add(skuEs);
        }
        res.setProducts(products);
        // 获取聚合信息
        ParsedLongTerms categoryAgg = response.getAggregations().get("category_agg");// 设置分类信息
        var categories = categoryAgg.getBuckets().stream().map(bucket -> {
            var category = new SearchResultDTO.CategoryDTO();
            category.setCategoryId(bucket.getKeyAsNumber().longValue());
            ParsedStringTerms categoryNameAgg = bucket.getAggregations().get("category_name_agg");
            var categoryName = categoryNameAgg.getBuckets().get(0).getKeyAsString();
            category.setCategoryName(categoryName);
            return category;
        }).collect(Collectors.toList());
        res.setCategories(categories);

        ParsedLongTerms brandAgg = response.getAggregations().get("brand_agg");// 设置品牌信息
        var brands = brandAgg.getBuckets().stream().map(bucket -> {
            var brand = new SearchResultDTO.BrandDTO();
            brand.setBrandId(bucket.getKeyAsNumber().longValue());
            ParsedStringTerms brandImgAgg = bucket.getAggregations().get("brand_img_agg");
            var brandImg = brandImgAgg.getBuckets().get(0).getKeyAsString();
            brand.setBrandImg(brandImg);
            ParsedStringTerms brandNameAgg = bucket.getAggregations().get("brand_name_agg");
            var brandName = brandNameAgg.getBuckets().get(0).getKeyAsString();
            brand.setBrandName(brandName);
            return brand;
        }).collect(Collectors.toList());
        res.setBrands(brands);

        ParsedNested attrAgg = response.getAggregations().get("attr_agg");// 设置属性信息
        ParsedLongTerms attrIdAgg = attrAgg.getAggregations().get("attr_id_agg");
        var attrs = attrIdAgg.getBuckets().stream().map(bucket -> {
            var attr = new SearchResultDTO.AttrDTO();
            attr.setAttrId(bucket.getKeyAsNumber().longValue());

            ParsedStringTerms attrNameAgg = bucket.getAggregations().get("attr_name_agg");
            var attrName = attrNameAgg.getBuckets().get(0).getKeyAsString();
            attr.setAttrName(attrName);
            ParsedStringTerms attrValueAgg = bucket.getAggregations().get("attr_value_agg");
            var attrValues = attrValueAgg.getBuckets().stream().map(Terms.Bucket::getKeyAsString)
                    .collect(Collectors.toList());
            attr.setAttrValues(attrValues);
            return attr;
        }).collect(Collectors.toList());
        res.setAttrs(attrs);
        log.debug("商品检索数据：[{}]", res);
        return res;
    }

    /**
     * 构建检索用DSL语句
     *
     * @param param 检索条件DTO
     * @return SearchRequest
     * @Date: 2020-05-09 06:24:21
     */
    private SearchRequest buildSearchRequest(SearchParamDTO param) {
        var sourceBuilder = new SearchSourceBuilder();
        // 构建查询DSL
        queryDSLBuilder(sourceBuilder, param);
        // 构建排序DSL
        sortDSLBuilder(sourceBuilder, param);
        // 构建pageDSL
        pageDSLBuilder(sourceBuilder, param);
        // 构建highlightDSL
        highlightDSLBuilder(sourceBuilder, param);
        // 构建aggsDSL
        aggsDSLBuilder(sourceBuilder, param);
        log.debug("DSL查询语句：[{}]", sourceBuilder.toString());
        return new SearchRequest(new String[] { EsConstant.PRODUCT_INDEX }, sourceBuilder);
    }

    private void aggsDSLBuilder(SearchSourceBuilder sourceBuilder, SearchParamDTO param) {
        // brand_agg
        var brandAgg = AggregationBuilders.terms("brand_agg").field("brandId").size(20);
        brandAgg.subAggregation(AggregationBuilders.terms("brand_name_agg").field("brandName").size(1));
        brandAgg.subAggregation(AggregationBuilders.terms("brand_img_agg").field("brandImg").size(1));
        sourceBuilder.aggregation(brandAgg);
        // category_agg
        var categoryAgg = AggregationBuilders.terms("category_agg").field("categoryId").size(20);
        categoryAgg.subAggregation(AggregationBuilders.terms("category_name_agg").field("categoryName").size(1));
        sourceBuilder.aggregation(categoryAgg);
        // attr_agg
        var nestedAttrBuilder = AggregationBuilders.nested("attr_agg", "attrs");
        var attrIdAgg = AggregationBuilders.terms("attr_id_agg").field("attrs.attrId").size(20);
        attrIdAgg.subAggregation(AggregationBuilders.terms("attr_name_agg").field("attrs.attrName").size(1));
        attrIdAgg.subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue").size(20));
        nestedAttrBuilder.subAggregation(attrIdAgg);
        sourceBuilder.aggregation(nestedAttrBuilder);
    }

    private void highlightDSLBuilder(SearchSourceBuilder sourceBuilder, SearchParamDTO param) {
        if (StringUtils.hasText(param.getKeyword())) {
            var highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("title");
            highlightBuilder.preTags("<b style='color:red'>");
            highlightBuilder.postTags("</b>");
            sourceBuilder.highlighter(highlightBuilder);
        }
    }

    private void pageDSLBuilder(SearchSourceBuilder sourceBuilder, SearchParamDTO param) {
        sourceBuilder.size(param.getSize());
        sourceBuilder.from((param.getCurrent() - 1) * param.getSize());
    }

    private void sortDSLBuilder(SearchSourceBuilder sourceBuilder, SearchParamDTO param) {
        if (StringUtils.hasText(param.getSort())) {
            // 字符串格式：field-asc/desc
            String field = param.getSort().split("-")[0];
            var order = param.getSort().split("-")[1].equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC;
            sourceBuilder.sort(field, order);
        }
    }

    private void queryDSLBuilder(SearchSourceBuilder sourceBuilder, SearchParamDTO param) {
        var booleanBuilder = QueryBuilders.boolQuery();
        // 模糊匹配
        if (StringUtils.hasText(param.getKeyword()))
            booleanBuilder.must(QueryBuilders.matchQuery("title", param.getKeyword()));
        // 过滤
        if (param.getCategoryId() != null)// 分类过滤
            booleanBuilder.filter(QueryBuilders.termQuery("categoryId", param.getCategoryId()));
        if (!CollectionUtils.isEmpty(param.getBrandId()))// 品牌过滤
            booleanBuilder.filter(QueryBuilders.termsQuery("brandId", param.getBrandId()));
        if (param.getHasStock() != null)// 库存过滤
            booleanBuilder.filter(QueryBuilders.termQuery("hasStock", param.getHasStock()));
        if (!CollectionUtils.isEmpty(param.getAttrs())) {// 属性过滤
            // 字符串格式：id:value1-value2
            for (var attrStr : param.getAttrs()) {
                var attrId = attrStr.split(":")[0];
                var attrValues = attrStr.split(":")[1].split("-");
                var nestedBooleanBuilder = QueryBuilders.boolQuery();
                nestedBooleanBuilder.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                nestedBooleanBuilder.must(QueryBuilders.termsQuery("attrs.attrValue", attrValues));
                var nestedBuilder = QueryBuilders.nestedQuery("attrs", nestedBooleanBuilder, ScoreMode.None);
                booleanBuilder.filter(nestedBuilder);
            }
        }
        if (StringUtils.hasText(param.getPrice())) {// 价格区间过滤
            // 字符串格式：m-n
            // 如果m=-1，则代表无下限；如果n=-1，则代表无上限
            var rangeQuery = QueryBuilders.rangeQuery("price");
            var numTuple = param.getPrice().split("-");
            rangeQuery.gte(Integer.parseInt(numTuple[0]));// 大于区间
            if (numTuple.length == 2)// 小于区间
                rangeQuery.lte(Integer.parseInt(numTuple[1]));
            booleanBuilder.filter(rangeQuery);
        }
        sourceBuilder.query(booleanBuilder);
    }
}