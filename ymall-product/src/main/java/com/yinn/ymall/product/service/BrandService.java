package com.yinn.ymall.product.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.product.dto.BrandQueryDTO;
import com.yinn.ymall.product.entity.Brand;

/**
 * 品牌表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
public interface BrandService extends IService<Brand> {

	Page<Brand> queryPage(BrandQueryDTO brandQueryDTO);

	List<Brand> listByCategoryId(Long categoryId);
}

