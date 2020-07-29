package com.yinn.ymall.product.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.product.dao.AttrGroupDao;
import com.yinn.ymall.product.dto.AttrGroupDTO;
import com.yinn.ymall.product.dto.AttrGroupPageQueryDTO;
import com.yinn.ymall.product.dto.ItemDTO.SpuAttrGroupDTO;
import com.yinn.ymall.product.entity.AttrGroup;
import com.yinn.ymall.product.service.AttrGroupService;
import com.yinn.ymall.product.service.AttributeService;

@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroup> implements AttrGroupService {

    @Autowired
    private AttributeService attributeService;

    /**
     *
     * @param page       分页数据
     * @param key        查询关键字，匹配categoryId或attrGroupName
     * @param categoryId 分类id
     * @return:
     * @Date: 2020-05-03 22:14:31
     */
    @Override
    public Page<AttrGroup> queryPage(AttrGroupPageQueryDTO attrGroupQueryDTO) {
        var w = Wrappers.<AttrGroup>lambdaQuery();
        String key=attrGroupQueryDTO.getKey();
        w.and(StringUtils.isNotBlank(key),e -> e.eq(AttrGroup::getId, key).or().like(AttrGroup::getName, key));
        var categoryId=attrGroupQueryDTO.getCategoryId();
        w.eq(categoryId!=null,AttrGroup::getCategoryId, categoryId);
        return page(attrGroupQueryDTO.page(), w);
    }

    /**
     * 根据分类id查询对应所有属性组，并附带上属性组对应详细属性信息后返回
     *
     * @param categoryId 分类id
     * @return:
     * @Date: 2020-05-05 04:29:34
     */
    @Override
    public List<AttrGroupDTO> listByCategoryIdWithAttrs(Long categoryId) {
        // @formatter:off
        return listByCategoryId(categoryId)
                        .stream()
                        .map(AttrGroupDTO::convertFor)
                        .map(e -> e.setAttributes(attributeService.listByAttrGroupId(e.getId())))
                        .collect(Collectors.toList());
        // @formatter:on
    }

    @Override
    public List<AttrGroup> listByCategoryId(Long categoryId) {
        return list(Wrappers.<AttrGroup>lambdaQuery().eq(AttrGroup::getCategoryId, categoryId));
    }

    @Override
    public List<SpuAttrGroupDTO> listSpuAttrGroupDTOs(Long spuId, Long categoryId) {
        return baseMapper.listSpuAttrGroupDTOs(spuId, categoryId);
    }
}