package com.yinn.ymall.search.controller;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.dto.SkuEsDTO;
import com.yinn.ymall.search.dto.SearchParamDTO;
import com.yinn.ymall.search.dto.SearchResultDTO;
import com.yinn.ymall.search.service.ProductService;
import com.yinn.ymall.search.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "搜索服务接口")
@RestController
@RequestMapping("/api/search/v1")
public class EsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SearchService searchService;

    @ApiOperation("商品上架")
    @PostMapping("/products/actions/up")
    public R<Void> productUp(@RequestBody List<SkuEsDTO> skuEsDTOs){
        productService.productUp(skuEsDTOs);
        return R.ok();
    }

    @ApiOperation("商品检索")
    @GetMapping("/products/actions/search")
    public R<SearchResultDTO> productUp(SearchParamDTO searchParamDTO){
        return R.ok(searchService.search(searchParamDTO));
    }

}