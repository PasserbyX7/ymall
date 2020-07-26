package com.yinn.ymall.ware.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.common.dto.SkuHasStockDTO;
import com.yinn.ymall.ware.dto.SkuLockDTO;
import com.yinn.ymall.ware.entity.SkuStock;

/**
 * 商品库存表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-08 01:19:23
 */
public interface SkuStockService extends IService<SkuStock> {

    List<SkuHasStockDTO> listSkuHasStock(List<Long> skuIds);

    /**
     * 为某订单锁定库存
     *
     * @param skuLockDTOs skuLockDTOs
     * @Date: 2020-05-21 22:29:39
     */
    void stockLock(SkuLockDTO skuLockDTO);

    SkuStock getBySkuId(Long skuId);

    /**
     * 为某订单解锁库存
     *
     * @param orderSn 订单号
     * @Date: 2020-05-22 22:05:37
     */
    void stockUnlock(String orderSn);
    /**
     * 为某订单扣减库存
     *
     * @param orderSn 订单号
     * @Date: 2020-07-26 03:55:16
     */
    void stockDeduct(String orderSn);

	Boolean getSkuHasStock(Long skuId);
}
