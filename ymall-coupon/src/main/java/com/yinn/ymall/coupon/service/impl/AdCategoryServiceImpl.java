package com.yinn.ymall.coupon.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.coupon.dao.AdCategoryDao;
import com.yinn.ymall.coupon.entity.AdCategory;
import com.yinn.ymall.coupon.service.AdCategoryService;


@Service
public class AdCategoryServiceImpl extends ServiceImpl<AdCategoryDao, AdCategory> implements AdCategoryService {
}