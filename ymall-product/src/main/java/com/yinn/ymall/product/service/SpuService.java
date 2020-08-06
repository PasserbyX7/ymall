package com.yinn.ymall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.product.dto.SpuDTO;
import com.yinn.ymall.product.dto.SpuPageQueryDTO;
import com.yinn.ymall.product.entity.Spu;

/**
 * 商品spu表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:50
 */
public interface SpuService extends IService<Spu> {

    void save(SpuDTO spuDTO);

    Page<Spu> queryPage(SpuPageQueryDTO spuQueryDTO);

    /**
     * 商品上架
     *
     * @param spuId spuId
     * @Date: 2020-05-08 21:13:06
     */
    void up(Long spuId);

	Spu getBySkuId(Long skuId);

	void down(Long spuId);
}
