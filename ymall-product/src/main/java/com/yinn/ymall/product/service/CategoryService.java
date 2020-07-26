package com.yinn.ymall.product.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.product.entity.Category;

/**
 * 商品分类表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
public interface CategoryService extends IService<Category> {

	List<Category> listWithTree();

	List<Long> getCategoryPath(Long categoryId);
}

