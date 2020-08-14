package com.yinn.ymall.coupon.service.impl;

import com.yinn.ymall.coupon.entity.SeckillSkuRelation;
import com.yinn.ymall.coupon.dao.SeckillSkuRelationDao;
import com.yinn.ymall.coupon.dto.SeckillSkuRelationPageQueryDTO;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.coupon.service.SeckillSkuRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * seckill-session与sku的关联表 服务实现类
 * </p>
 *
 * @author Passerby
 * @since 2020-08-14
 */
@Service
@Transactional
public class SeckillSkuRelationServiceImpl extends ServiceImpl<SeckillSkuRelationDao, SeckillSkuRelation>
        implements SeckillSkuRelationService {

    @Override
    public Page<SeckillSkuRelation> queryPage(SeckillSkuRelationPageQueryDTO query) {
        var w = Wrappers.<SeckillSkuRelation>lambdaQuery();
        w.eq(query.getSessionId() != null, SeckillSkuRelation::getSessionId, query.getSessionId());
        return (Page<SeckillSkuRelation>) page(query.page(), w);
    }

	@Override
	public List<SeckillSkuRelation> listBySessionId(Long sessionId) {
        return list(Wrappers.<SeckillSkuRelation>lambdaQuery().eq(SeckillSkuRelation::getSessionId,sessionId));
	}

}
