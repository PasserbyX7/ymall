package com.yinn.ymall.coupon.service;

import com.yinn.ymall.coupon.dto.SeckillSkuRelationPageQueryDTO;
import com.yinn.ymall.coupon.entity.SeckillSkuRelation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * seckill-session与sku的关联表 服务类
 * </p>
 *
 * @author Passerby
 * @since 2020-08-14
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelation> {

	Page<SeckillSkuRelation> queryPage(SeckillSkuRelationPageQueryDTO seckillSkuRelationPageQueryDTO);

}
