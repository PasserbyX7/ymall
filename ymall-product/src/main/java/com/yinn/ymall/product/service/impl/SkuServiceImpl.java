package com.yinn.ymall.product.service.impl;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.product.dao.SkuDao;
import com.yinn.ymall.product.dto.ItemDTO;
import com.yinn.ymall.product.dto.SkuDTO;
import com.yinn.ymall.product.dto.SkuPageQueryDTO;
import com.yinn.ymall.product.dto.SkuStockDTO;
import com.yinn.ymall.product.entity.Sku;
import com.yinn.ymall.product.entity.SkuImage;
import com.yinn.ymall.product.entity.Spu;
import com.yinn.ymall.product.feign.WareFeignService;
import com.yinn.ymall.product.service.AttrGroupService;
import com.yinn.ymall.product.service.SkuAttrValueService;
import com.yinn.ymall.product.service.SkuImageService;
import com.yinn.ymall.product.service.SkuService;
import com.yinn.ymall.product.service.SpuService;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuDao, Sku> implements SkuService {

    @Autowired
    private SkuImageService skuImageService;

    @Autowired
    private SkuAttrValueService skuAttrValueService;

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private SpuService spuService;

    @Autowired
    private WareFeignService wareFeignService;

    @Override
    public void save(SkuDTO skuDTO, Spu spu) {
        // 保存基本属性
        var sku = saveSku(skuDTO, spu);
        Long skuId = sku.getId();
        // 保存图片
        saveImg(skuDTO, skuId);
        // 保存销售属性
        saveSkuAttrs(skuDTO, skuId);
        // 远程同步库存信息
        wareFeignService.saveSkuStock(new SkuStockDTO(skuId,skuDTO.getStock(),0));
    }

    @Override
    public Page<Sku> queryPage(SkuPageQueryDTO skuQueryDTO) {
        var w = Wrappers.<Sku>lambdaQuery();
        if (skuQueryDTO.getCategoryId() != null)
            w.eq(Sku::getCategoryId, skuQueryDTO.getCategoryId());
        if (skuQueryDTO.getBrandId() != null)
            w.eq(Sku::getBrandId, skuQueryDTO.getBrandId());
        if (skuQueryDTO.getMax() != null)
            w.ge(Sku::getPrice, skuQueryDTO.getMax());
        if (skuQueryDTO.getMin() != null)
            w.le(Sku::getPrice, skuQueryDTO.getMin());
        String key = skuQueryDTO.getKey();
        if (StringUtils.isNotBlank(key))
            w.and(e -> e.eq(Sku::getId, key).or().like(Sku::getName, key));
        return page(skuQueryDTO.page(),w);
    }

    @Override
    public BigDecimal getPriceById(Long skuId) {
        // @formatter:off
        return Optional.of(getById(skuId))
                                .map(Sku::getPrice)
                                .orElse(null);
        // @formatter:on
    }

    @Override
    public List<Sku> listBySpuId(Long spuId) {
        return list(Wrappers.<Sku>lambdaQuery().eq(Sku::getSpuId, spuId));
    }

    @Override
    public ItemDTO getItem(Long skuId) {
        var skuItem = new ItemDTO();
        // 获取当前sku基本信息
        var sku = getById(skuId);
        skuItem.setSku(sku);
        // 获取当前sku图片信息
        skuItem.setImages(skuImageService.getBySkuId(skuId));
        // 获取spu介绍信息
        var spuId = sku.getSpuId();
        var categoryId = sku.getCategoryId();
        var imgDesc = spuService.getById(spuId).getImgDesc();
        skuItem.setImgDesc(imgDesc);
        // 获取spu规格参数信息
        skuItem.setSpuAttrGroupDTOs(attrGroupService.listSpuAttrGroupDTOs(spuId, categoryId));
        // 获取spu销售属性信息
        skuItem.setSkuAttrDTOs(skuAttrValueService.listSkuAttrDTOBySpuId(spuId));
        return skuItem;
    }

    private void setSkuDefaultImg(SkuDTO skuDTO, Sku sku) {
        // @formatter:off
        skuDTO.getImages()// 设置默认图片
                            .stream()
                            .filter(e -> e.getIsDefault())
                            .findFirst()
                            .map(SkuImage::getUrl)
                            .ifPresent(sku::setDefaultImg);
        // @formatter:on
    }

    private void saveSkuAttrs(SkuDTO skuDTO, Long skuId) {
        var skuAttrs = skuDTO.getSkuAttrs().stream().map(e -> e.setSkuId(skuId)).collect(Collectors.toList());
        skuAttrValueService.saveBatch(skuAttrs);
    }

    private void saveImg(SkuDTO skuDTO, Long skuId) {
        // @formatter:off
        var skuImages = skuDTO
                                        .getImages()
                                        .stream()
                                        .map(e -> e.setSkuId(skuId))
                                        // 没有图片路径的无需保存
                                        .filter(e -> StringUtils.isNotBlank(e.getUrl()))
                                        .collect(Collectors.toList());
        // @formatter:on
        skuImageService.saveBatch(skuImages);
    }

    private Sku saveSku(SkuDTO skuDTO, Spu spu) {
        // @formatter:off
        var sku = skuDTO.convertToSku()
                                    .setCategoryId(spu.getCategoryId())
                                    .setBrandId(spu.getBrandId())
                                    .setSpuId(spu.getId())
                                    .setSale(0);
        // @formatter:on
        setSkuDefaultImg(skuDTO, sku);
        save(sku);
        return sku;
    }
}