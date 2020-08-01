package com.yinn.ymall.product.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.product.entity.AttrAttrGroupRelation;

/**
 * 属性与属性分组的关系表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
public interface AttrAttrGroupRelationService extends IService<AttrAttrGroupRelation> {

    void removeByAttrId(Long attrId);

    void removeByAttrIds(List<Long> attrIds);

    List<Long> getAttrGroupIdsByAttrId(Long attrId);

    List<Long> getAttrIdsByAttrGroupId(Long attrGroupId);

}

