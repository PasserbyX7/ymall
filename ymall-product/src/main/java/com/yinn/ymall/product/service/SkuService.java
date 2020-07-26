package com.yinn.ymall.product.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.product.dto.ItemDTO;
import com.yinn.ymall.product.dto.SkuDTO;
import com.yinn.ymall.product.dto.SkuQueryDTO;
import com.yinn.ymall.product.entity.Sku;
import com.yinn.ymall.product.entity.Spu;

/**
 * 商品sku表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
public interface SkuService extends IService<Sku> {
    void save(SkuDTO skuInputDTO, Spu spu);

    Page<Sku> queryPage(SkuQueryDTO skuQueryDTO);

    List<Sku> listBySpuId(Long spuId);

    ItemDTO getItem(Long skuId);

    /**
     * 返回sku对应价格，如果sku不存在则返回null
     *
     * @param skuId skuId
     * @Date: 2020-05-20 13:27:13
     */
    BigDecimal getPriceById(Long skuId);
}
