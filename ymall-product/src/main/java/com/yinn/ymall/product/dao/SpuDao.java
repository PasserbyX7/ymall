package com.yinn.ymall.product.dao;

import com.yinn.ymall.product.entity.Spu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品spu表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:50
 */
@Mapper
public interface SpuDao extends BaseMapper<Spu> {
}
