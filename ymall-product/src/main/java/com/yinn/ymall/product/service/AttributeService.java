package com.yinn.ymall.product.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.product.constant.AttrTypeEnum;
import com.yinn.ymall.product.entity.Attribute;

/**
 * 商品属性表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
public interface AttributeService extends IService<Attribute> {
    List<Attribute> listByAttrGroupId(Long attrGroupId);

    Page<Attribute> queryPageByCategoryId(Page<Attribute> page, String key, Long categoryId, AttrTypeEnum type);

    Page<Attribute> InvertListByAttrGroupId(Page<Attribute> page, String key, Long attrGroupId);

    List<Attribute> listSearchAttrsByIds(List<Long> attrIds);
}
