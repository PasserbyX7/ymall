package com.yinn.ymall.product.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.common.dto.SkuEsDTO;
import com.yinn.ymall.common.dto.SkuHasStockDTO;
import com.yinn.ymall.common.exception.ProductDownException;
import com.yinn.ymall.common.exception.ProductUpException;
import com.yinn.ymall.common.exception.RPCException;
import com.yinn.ymall.product.constant.PublishStatusEnum;
import com.yinn.ymall.product.constant.VerifyStatusEnum;
import com.yinn.ymall.product.dao.SpuDao;
import com.yinn.ymall.product.dto.SpuDTO;
import com.yinn.ymall.product.dto.SpuPageQueryDTO;
import com.yinn.ymall.product.entity.Sku;
import com.yinn.ymall.product.entity.Spu;
import com.yinn.ymall.product.entity.SpuAttrValue;
import com.yinn.ymall.product.feign.SearchFeignService;
import com.yinn.ymall.product.feign.WareFeignService;
import com.yinn.ymall.product.service.BrandService;
import com.yinn.ymall.product.service.CategoryService;
import com.yinn.ymall.product.service.SkuService;
import com.yinn.ymall.product.service.SpuAttrValueService;
import com.yinn.ymall.product.service.SpuService;

@Slf4j
@Service
public class SpuServiceImpl extends ServiceImpl<SpuDao, Spu> implements SpuService {

    @Autowired
    private SpuAttrValueService spuAttrValueService;

    @Autowired
    private SkuService skuService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WareFeignService wareFeignService;

    @Autowired
    private SearchFeignService searchFeignService;

    @Transactional
    @Override
    public void save(SpuDTO spuDTO) {
        // 保存spu基本信息
        // @formatter:off
        var spu = spuDTO.convertToSpu()
                                    .setPublishStatus(PublishStatusEnum.CREATE)
                                    .setIsRecommend(false)
                                    .setIsDelete(false)
                                    .setVerifyStatus(VerifyStatusEnum.CREATE);
        // @formatter:on
        save(spu);
        Long spuId = spu.getId();
        // 保存spuAttr信息
        spuAttrValueService.saveBatch(spuDTO.getSpuAttrs(), spuId);
        // 保存sku信息
        spuDTO.getSkuDTOs().forEach(sku -> skuService.save(sku, spu));
    }

    @Override
    public Page<Spu> queryPage(SpuPageQueryDTO spuQueryDTO) {
        var w = Wrappers.<Spu>lambdaQuery();
        w.eq(spuQueryDTO.getCategoryId() != null, Spu::getCategoryId, spuQueryDTO.getCategoryId());
        w.eq(spuQueryDTO.getBrandId() != null, Spu::getBrandId, spuQueryDTO.getBrandId());
        w.eq(spuQueryDTO.getPublishStatus() != null, Spu::getPublishStatus, spuQueryDTO.getPublishStatus());
        String key = spuQueryDTO.getKey();
        if (StringUtils.isNoneBlank(key))
            w.and(e -> e.eq(Spu::getId, key).or().like(Spu::getName, key));
        return page(spuQueryDTO.page(), w);
    }

    /**
     * 远程调用检索服务保存sku信息后将spu状态更新为上架
     *
     * @param spuId spuId
     * @Date: 2020-05-25 17:32:15
     */
    @Transactional
    @Override
    public void up(Long spuId) {
        var skus = skuService.listBySpuId(spuId);
        // 列出spu对应所有可检索规格属性
        // @formatter:off
        var attrEsDTOs=spuAttrValueService
                                            .listSearchAttrBySpuId(spuId)
                                            .stream()
                                            .map(SpuAttrValue::convertToAttrEsDTO)
                                            .collect(Collectors.toList());
        // @formatter:on
        // 远程调用库存服务查询所有sku库存信息
        var skuIds = skus.stream().map(Sku::getId).collect(Collectors.toList());
        var skuStockMap = listSkuHasStock(skuIds);
        // sku->skuEsDTO
        // @formatter:off
        var skuEsDTOs = skus.stream()
                .map(this::skuConvertToSkuEsDTO)// 设置基本属性
                .map(e -> e.setAttrs(attrEsDTOs))// 设置属性列表
                .map(e -> e.setHotScore(0L))// 设置热度
                .map(e -> e.setHasStock(Optional.ofNullable(skuStockMap.get(e.getSkuId())).orElse(false)))// 设置库存
                .collect(Collectors.toList());
        // @formatter:on
        // 远程调用检索服务保存sku信息
        // 如果检索保存成功，则将更新spu信息为上架
        if (!searchFeignService.productUp(skuEsDTOs).isOk())
            throw new ProductUpException();
        spuUp(spuId);
    }

    @Override
    public void down(Long spuId) {
        if(!searchFeignService.productDown(spuId).isOk())
            throw new ProductDownException();
        // @formatter:off
        update(
            Wrappers.<Spu>lambdaUpdate()
                .eq(Spu::getId, spuId)
                .set(Spu::getPublishStatus,PublishStatusEnum.DOWN)
            );
        // @formatter:on
    }

    @Override
    public Spu getBySkuId(Long skuId) {
        var spuId = skuService.getById(skuId).getSpuId();
        return getById(spuId);
    }

    /**
     * 将spu状态设置为上架
     *
     * @param spuId spuId
     * @Date: 2020-05-09 01:13:26
     */
    private void spuUp(Long spuId) {
        // @formatter:off
        update(
            Wrappers.<Spu>lambdaUpdate()
                .eq(Spu::getId, spuId)
                .set(Spu::getPublishStatus,PublishStatusEnum.UP)
            );
        // @formatter:on
    }

    private SkuEsDTO skuConvertToSkuEsDTO(Sku sku) {
        var skuEsDTO = new SkuEsDTO();
        BeanUtils.copyProperties(sku, skuEsDTO);// 属性对拷
        skuEsDTO.setSkuId(sku.getId());
        skuEsDTO.setSkuImg(sku.getDefaultImg());// 设置默认图片
        var brand = brandService.getById(sku.getBrandId());
        if (brand != null) {
            skuEsDTO.setBrandName(brand.getName());// 设置品牌名
            skuEsDTO.setBrandImg(brand.getLogo());// 设置品牌图片
        }
        var category = categoryService.getById(sku.getCategoryId());
        skuEsDTO.setCategoryName(category.getName());// 设置分类名
        return skuEsDTO;
    }

    private Map<Long, Boolean> listSkuHasStock(List<Long> skuIds) {
        // 远程查询所有sku库存信息
        Map<Long, Boolean> skuStockMap = new HashMap<>();
        try {
            // @formatter:off
            skuStockMap=wareFeignService
                                        .listSkuHasStock(skuIds)
                                        .getData()
                                        .stream()
                                        .collect(Collectors.toMap(SkuHasStockDTO::getSkuId, SkuHasStockDTO::getIsHasStock));
            // @formatter:on
        } catch (Exception e) {
            log.error("库存查询异常：{}", e.getMessage());
            throw new RPCException(e.getMessage());
        }
        return skuStockMap;
    }

}