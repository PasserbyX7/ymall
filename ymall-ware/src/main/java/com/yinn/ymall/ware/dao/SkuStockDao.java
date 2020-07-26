package com.yinn.ymall.ware.dao;

import com.yinn.ymall.ware.entity.SkuStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-08 01:19:23
 */
@Mapper
public interface SkuStockDao extends BaseMapper<SkuStock> {
}
