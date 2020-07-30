package com.yinn.ymall.product.service;

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

 /**
  * 根据属性id和属性组id删除对应关联关系
  *
  * @param attrId 属性主键
  * @param attrGroupId 属性组主键
  * @Date: 2020-05-23 22:04:53
  */
    void remove(Long attrId, Long attrGroupId);

    Long getAttrGroupIdByAttrId(Long attrId);
}

